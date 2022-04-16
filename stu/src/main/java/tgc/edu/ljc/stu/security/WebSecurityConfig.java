package tgc.edu.ljc.stu.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import tgc.edu.ljc.stu.custom.AjaxResult;
import tgc.edu.ljc.stu.filter.JWTAuthenticationFilter;
import tgc.edu.ljc.stu.service.SysUserService;
@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private SysUserService userService;
	//@Autowired
	//private MyAccessDeniedHandler accessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
		auth.inMemoryAuthentication().withUser("aaa").password("{noop}1234").roles("DIY","ADMIN");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	 	http.csrf().disable();
	 	http.cors();
	 	http.headers().frameOptions().disable();    //解决iframe与安全器兼容性问题

		http.formLogin()
			//登录请求被拦截
			.loginPage("/login").permitAll()
			//设置默认登录成功跳转页面
			.successForwardUrl("/loginSucc").permitAll()
//			.failureHandler(new SimpleUrlAuthenticationFailureHandler() {
//				
//				@Override
//				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//						AuthenticationException exception) throws IOException, ServletException {
//					ObjectMapper mapper=new ObjectMapper();
//					response.setContentType("application/json;charset=utf-8");
//					//response.setStatus(401);
//					PrintWriter out = response.getWriter();
//					out.write(mapper.writeValueAsString(new AjaxResult(false,"用户名或密码错误")));
//					out.close();
//				}
//			});
			.failureForwardUrl("/loginFailure").permitAll();
			//登录失败的页面
			//.failureUrl("/loginFailure").permitAll();
		http.logout().logoutUrl("/logout").permitAll()
		.logoutSuccessUrl("/logoutSucc").permitAll();     //退出
//		.logoutSuccessHandler(new LogoutSuccessHandler() {
//			@Override
//			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
//					throws IOException, ServletException {
//				ObjectMapper mapper=new ObjectMapper();
//				response.setContentType("application/json;charset=utf-8");
//				//response.setStatus(200);
//				PrintWriter out = response.getWriter();
//				out.write(mapper.writeValueAsString(new AjaxResult("注销成功")));
//				out.close();
//			}
//		});
		http.authorizeRequests().antMatchers("/student/download").permitAll();
        http.authorizeRequests().anyRequest().authenticated()  //除此之外的都必须通过请求验证才能访问
        	.and()
        	.addFilter(new JWTAuthenticationFilter(authenticationManager())); //所有请求加过滤器，检查是否有token   
        http.exceptionHandling().accessDeniedHandler(new MyAccessDeniedHandler())     //权限不足的处理
        .authenticationEntryPoint(new MyAuthenticationEntryPoint());          //没有登录的处理
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
	    configuration.applyPermitDefaultValues();
		//configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		//configuration.setAllowedMethods(Arrays.asList("GET","POST","OPTIONS"));
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", configuration);
	      return source;
	
	}
	
}
