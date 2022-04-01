package com.jxys.auth.config;

import com.jxys.auth.annotation.resolver.AuthMethodArgumentResolver;
import com.jxys.auth.interceptor.LoginFailureInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootConfiguration
public class InterceptorConfig implements WebMvcConfigurer {

	//使这个拦截器提前加载，避免@Autowired无法获取到数据
	@Bean
	public LoginFailureInterceptor getLoginInterceptor(){
		return new LoginFailureInterceptor();
	}

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*//注册自己的拦截器,并设置拦截路径，拦截多个可以全一个list集合*/
		//业务接口
		List<String> excludeList=new ArrayList<>();
        excludeList.add("/user/signIn");
        excludeList.add("/user/signUp");
        //组件接口
        excludeList.add("/swagger-resources/**");
        excludeList.add("/webjars/**");
        excludeList.add("/v2/**");
        excludeList.add("/error");
        excludeList.add("/csrf");
        excludeList.add("/");
        excludeList.add("/swagger-ui.html/**");
        excludeList.add("/doc.html/**");
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/**").excludePathPatterns(excludeList);
    }

	/**
	 * 添加认证注解参数解析器
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(authMethodArgumentResolver());
	}
	/**
	 * 注册认证注解参数解析器
	 */
	@Bean
	public AuthMethodArgumentResolver authMethodArgumentResolver() {
		return new AuthMethodArgumentResolver();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations(
                "classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
                "classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations(
                "classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
    }
}
