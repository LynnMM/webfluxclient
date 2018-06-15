package org.lynn.springboot2.webfluxclient.bean;

/**
 * 服务器信息类
 *
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 16:31
 */
public class ServerInfo {

  /**
   * 服务器url
   */
  private String url;

  public ServerInfo(){ }

  public ServerInfo(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @Override
  public String toString() {
    return "ServerInfo{" +
        "url='" + url + '\'' +
        '}';
  }
}
