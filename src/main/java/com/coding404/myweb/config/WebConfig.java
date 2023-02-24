package com.coding404.myweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


import com.coding404.myweb.util.interceptor.MenuHandler;
import com.coding404.myweb.util.interceptor.UserAuthHandler;

@Configuration //스프링 설정 파일
public class WebConfig implements WebMvcConfigurer{

	@Bean
	public UserAuthHandler userAuthHandler() {
		return new UserAuthHandler();
	}
	
	@Bean
	public MenuHandler menuHandler() {
		return new MenuHandler();
	}
	
	//인터셉터 추가
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
//		registry.addInterceptor(userAuthHandler())
//				.addPathPatterns("/product/*")
//				.addPathPatterns("/main")
//				.addPathPatterns("/user/*")
//				.excludePathPatterns("/user/login")
//				.excludePathPatterns("/user/join");
				
				//.addPathPatterns("/**")
				//.excludePathPatterns("/user/login")
				//.excludePathPatterns("/user/join")
				//.excludePathPatterns("/js/*")
				//.excludePathPatterns("/css/*")
				//.excludePathPatterns("/img/*");
				//Rest API 패스에서 제외...?
		
		registry.addInterceptor(menuHandler())
				.addPathPatterns("/main")
				.addPathPatterns("/product/*")
				.addPathPatterns("/user/*");
		
	}
}
