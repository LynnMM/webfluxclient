package org.lynn.springboot2.webfluxclient;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tangxinyi@Ctrip.com
 * @date 2018/6/14 11:36
 */
@Service
public class UserApiImpl implements IUserApi {

  @Override
  public Flux<User> getAllUser() {
    return null;
  }

  @Override
  public Mono<User> getUserById(String id) {
    return null;
  }

  @Override
  public Mono<Void> deleteUserById(String id) {
    return null;
  }

  @Override
  public Mono<User> createUser(Mono<User> user) {
    return null;
  }
}
