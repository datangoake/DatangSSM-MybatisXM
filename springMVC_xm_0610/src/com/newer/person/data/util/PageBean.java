package com.newer.person.data.util;

import java.util.List;

//分页工具
public class PageBean<T> {
	//表实体集合
	private List<T> data;
	// 总记录数
	private int totalRecords;
	// 页号
	private int pageNo;
	// 每一页记录的总数
	private int pageSize;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}


	public int getTotalPages() {
		// if (this.totalRecords % pageSize == 0)
		// return this.totalRecords / pageSize;
		// return this.totalRecords / pageSize + 1;
		return (this.totalRecords + this.pageSize - 1) / this.pageSize;
	}

	
	public int getFirstPage() {
		return 1;
	}

	
	public int getLastPage() {
		if (this.totalRecords == 0)
			return 1;
		return this.getTotalPages();
	}

	
	public int getPreviousPage() {
		if (this.pageNo == 1)
			return 1;
		return this.pageNo - 1;
	}

	
	public int getNextPage() {
		if (this.pageNo == this.getLastPage())
			return this.pageNo;
		return this.pageNo + 1;
	}
}
