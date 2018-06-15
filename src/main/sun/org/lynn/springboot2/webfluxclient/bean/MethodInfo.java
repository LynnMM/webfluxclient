package org.lynn.springboot2.webfluxclient.bean;

import java.util.Map;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

/**
 * 方法调用信息类
 *
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 16:35
 */
public class MethodInfo {

  /**
   * 请求url
   */
  private String url;

  /**
   * 请求方法
   */
  private HttpMethod method;

  /**
   * 请求参数(url)
   */
  private Map<String, Object> params;

  /**
   * 请求Body
   */
  private Mono<?> body;

  public MethodInfo(){ };

  public MethodInfo(String url, HttpMethod method, Map<String, Object> params, Mono<?> body) {
    this.url = url;
    this.method = method;
    this.params = params;
    this.body = body;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public HttpMethod getMethod() {
    return method;
  }

  public void setMethod(HttpMethod method) {
    this.method = method;
  }

  public Map<String, Object> getParams() {
    return params;
  }

  public void setParams(Map<String, Object> params) {
    this.params = params;
  }

  public Mono<?> getBody() {
    return body;
  }

  public void setBody(Mono<?> body) {
    this.body = body;
  }

  @Override
  public String toString() {
    return "MethodInfo{" +
        "url='" + url + '\'' +
        ", method=" + method +
        ", params=" + params +
        ", body=" + body +
        '}';
  }
}
