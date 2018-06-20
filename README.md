# webfluxclient
开发一个WebClient框架，类似Feign/Retrofit这样的框架，后台实现是使用Spring 5 提供的rest web client，是一个响应式的客户端

采用了JDK动态代理，代理模式的思想：

RestHandler rest请求调用Handler 相当于java.lang.reflect包下的InvocationHandler，调用委托类的方法时， 实际就是调用实现了RestHandler接口的invokeRest方法
ProxyCreator 创建代理类接口， 其实现类JDKProxyCreator的createProxy方法中会调用java.lang.reflect包下的Proxy类的newProxyInstance方法
IUserApi 即委托方

