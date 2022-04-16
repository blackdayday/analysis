package tgc.edu.ljc.stu.controller;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tgc.edu.ljc.stu.custom.AjaxResult;
import tgc.edu.ljc.stu.custom.BaseController;
import tgc.edu.ljc.stu.custom.FileUtils;
import tgc.edu.ljc.stu.entity.Student;
import tgc.edu.ljc.stu.entity.SysRole;
import tgc.edu.ljc.stu.entity.TbClass;
import tgc.edu.ljc.stu.form.StudentForm;
import tgc.edu.ljc.stu.service.StudentService;

@Controller
@RequestMapping(value = "/student")
public class StudentController extends BaseController<Student, Integer, StudentForm>{
	@Autowired
	private StudentService service;
	@Value(value = "${web.file_url}")
	private String url; // d:/file
	@Autowired
	private FileUtils fileUtiles;
	
	@SuppressWarnings("serial")
	@Override
	public Specification<Student> buildSpec(StudentForm form) {
		Specification<Student> spec=new Specification<Student>() {

			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				HashSet<Predicate> rules=new HashSet<>();
				Integer tbClassId = form.getTbClassId();
				if(tbClassId!=null) {
					rules.add(cb.equal(root.get("tbClass").get("id"), tbClassId));
				}
				String search = form.getSearch();
				if(StringUtils.hasText(search)) {
					HashSet<Predicate> rules2=new HashSet<>();
					rules2.add(cb.like(root.get("name"), "%"+search+"%"));
					rules2.add(cb.like(root.get("sex"), "%"+search+"%"));
					rules2.add(cb.like(root.get("stuNo"), "%"+search+"%"));
					rules.add(cb.or(rules2.toArray(new Predicate[rules2.size()])));
				}
				return cb.and(rules.toArray(new Predicate[rules.size()]));
			}
		};
		return spec;
	}

	@Override
	@Transactional
	public Object save(StudentForm form) {
		try {
			Student model=new Student();
			Integer id = form.getId();
			if(id!=null) {
				model=service.findById(id);
			}else {
				model.setPassword(new BCryptPasswordEncoder().encode("1111"));
			}
			MultipartFile file = form.getFile();
			if(file!=null) {
				String filename = file.getOriginalFilename();
				if(StringUtils.hasText(filename)) {
					File path=new File(url);
					if(!path.exists()) {
						path.mkdir();
					}
					String uuid=model.getUuid();
					if(uuid==null) {
						uuid = UUID.randomUUID().toString();
					}
					File saveFile=new File(path,uuid);
					file.transferTo(saveFile);
					model.setFilename(filename);
					model.setUuid(uuid);
				}
			}
			Integer tbClassId = form.getTbClassId();
			if(tbClassId==null) {
				model.setTbClass(null);
			}else {
				model.setTbClass(new TbClass(tbClassId));
			}
			List<SysRole> roles = model.getRoles();
			roles.add(new SysRole(2));
			String stuNo = form.getStuNo();
			model.setUsername(stuNo);
			BeanUtils.copyProperties(form, model,"id","tbClss");
			model = service.save(model);
			return new AjaxResult(true,"数据保存成功",model);
			
		} catch (Exception e) {
			return new AjaxResult(false,"数据保存失败");
		}
	}

	@RequestMapping(value = "/restPwd")
	@ResponseBody
	public Object restPwd(Integer id) {
		try {
			Student student = service.findById(id);
			student.setPassword(new BCryptPasswordEncoder().encode("1111"));
			service.save(student);
			return new AjaxResult("密码重置成功");
		} catch (Exception e) {
			return new AjaxResult(false,"密码重置失败");
		}
	}
	
	
	@RequestMapping(value = "/download")
	@ResponseBody
	public Object download(Integer id,HttpServletRequest request,HttpServletResponse response) {
		Student stu=service.findById(id);
		String filename = stu.getFilename();
		String uuid = stu.getUuid();
		fileUtiles.download(request, response, uuid, filename);
		return null;
	}

	@Override
	public Object delete(StudentForm form) {
		
		Integer id = form.getId();
		Student model = service.findById(id);
		String uuid = model.getUuid();
		try {
			if(StringUtils.hasText(uuid)) {
				File file = new File(url, uuid);
				file.delete();
			}
			service.deleteById(id);
			return new AjaxResult("数据删除成功");
		}catch(Exception e) {
			return new AjaxResult("数据删除失败");
		}
		
	}
	
	
}
