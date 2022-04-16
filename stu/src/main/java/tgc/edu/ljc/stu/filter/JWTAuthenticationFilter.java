package tgc.edu.ljc.stu.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import tgc.edu.ljc.stu.custom.JwtTokenUtils;
import tgc.edu.ljc.stu.security.User2;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter{

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String tokenHeader = request.getHeader(JwtTokenUtils.TOKEN_HEADER);
	    // 如果请求头中没有Authorization信息则直接放行了
	    if (tokenHeader == null || !tokenHeader.startsWith(JwtTokenUtils.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
        //chain.doFilter(request, response);
        super.doFilterInternal(request, response, chain);
	}

	// 这里从token中获取用户信息并新建一个authentication
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(JwtTokenUtils.TOKEN_PREFIX, "");
        String username = JwtTokenUtils.getUsername(token);
        String name=JwtTokenUtils.getName(token);
        String role = JwtTokenUtils.getUserRole(token);
        if (username != null){
            List<GrantedAuthority> authorities=new ArrayList<>();
            for (String authStr : role.split(",")) {
                authorities.add(new SimpleGrantedAuthority(authStr));
            }
            User2 user = new User2(username,"",name, authorities);
            return new UsernamePasswordAuthenticationToken(user, null,authorities);
        }
        return null;
    }
}
