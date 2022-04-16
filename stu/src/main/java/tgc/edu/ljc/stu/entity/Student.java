package tgc.edu.ljc.stu.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
@Entity
public class Student extends SysUser{
	private String stuNo;
	private String sex;
	private TbClass tbClass;
	private String filename;
	private String uuid;
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@ManyToOne
	public TbClass getTbClass() {
		return tbClass;
	}
	public void setTbClass(TbClass tbClass) {
		this.tbClass = tbClass;
	}
	@Column(name = "stu_num")
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public Student() {
		super();
	}
	public Student(Integer id) {
		super(id);
	}
	@Transient
	public Integer getTbClassId() {
		return tbClass==null?null:tbClass.getId();
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
