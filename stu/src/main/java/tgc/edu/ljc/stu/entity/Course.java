package tgc.edu.ljc.stu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import tgc.edu.ljc.stu.custom.BaseEntity;
@Entity
public class Course extends BaseEntity<Integer>{
	private String name;//课程名称
	private Integer classHour; //课时数
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "class_hours")
	public Integer getClassHour() {
		return classHour;
	}
	public void setClassHour(Integer classHour) {
		this.classHour = classHour;
	}
	@Override
	public String toString() {
		return "Course [name=" + name + ", classHour=" + classHour + "]";
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	
}
