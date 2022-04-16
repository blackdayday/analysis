package tgc.edu.ljc.stu.form;

import org.springframework.web.multipart.MultipartFile;

import tgc.edu.ljc.stu.custom.BaseForm;

public class StudentForm extends BaseForm<Integer>{
	private String stuNo;
	private String name;
	private String sex;
	//private TbClass tbClass;
	private Integer tbClassId;
	private MultipartFile file;
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getTbClassId() {
		return tbClassId;
	}
	public void setTbClassId(Integer tbClassId) {
		this.tbClassId = tbClassId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
