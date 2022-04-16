package tgc.edu.ljc.stu.form;

import tgc.edu.ljc.stu.custom.BaseForm;
import tgc.edu.ljc.stu.entity.Course;
import tgc.edu.ljc.stu.entity.Student;

public class GradeForm extends BaseForm<Integer>{
//	private Course course;
//	private Student student;
	private Integer grade;
	private Integer courseId;
	private Integer studentId;
	

	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
}
