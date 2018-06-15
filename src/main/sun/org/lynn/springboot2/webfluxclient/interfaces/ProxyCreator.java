package org.lynn.springboot2.webfluxclient.interfaces;

/**
 * 创建代理类接口
 *
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 16:20
 */
public interface ProxyCreator {

  /**
   * 创建代理类
   *
   * @param type
   * @return
   */
  Object createProxy(Class<?> type);
}
