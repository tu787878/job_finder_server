package de.tcg.jobFinder.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JobQuerySearch extends QuerySearch {

	private StatusQuery type;
	private String jobCategoryId;
	private String businessCategory;
	private String tags;
	private float jobSalaryFrom;
	private float jobSalaryTo;
	private String cityId;
	private int postCode;
	private int area;

	public JobQuerySearch(String orderBy, String orderType, String search, int limit, int offset, boolean hasQuery,
			StatusQuery type, String jobCategoryId, String businessCategory, String tags, float jobSalaryFrom,
			float jobSalaryTo, String cityId, int postCode, int area) {
		super(orderBy, orderType, search, limit, offset, hasQuery);
		this.type = type;
		this.jobCategoryId = jobCategoryId;
		this.businessCategory = businessCategory;
		this.tags = tags;
		this.jobSalaryFrom = jobSalaryFrom;
		this.jobSalaryTo = jobSalaryTo;
		this.cityId = cityId;
		this.postCode = postCode;
		this.area = area;
	}

	public JobQuerySearch() {
	}

	public StatusQuery getType() {
		return type;
	}

	public void setType(StatusQuery status) {
		this.type = status;
	}

	public String getJobCategoryId() {
		return jobCategoryId;
	}

	public void setJobCategoryId(String jobCategoryId) {
		this.jobCategoryId = jobCategoryId;
	}

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public float getJobSalaryFrom() {
		return jobSalaryFrom;
	}

	public void setJobSalaryFrom(float jobSalaryFrom) {
		this.jobSalaryFrom = jobSalaryFrom;
	}

	public float getJobSalaryTo() {
		return jobSalaryTo;
	}

	public void setJobSalaryTo(float jobSalaryTo) {
		this.jobSalaryTo = jobSalaryTo;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public int getArea() {
		return area;
	}

	public void setArea(int area) {
		this.area = area;
	}
	
	public List<String> toTagList(){
		List<String> list = Arrays.asList(tags.split("\\s*,\\s*"));
		return list;
	}

	@Override
	public String toString() {
		return "JobQuerySearch [status=" + type + ", jobCategoryId=" + jobCategoryId + ", businessCategory="
				+ businessCategory + ", tags=" + tags + ", jobSalaryFrom=" + jobSalaryFrom + ", jobSalaryTo="
				+ jobSalaryTo + ", cityId=" + cityId + ", postCode=" + postCode + ", area=" + area + ", getOrderBy()="
				+ getOrderBy() + ", getOrderType()=" + getOrderType() + ", getSearch()=" + getSearch() + ", count="
				+ getCount() + ", page=" + getPage() + ", isHasQuery()=" + isHasQuery() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	public static JobQuerySearch toObject(Map<String, String> restDTO) {
		JobQuerySearch jobQuerySearch = new JobQuerySearch();

		final ObjectMapper mapper = new ObjectMapper();
		jobQuerySearch = mapper.convertValue(restDTO, JobQuerySearch.class);

		return jobQuerySearch;
	}

	
}
