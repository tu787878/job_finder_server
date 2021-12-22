package de.tcg.jobFinder.dto;

public abstract class QuerySearch {

	private String orderBy;
	private String orderType;
	private String search;
	private int count;
	private int page;
	private boolean hasQuery;
	
	public QuerySearch(String orderBy, String orderType, String search, int count, int page, boolean hasQuery) {
		super();
		this.orderBy = orderBy;
		this.orderType = orderType;
		this.search = search;
		this.count = count;
		this.page = page;
		this.hasQuery = hasQuery;
	}

	public QuerySearch() {
		super();
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isHasQuery() {
		return hasQuery;
	}

	public void setHasQuery(boolean hasQuery) {
		this.hasQuery = hasQuery;
	}

	@Override
	public String toString() {
		return "QuerySearch [orderBy=" + orderBy + ", orderType=" + orderType + ", search=" + search + ", limit="
				+ count + ", offset=" + page + ", hasQuery=" + hasQuery + "]";
	}

	
	
}
