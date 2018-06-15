package org.lynn.springboot2.webfluxclient.handler;

import org.lynn.springboot2.webfluxclient.bean.MethodInfo;
import org.lynn.springboot2.webfluxclient.bean.ServerInfo;
import org.lynn.springboot2.webfluxclient.interfaces.RestHandler;

/**
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 21:53
 */
public class WebClientRestHandler implements RestHandler {

  @Override
  public void init(ServerInfo serverInfo) {

  }

  @Override
  public Object invokeRest(MethodInfo methodInfo) {
    return null;
  }
}
