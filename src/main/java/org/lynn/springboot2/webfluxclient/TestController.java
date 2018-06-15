package org.lynn.springboot2.webfluxclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/13 22:09
 */
@RestController
public class TestController {
  // 直接注入定义的接口
  @Autowired
  IUserApi userApi;

  @GetMapping("/")
  public void test(){
    // 测试信息提取
    // 不订阅，不会实际发出请求，但会进入我们的代理类
    userApi.getAllUser();
    userApi.getUserById("11111111");
    userApi.deleteUserById("11111111");
    userApi.createUser(Mono.just(new User("xfq", 33)));

//    // 直接调用 实现调用rest接口的效果
//    Flux<User> users = userApi.getAllUser();
//    users.subscribe(System.out::println);
  }
}
