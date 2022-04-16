package tgc.edu.ljc.stu.custom;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class BaseController<T,ID,Form extends BaseForm<ID>> {
	@SuppressWarnings({ "unchecked" })
	private Class<T> clazz=GenericsUtils.getSuperClassGenricType(getClass());
	
	@Autowired
	private BaseService<T, ID> baseService;
	
	public Specification<T> buildSpec(Form form){
		return null;
	}
	
	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(Form form) {
		Specification<T> spec = buildSpec(form);
		return baseService.findAll(spec) ;
	}
	
	@RequestMapping(value = "/page")
	@ResponseBody
	public Object page(TablePageable tablePaegable,Form form) {
		PageRequest pageable = tablePaegable.buildPageRequest();
		Specification<T> spec = buildSpec(form);
		Page<T> page = baseService.findAll(spec, pageable);
		return new AjaxResult(AjaxResult.buildTableResult(page)) ;
	}
	
	@RequestMapping(value = "/manage")
	public void manage(ModelMap modelMap) {
		
	}
	
	@RequestMapping(value = "/edit")
	public void edit(Form form,ModelMap modelMap) throws InstantiationException, IllegalAccessException {
		T model=clazz.newInstance();
		ID id = form.getId();
		if(id!=null) {
			model=baseService.findById(id);
		}
		modelMap.put("model", model);
	}
	
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(Form form)  {
		try {
			T model=clazz.newInstance();
			ID id = form.getId();
			if(id!=null) {
				model=baseService.findById(id);
			}
			BeanUtils.copyProperties(form, model,"id");
			model=baseService.save(model);
			return new AjaxResult(true,"数据保存成功",model);
		}catch (Exception e) {
			return new AjaxResult(false, "数据保存失败");
		}
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(Form form) {
		try {
			ID id = form.getId();
			baseService.deleteById(id);
			return new AjaxResult("数据删除成功");
		}catch (Exception e) {
			return new AjaxResult(false, "数据删除失败");
		}
		
	}
	
	@RequestMapping(value = "/search")
	public void search(Form form,ModelMap modelMap) {
		modelMap.put("model", form);
	}
}
