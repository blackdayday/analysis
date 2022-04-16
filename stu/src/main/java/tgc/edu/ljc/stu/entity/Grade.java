package tgc.edu.ljc.stu.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import tgc.edu.ljc.stu.custom.BaseEntity;
@Entity
public class Grade extends BaseEntity<Integer>{
	private Student student;
	private Course course;
	private Integer grade;
	
	@ManyToOne
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	
	@ManyToOne
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Grade(Integer id) {
		super(id);
		// TODO Auto-generated constructor stub
	}
	
	
}
