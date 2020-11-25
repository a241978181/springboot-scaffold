package com.jxys.scaffold.auth.interceptor;

import com.jxys.scaffold.auth.enums.TokenSubject;
import com.jxys.scaffold.auth.exception.AuthException;
import com.jxys.scaffold.auth.exception.LogonInvalidException;
import com.jxys.scaffold.auth.exception.TokenException;
import com.jxys.scaffold.auth.exception.UnauthorizedException;
import com.jxys.scaffold.auth.service.TokenService;
import com.jxys.scaffold.base.config.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@CrossOrigin//跨域访问
@Component
public class LoginFailureInterceptor implements HandlerInterceptor {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	@Autowired
	private TokenService tokenService;
	
	//请求接口运行前执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 从Header取出AccessToken
		String token = request.getHeader("Access-Token");
		// 判断Token是否为空
		if (token != null && token != "") {
			try {
				// 从Token中获取Id并捕获异常
				String id= (String) tokenService.parse(TokenSubject.ACCESS, token).getBody().get("id");
				//长时间未操作过期校验
                if (this.redisTemplate.hasKey(id)){
                	//未过期直接进入
					return true;
                }else{
					//过期直接抛出异常
					throw new LogonInvalidException();
				}
			} catch (TokenException e) {
				// Token失效，抛出认证异常
				throw new AuthException();
			}
		} else {
			// Token不能为空，抛出未登录异常
			throw new UnauthorizedException();
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}
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

	
}
