package org.lynn.springboot2.webfluxclient.handler;

import org.lynn.springboot2.webfluxclient.bean.MethodInfo;
import org.lynn.springboot2.webfluxclient.bean.ServerInfo;
import org.lynn.springboot2.webfluxclient.interfaces.RestHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

/**
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 21:53
 */
public class WebClientRestHandler implements RestHandler {

  private WebClient client;

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

    ResponseSpec request = this.client
        // 请求方法
        .method(methodInfo.getMethod())
        // 请求url
        .uri(methodInfo.getUrl(), methodInfo.getParams())
        .accept(MediaType.APPLICATION_JSON)
        // 发出请求
        .retrieve();

    // 处理body
    if (methodInfo.isReturnFlux()) {
      result = request.bodyToFlux(methodInfo.getReturnElementType());
    }
    else {
      result = request.bodyToMono(methodInfo.getReturnElementType());
    }

    return result;
  }
}
