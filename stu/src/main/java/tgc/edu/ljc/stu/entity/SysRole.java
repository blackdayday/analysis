package tgc.edu.ljc.stu.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import tgc.edu.ljc.stu.custom.BaseEntity;

@Entity
public class SysRole extends BaseEntity<Integer>{
	//private Integer id;
	private String name;
	private String code;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public SysRole(Integer id) {
		super(id);
		
	}
	public SysRole() {
		super();
	}
	
	
}
