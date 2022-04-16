package tgc.edu.ljc.stu.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import tgc.edu.ljc.stu.entity.SysUser;
import tgc.edu.ljc.stu.repository.SysUserRepository;

@Component
public class UserUtils {
	@Autowired
	private SysUserRepository userDAO;
	
	public SysUser getUser() {
		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		return userDAO.findByUsername(username);
	}
	
	public boolean hasRole(String roleCode) {
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		List<String> roleCodes=new ArrayList<>();
		for (GrantedAuthority authority : authorities) {
			roleCodes.add(authority.getAuthority());
		}
		return roleCodes.contains(roleCode);
	}

	public String getUsername() {
		UserDetails userDetails=(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = userDetails.getUsername();
		return username;
	}
	
	public String getName() {
		try {
			User2 user=(User2)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return user.getName();
		} catch (Exception e) {
			String username = getUsername();
			if("aaa".equals(username)) {
				return "开发者";
			}else {
				return "访客";
			}
		}

	}
	
	public String getRoles() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		String roleStr=authorities.toString();
		roleStr=roleStr.substring(1,roleStr.length()-1);
		return roleStr;
	}

}
