package tgc.edu.ljc.stu.form;

import tgc.edu.ljc.stu.custom.BaseForm;

public class CourseForm extends BaseForm<Integer>{
	private String name;
	private Integer classHour;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getClassHour() {
		return classHour;
	}
	public void setClassHour(Integer classHour) {
		this.classHour = classHour;
	}
	
	
}
