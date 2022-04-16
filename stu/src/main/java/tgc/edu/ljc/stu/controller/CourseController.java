package tgc.edu.ljc.stu.controller;

import java.util.HashSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import tgc.edu.ljc.stu.custom.BaseController;
import tgc.edu.ljc.stu.entity.Course;
import tgc.edu.ljc.stu.form.CourseForm;

@Controller
@RequestMapping(value = "/course")
public class CourseController extends BaseController<Course, Integer, CourseForm>{

	@SuppressWarnings("serial")
	@Override
	public Specification<Course> buildSpec(CourseForm form) {
		Specification<Course> spec=new Specification<Course>() {

			@Override
			public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				String search = form.getSearch();
				if(StringUtils.hasText(search)) {
					HashSet<Predicate> rules2=new HashSet<>();
					rules2.add(cb.like(root.get("name"), "%"+search+"%"));
					rules2.add(cb.like(root.get("classHour").as(String.class), "%"+search+"%"));
					rules.add(cb.or(rules2.toArray(new Predicate[rules2.size()])));
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return spec;
	}

}
