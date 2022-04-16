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
import tgc.edu.ljc.stu.entity.TbClass;
import tgc.edu.ljc.stu.form.TbClassForm;

@Controller
@RequestMapping(value = "/banji")
public class TbClassController extends BaseController<TbClass, Integer, TbClassForm>{
	@SuppressWarnings("serial")
	@Override
	public Specification<TbClass> buildSpec(TbClassForm form) {
		Specification<TbClass> spec=new Specification<TbClass>() {

			@Override
			public Predicate toPredicate(Root<TbClass> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				String search = form.getSearch();
				if(StringUtils.hasText(search)) {
					HashSet<Predicate> rules2=new HashSet<>();
					rules2.add(cb.like(root.get("name"), "%"+search+"%"));
					rules2.add(cb.like(root.get("manager"), "%"+search+"%"));
					rules.add(cb.or(rules2.toArray(new Predicate[rules2.size()])));
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return spec;
	}


	
}
