package tgc.edu.ljc.stu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import tgc.edu.ljc.stu.custom.BaseEntity;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class
SysUser extends BaseEntity<Integer>{
	//private Integer id;
	private String username;
	private String name;
	@JsonIgnore
	private String password;
	@JsonIgnore
	private List<SysRole> roles=new ArrayList<>();
	
	@Column(length = 20,unique = true)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
	@JoinTable(name = "sys_user_role",joinColumns = {@JoinColumn(name="user_id")},inverseJoinColumns = {@JoinColumn(name="role_id")})
	public List<SysRole> getRoles() {
		return roles;
	}
	public void setRoles(List<SysRole> roles) {
		this.roles = roles;
	}
	
	@Transient
	public String getRoleNames() {
		String roleNames="";
		for (SysRole role : roles) {
			roleNames+=","+role.getName();
		}
		if(roleNames.length()>0) {
			roleNames=roleNames.substring(1);
		}
		return roleNames;
	}

	@Transient
	public String getRoleCodes() {
		String roleNames="";
		for (SysRole role : roles) {
			roleNames+=","+role.getCode();
		}
		if(roleNames.length()>0) {
			roleNames=roleNames.substring(1);
		}
		return roleNames;
	}
	public SysUser() {
		super();
	}
	public SysUser(Integer id) {
		super(id);
	
	}

}
