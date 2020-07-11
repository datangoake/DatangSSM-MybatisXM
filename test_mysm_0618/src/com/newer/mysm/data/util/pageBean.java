package com.newer.mysm.data.util;

import java.util.List;

public class pageBean<T> {
	// 保存当前页的数据的集合
	private List<T> data;
	// 保存分页前的总的记录数
	private int totalRecords;
	// 保存当前页号
	private int pageNo;
	// 保存页大小
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

	// 返回总页数的方法
	public int getTotalPages() {
		// if (this.totalRecords % this.pageSize == 0)
		// return this.totalRecords / this.pageSize;
		// return this.totalRecords / this.pageSize + 1;
		return (this.totalRecords + this.pageSize - 1) / this.pageSize;
	}

	// 返回首页的方法
	public int getFirstPage() {
		return 1;
	}

	// 返回最后一页的方法
	public int getLastPage() {
		//必须使用getTotalPages()返回，不得使用this.totalPages
		if (this.totalRecords == 0)
			return 1;
		return this.getTotalPages();
	}

	// 返回前一页的方法
	public int getPreviousPage() {
		if (this.pageNo == 1)
			return 1;
		return this.pageNo - 1;
	}

	// 返回后一页的方法
	public int getNextPage() {
		//必须使用getLastPage()方法,不得使用totalPages
		if (this.pageNo == this.getLastPage())
			//不得使用toatalPages返回
			return this.pageNo;
		return this.pageNo + 1;
	}

}
