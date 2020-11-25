[返回目录](../index.md)

# BaseService 父类

## 简述
BaseService.java是所有Service的父类，他为所有的Service提供了一个封装机制，目前已经集成了`logger`的打印工具
方便项目中`try catch`中进行信息的打印

## 使用方法

#### 1. 继承
```java_holder_method_tree
@Service
public class UserServiceImpl extends BaseService implements UserService
```

#### 2. 使用logger

```java_holder_method_tree
try {
    this.redisTemplate.opsForValue().set(user.getId(), user, Constant.REDIS_OVERDUE_TIME_MINUTES, TimeUnit.MINUTES);
} catch (Exception e) {
    //此处调用logger
    logger.error("向redis中存入数据失败!",e);  
    throw new ErrorServiceException();
}finally {
    RedisConnectionUtils.unbindConnection(redisTemplate.getConnectionFactory());
}
```