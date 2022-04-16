package tgc.edu.ljc.stu.form;

import tgc.edu.ljc.stu.custom.BaseForm;

public class TbClassForm extends BaseForm<Integer>{
	private String name;
	private String manager;
	
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
