package tgc.edu.ljc.stu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tgc.edu.ljc.stu.custom.AjaxResult;
import tgc.edu.ljc.stu.custom.BaseController;
import tgc.edu.ljc.stu.entity.Course;
import tgc.edu.ljc.stu.entity.Grade;
import tgc.edu.ljc.stu.entity.Student;
import tgc.edu.ljc.stu.entity.SysUser;
import tgc.edu.ljc.stu.entity.TbClass;
import tgc.edu.ljc.stu.form.GradeForm;
import tgc.edu.ljc.stu.security.UserUtils;
import tgc.edu.ljc.stu.service.GradeService;
import tgc.edu.ljc.stu.service.StudentService;

@Controller
@RequestMapping(value = "/grade")
public class GradeController extends BaseController<Grade, Integer, GradeForm>{
	@Autowired
	private GradeService service;
	@Autowired
	private StudentService studentService;
	@Autowired
	private UserUtils userUtils;

	@Override
	public Object save(GradeForm form) {
		Grade model=new Grade();
		Integer id = form.getId();
		System.out.println(id);
		if(id!=null) {
			model=service.findById(id);
		}
		Integer courseId = form.getCourseId();
		System.out.println(courseId);
		if(courseId==null) {
			model.setCourse(null);
		}else {
			model.setCourse(new Course(courseId));
		}
		Integer studentId = form.getStudentId();
		System.out.println(studentId);
		if(studentId==null) {
			model.setStudent(null);
		}else {
			model.setStudent(new Student(studentId));
		}
		model.setGrade(form.getGrade());
		model=service.save(model);
		return new AjaxResult(model);
	}
	
	@RequestMapping(value = "/findGradeListByCourseIdAndBanjiId")
	@ResponseBody
	public List<Grade> findGradeListByCourseIdAndBanjiId(Integer courseId,Integer banjiId){
		List<Grade> list=new ArrayList<>();
		List<Student> stus = studentService.findByTbClassId(banjiId);
		for (Student student : stus) {
			Grade grade=service.findByCourseIdAndStudentId(courseId,student.getId());
			if(grade==null) {
				grade=new Grade();
				grade.setCourse(new Course(courseId));
				grade.setStudent(student);
				grade=service.save(grade);
			}
			list.add(grade);
		}
		return list;
	}

	@SuppressWarnings("serial")
	@Override
	public Specification<Grade> buildSpec(GradeForm form) {
		Specification<Grade> spec=new Specification<Grade>() {

			@Override
			public Predicate toPredicate(Root<Grade> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				Integer studentId = form.getStudentId();
				if(studentId!=null) {
					rules.add(cb.equal(root.get("student").get("id"), studentId));
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return spec;
	}
	
	@RequestMapping(value = "/banjiGradeList")
	@ResponseBody
	public Object banjiGradeList(Integer tbClassId) {
		System.out.println(tbClassId);
		List<Course> courses=service.findCoursesByTbClassId(tbClassId);
		System.out.println(courses);
		List<String> cols=new ArrayList<>();
		cols.add("学号");
		cols.add("姓名");
		for (Course course : courses) {
			cols.add(course.getName());
		}
		cols.add("总分");
		List<HashMap<String, Object>> rows=service.findGradeRows(courses,tbClassId);
		System.out.println(rows);
		HashMap<String, Object> result=new HashMap<>();
		result.put("cols", cols);
		result.put("tableData", rows);
		return result;
	}
	
	@RequestMapping(value = "/myGrade")
	@ResponseBody
	public Object myGrade() {
		SysUser user = userUtils.getUser();
		Integer id = user.getId();
		List<Grade>list= service.findByStudentId(id); 
		return new AjaxResult(list);
	}
	
	
	@RequestMapping(value = "/myClassGrade")
	@ResponseBody
	public Object myClassGrade() {
		SysUser user = userUtils.getUser();
		Student stu=(Student) user;
		Integer id = stu.getTbClass().getId();
		return banjiGradeList(id);
	}
}
