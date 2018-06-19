package org.lynn.springboot2.webfluxclient.handler;

import org.lynn.springboot2.webfluxclient.bean.MethodInfo;
import org.lynn.springboot2.webfluxclient.bean.ServerInfo;
import org.lynn.springboot2.webfluxclient.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

/**
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 21:53
 */
public class WebClientRestHandler implements RestHandler {

  private WebClient client;
  private RequestBodySpec request;

  /**
   * 初始化webclient
   * @param serverInfo
   */
  @Override
  public void init(ServerInfo serverInfo) {
    this.client = WebClient.create(serverInfo.getUrl());
  }

  /**
   * 处理rest请求
   * @param methodInfo
   * @return
   */
  @Override
  public Object invokeRest(MethodInfo methodInfo) {
    // 返回结果
    Object result = null;

    request = this.client
        // 请求方法
        .method(methodInfo.getMethod())
        // 请求url
        .uri(methodInfo.getUrl(), methodInfo.getParams())
        .accept(MediaType.APPLICATION_JSON);

    ResponseSpec retrieve = null;
    // 判断是否带了body
    if (methodInfo.getBody() != null){
      // 发出请求
      retrieve = request.body(methodInfo.getBody(), methodInfo.getBodyElementType()).retrieve();
    }else{
      // 发出请求
      retrieve = request.retrieve();
    }

    // 处理body
    if (methodInfo.isReturnFlux()) {
      result = retrieve.bodyToFlux(methodInfo.getReturnElementType());
    }
    else {
      result = retrieve.bodyToMono(methodInfo.getReturnElementType());
    }

    return result;
  }
}
