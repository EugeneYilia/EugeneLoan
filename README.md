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
- kafka:  
    topic
    1. register
        - 将必要信息同步，在数据库中创建出来，资金账户的信息在数据库通过异步的方式放在消息队列传递到trade模块然后再在数据库相应表中增加记录
    2. log
        - 异步日志记录  
        key作为文件名，value作为日志内容
    3. notification
        - 异步处理通知消息
### 端口号的具体配置
- 2181:zookeeper kafka注册时使用  kafka-server consumer producer都将自己注册到zookeeper上去
- 9092:kafka server       
