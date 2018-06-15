package org.lynn.springboot2.webfluxclient.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;
import org.lynn.springboot2.webfluxclient.ApiServer;
import org.lynn.springboot2.webfluxclient.bean.MethodInfo;
import org.lynn.springboot2.webfluxclient.bean.ServerInfo;
import org.lynn.springboot2.webfluxclient.handler.WebClientRestHandler;
import org.lynn.springboot2.webfluxclient.interfaces.ProxyCreator;
import org.lynn.springboot2.webfluxclient.interfaces.RestHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

/**
 * 使用jdk动态代理实现代理类
 *
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 16:27
 */
public class JDKProxyCreator implements ProxyCreator{
  private static final Logger logger = LoggerFactory.getLogger(JDKProxyCreator.class);

  @Override
  public Object createProxy(Class<?> type) {
    logger.info(String.format("createProxy:%s", type));

    // 根据接口得到API服务器信息
    ServerInfo serverInfo = extractServerInfo(type);
    logger.info(String.format("serverinfo: %s", serverInfo));

    // 给每一个代理类一个实现
    RestHandler handler = new WebClientRestHandler();

    // 初始化服务器信息(初始化webclient)
    handler.init(serverInfo);

    return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{type}, new InvocationHandler() {
      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据方法和参数得到调用信息
        MethodInfo methodInfo = extractMethodInfo(method, args);

        // 调用rest
        return handler.invokeRest(methodInfo);
      }
    });
  }

  /**
   * 根据方法定义和调用参数得到调用的相关信息
   *
   * @param method
   * @param args
   * @return
   */
  private MethodInfo extractMethodInfo(Method method, Object[] args) {
    MethodInfo methodInfo = new MethodInfo();

    extractUrlAndMethod(method, methodInfo);

    extractRequestParamAndBody(method, args, methodInfo);

    return methodInfo;
  }

  /**
   * 得到调用的参数和body
   * @param method
   * @param args
   * @param methodInfo
   */
  private void extractRequestParamAndBody(Method method, Object[] args, MethodInfo methodInfo) {
    Parameter[] parameters = method.getParameters();

    // 参数和值对应的map
    Map<String, Object> params = new LinkedHashMap<>();
    methodInfo.setParams(params);

    for (int i = 0; i < parameters.length; i++) {
      // 是否带@PathVariable
      PathVariable annoPath = parameters[i].getAnnotation(PathVariable.class);

      if (annoPath != null){
        params.put(annoPath.value(), args[i]);
      }

      // 是否带了RequestBody
      RequestBody annoBody = parameters[i].getAnnotation(RequestBody.class);

      if (annoBody != null){
        methodInfo.setBody((Mono<?>) args[i]);
      }
    }
  }

  /**
   * 得到请求URL和请求方法
   * @param method
   * @param methodInfo
   */
  private void extractUrlAndMethod(Method method, MethodInfo methodInfo) {
    Annotation[] annotations = method.getAnnotations();

    for (Annotation annotation: annotations){
      // GET
      if (annotation instanceof GetMapping){
        GetMapping a = (GetMapping) annotation;
        methodInfo.setUrl(a.value()[0]);
        methodInfo.setMethod(HttpMethod.GET);
      }else if (annotation instanceof PostMapping){
        PostMapping a = (PostMapping) annotation;
        methodInfo.setUrl(a.value()[0]);
        methodInfo.setMethod(HttpMethod.POST);
      } else if (annotation instanceof DeleteMapping){
        DeleteMapping a = (DeleteMapping) annotation;
        methodInfo.setUrl(a.value()[0]);
        methodInfo.setMethod(HttpMethod.DELETE);
      }
    }
  }

  /**
   * 提取服务器信息
   * @param type
   * @return
   */
  private ServerInfo extractServerInfo(Class<?> type) {
    ServerInfo serverInfo = new ServerInfo();
    ApiServer anno = type.getAnnotation(ApiServer.class);
    serverInfo.setUrl(anno.value());

    return serverInfo;
  }
}
