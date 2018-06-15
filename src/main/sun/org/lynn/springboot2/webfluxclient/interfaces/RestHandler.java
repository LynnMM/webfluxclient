package org.lynn.springboot2.webfluxclient.interfaces;

import org.lynn.springboot2.webfluxclient.bean.MethodInfo;
import org.lynn.springboot2.webfluxclient.bean.ServerInfo;

/**
 * rest请求调用Handler
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 16:50
 */
public interface RestHandler {

  /**
   * 初始化服务器信息
   * @param serverInfo
   */
  void init(ServerInfo serverInfo);

  /**
   * 调用rest请求, 返回接口
   * @param methodInfo
   * @return
   */
  Object invokeRest(MethodInfo methodInfo);
}
