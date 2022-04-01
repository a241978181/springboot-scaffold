[返回目录](../index.md)

# logback-spring.xml 日志打印

## 简述

更为细致的logback打印，集中打印了`mapper/dao`层的数据库调用信息，包括执行的mapper与方法、sql语句、填充数据、返回数据信息、时间
等大致一次调用信息如下：

```java_holder_method_tree
2020-11-24 16:28:41 [http-nio-8081-exec-4] DEBUG com.jxys.mapper.UserMapper.selectOne - ==>  Preparing: SELECT id,name,password,email,create_time,update_time FROM user WHERE (name = ?)  
2020-11-24 16:28:41 [http-nio-8081-exec-4] DEBUG com.jxys.mapper.UserMapper.selectOne - ==>  Preparing: SELECT id,name,password,email,create_time,update_time FROM user WHERE (name = ?)  
2020-11-24 16:28:41 [http-nio-8081-exec-4] DEBUG com.jxys.mapper.UserMapper.selectOne - ==> Parameters: 李建(String) 
2020-11-24 16:28:41 [http-nio-8081-exec-4] DEBUG com.jxys.mapper.UserMapper.selectOne - ==> Parameters: 李建(String) 
2020-11-24 16:28:41 [http-nio-8081-exec-4] DEBUG com.jxys.mapper.UserMapper.selectOne - <==      Total: 1 
2020-11-24 16:28:41 [http-nio-8081-exec-4] DEBUG com.jxys.mapper.UserMapper.selectOne - <==      Total: 1 
```

## 日志输出位置

日志输出位置可以在`logback-spring.xml`文件中进行控制

```java_holder_method_tree
	<!-- root: 默认日志输出 -->
	<root level="INFO">
		<appender-ref ref="Console"/>
<!--		<appender-ref ref="fileInfoLog"/>-->
<!--		<appender-ref ref="fileErrorLog"/>-->
	</root>

	<logger name="com.jxys.mapper" level="${sqlLevel}">
		<appender-ref ref="Console"/>
<!--		<appender-ref ref="fileInfoLog"/>-->
<!--		<appender-ref ref="fileErrorLog"/>-->
	</logger>
```

> 注意：logback-spring.xml的命名是以springboot中优先级最高的方式命名，避免后期优先级冲突。