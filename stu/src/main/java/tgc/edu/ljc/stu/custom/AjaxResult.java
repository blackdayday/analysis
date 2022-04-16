package tgc.edu.ljc.stu.custom;

import java.util.HashMap;

import org.springframework.data.domain.Page;

public class AjaxResult {
	private Boolean success;
	private String msg;
	private Object data;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public AjaxResult(String msg) {
		super();
		this.msg = msg;
		this.success=true;
	}
	
	public AjaxResult(Boolean success, String msg) {
		super();
		this.success = success;
		this.msg = msg;
	}
	
	public AjaxResult(Object data) {
		super();
		this.data = data;
		this.success=true;
	}
	
	public AjaxResult(String msg, Object data) {
		super();
		this.success=true;
		this.msg=msg;
		this.data=data;
	}
	@SuppressWarnings("rawtypes")
	public static HashMap<String, Object> buildTableResult(Page page){
		HashMap<String, Object> result=new HashMap<>();
		result.put("rows", page.getContent());
		result.put("total", page.getTotalElements());
		return result;
	}
	public AjaxResult(Boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	
	
	
	
}
