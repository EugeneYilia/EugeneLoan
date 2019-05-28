# P2P借贷平台


## 具体的内部实现
- 微服务的认证和授权是通过服务器端发给浏览器端的加密cookie来实现的
- 

### 每个组件的功能
- springcloud-gateway:
    1. 路由
    2. 限流
    3. 拦截
    4. 过滤
- springcloud-eureka:
    1. 服务注册
    2. 服务发现
-

### 端口号的具体配置
- 2181:zookeeper kafka注册时使用  kafka-server consumer producer都将自己注册到zookeeper上去
- 9092:kafka server       
