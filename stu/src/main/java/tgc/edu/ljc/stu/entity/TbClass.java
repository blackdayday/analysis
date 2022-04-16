package tgc.edu.ljc.stu.entity;

import javax.persistence.Entity;

import tgc.edu.ljc.stu.custom.BaseEntity;
@Entity
public class TbClass extends BaseEntity<Integer>{
	private String name;
	private String manager;
	
	
	public TbClass() {
		super();
	}
	public TbClass(Integer id) {
		super(id);
	}
	@Override
	public String toString() {
		return "TbClass [name=" + name + ", manager=" + manager + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	
	
}
