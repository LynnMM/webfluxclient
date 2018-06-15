package org.lynn.springboot2.webfluxclient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务器相关的信息
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/13 22:04
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiServer {
  String value() default "";
}
