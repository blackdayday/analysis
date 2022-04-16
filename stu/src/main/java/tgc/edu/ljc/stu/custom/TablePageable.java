package tgc.edu.ljc.stu.custom;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class TablePageable {
	private Integer limit; //分页
	private Integer page;//首记录号（从0开始）
	private String sort;   //排序字段
	private String order;  //顺序，逆序
	
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}


	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	
	public PageRequest buildPageRequest() {
		int page=this.page==null?0:this.page-1;
		int size=limit!=null?limit:10;
		if(sort==null) {
			return PageRequest.of(page, size);
		}else {
			Order order2=new Order(Direction.fromString(order), sort);
			Sort sort2=Sort.by(order2);
			return PageRequest.of(page,size,sort2 );
		}
		
	}
	
	public PageRequest bulidPageable(Sort sort) {
		int page=this.page==null?0:this.page-1;
		int size=limit!=null?limit:10;
		return PageRequest.of(page, size, sort);
	}
	
	public Sort bulidSort() {
		Order order2=new Order(Direction.fromString(order), sort);
		Sort sort2=Sort.by(order2);
		return sort2;
	}
}
