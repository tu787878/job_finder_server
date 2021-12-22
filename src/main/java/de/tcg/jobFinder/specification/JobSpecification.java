package de.tcg.jobFinder.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import de.tcg.jobFinder.dto.JobQuerySearch;
import de.tcg.jobFinder.entity.Job;

public class JobSpecification implements Specification<Job>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JobQuerySearch jobQuerySearch;
	

	public JobSpecification(JobQuerySearch jobQuerySearch) {
		super();
		this.jobQuerySearch = jobQuerySearch;
	}

	@Override
	public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<>();
		
		if(jobQuerySearch.getCityId() != null) {
			predicates.add(criteriaBuilder.equal(
                    root.get("city").get("id"), jobQuerySearch.getCityId()));
		}
		
		if(jobQuerySearch.getJobCategoryId() != null) {
			predicates.add(criteriaBuilder.equal(
                    root.get("jobCategory").get("id"), jobQuerySearch.getJobCategoryId()));
		}
		
		if(jobQuerySearch.getJobSalaryFrom() != 0) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(
                    root.get("salaryTo"), jobQuerySearch.getJobSalaryFrom()));
		}
		
		if(jobQuerySearch.getJobSalaryTo() != 0) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(
                    root.get("salaryFrom"), jobQuerySearch.getJobSalaryTo()));
		}
		
		if(jobQuerySearch.getSearch() != null) {
			predicates.add(criteriaBuilder.like(
					criteriaBuilder.lower(root.get("jobName")), "%" + jobQuerySearch.getSearch().toLowerCase() + "%"));
		}
		
		if(jobQuerySearch.getTags() != null) {
			for(String tag : jobQuerySearch.toTagList()) {
				predicates.add(criteriaBuilder.equal(
	                    root.join("jobTag", JoinType.INNER).get("id"), tag));
			}
		}
		
		if(jobQuerySearch.getOrderBy() != null) {
			if(jobQuerySearch.getOrderType().equals("desc")) {
				 query.orderBy(criteriaBuilder.desc(root.get(jobQuerySearch.getOrderBy())));
			}else if(jobQuerySearch.getOrderType().equals("asc")){
				query.orderBy(criteriaBuilder.asc(root.get(jobQuerySearch.getOrderBy())));
			}
		}
		
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

	public JobQuerySearch getJobQuerySearch() {
		return jobQuerySearch;
	}

	public void setJobQuerySearch(JobQuerySearch jobQuerySearch) {
		this.jobQuerySearch = jobQuerySearch;
	}

}
