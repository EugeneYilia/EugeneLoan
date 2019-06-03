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
- resources:
    1. 将用户中心，借入，借出放入到保护资源中        
- nginx:
    1. 反向代理、负载均衡
        
### 端口号的具体配置
- 2181:zookeeper kafka注册时使用  kafka-server consumer producer都将自己注册到zookeeper上去
- 9092:kafka server       


### TODO
- 增加对象存储服务器，减小主业务服务器的资源负载压力


#### 不是必要看的东西
1. nginx会将/static/静态资源中不存在的资源将其代理到配置好的404.html上  
   对于非/static/开头的资源中的不存在的资源路径都是由springboot程序决定返回的404页面是什么
2. 没有登录访问资源服务器文件打回登录页面
   登陆后借入者访问借出者资源或者借出者访问借入者资源，返回未授权页面
3.    