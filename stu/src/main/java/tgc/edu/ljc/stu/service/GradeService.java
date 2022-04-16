package tgc.edu.ljc.stu.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgc.edu.ljc.stu.custom.BaseService;
import tgc.edu.ljc.stu.entity.Course;
import tgc.edu.ljc.stu.entity.Grade;
import tgc.edu.ljc.stu.entity.Student;
import tgc.edu.ljc.stu.repository.GradeRepository;
import tgc.edu.ljc.stu.repository.StudentRepository;
@Service
public class GradeService extends BaseService<Grade, Integer>{
	
	@Autowired
	private GradeRepository gradeDAO;
	@Autowired
	private StudentRepository studentDAO;
	
	public Grade findByCourseIdAndStudentId(Integer courseId, Integer id) {
		
		return gradeDAO.findByCourseIdAndStudentId(courseId,id);
	}

	public List<Course> findCoursesByTbClassId(Integer tbClassId) {
		return gradeDAO.findCoursesByTbClassId(tbClassId);
	}

	public List<HashMap<String, Object>> findGradeRows(List<Course> courses, Integer tbClassId) {
		List<HashMap<String, Object>> result=new ArrayList<>();
		List<Student> students = studentDAO.findByTbClassIdOrderByStuNo(tbClassId);
		for (Student student : students) {
			HashMap<String, Object> row=new HashMap<>();
			row.put("学号",student.getStuNo());
			row.put("姓名",student.getName());
			Integer total=0;
			for (Course course : courses) {
				Grade grade = findByCourseIdAndStudentId(course.getId(), student.getId());
				total+=grade==null||grade.getGrade()==null?0:grade.getGrade();
				row.put(course.getName(),grade==null?null:grade.getGrade());
			}
			row.put("总分",total);
			result.add(row);
		}
		return result;
	}

	public List<Grade> findByStudentId(Integer id) {
		return gradeDAO.findByStudentId(id);
	}

}
