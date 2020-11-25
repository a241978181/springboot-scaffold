[返回目录](../index.md)

# LoginFailureInterceptor 拦截器

## 简述

LoginFailureInterceptor.java 拦截器目前是用于身份验证与未操作验证的功能。它的工作机制是在访问 Controller 时通过获取并解析请求头上的`Access-Token`来获取当前登陆用户的 Id。

##

## 身份验证
通过判断请求头中存在的`Access-Token`来获取当前登陆用户的 Id
存在id则证明进行过登录，放行。不存在则直接提示未登录信息并拦截该请求。

## 未操作验证
在拦截器中获取到`Access-Token`中解析出的用户ID后配置登录接口中存入Redis中用户的ID键值对进行匹配，
键值对设置有过期时间，默认30分钟。如Redis中存在以该ID为键的键值对数据时则证明局用户上次操作未过30分钟，放行。
如查询不出来则证明登录ID已失效用户已经30分钟以上没有进行过登录则需要返回提示让用户重新登录。

## Redis登录ID失效时间刷新

该时间刷新放在拦截器的请求结束后，进行刷新RedisID的失效时间。
```java_holder_method_tree
//请求接口运行后执行
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 从Header取出AccessToken
		String token = request.getHeader("Access-Token");
		// 判断Token是否为空
		if (token != null && token != "") {
			try {
				// 从Token中获取Id并捕获异常
				String id= (String) tokenService.parse(TokenSubject.ACCESS, token).getBody().get("id");
				redisTemplate.expire(id, Constant.REDIS_OVERDUE_TIME_MINUTES, TimeUnit.MINUTES);
			} catch (TokenException e) {
				// Token失效，抛出认证异常
				throw new AuthException();
			}
		} else {
			// Token不能为空，抛出未登录异常
			throw new UnauthorizedException();
		}
	}
```
## 修改Redis默认失效时间

找到`Constant.java`文件，修改如下时间
````java_holder_method_tree
//长时间未操作Redis验证失效时间
    public static final Integer REDIS_OVERDUE_TIME_MINUTES=30;
````
