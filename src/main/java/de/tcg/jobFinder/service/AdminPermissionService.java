package de.tcg.jobFinder.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.tcg.jobFinder.model.admin.AdminAction;

public interface AdminPermissionService {

	public List<AdminAction> getAdminActionsByAdminAccount(List<String> permission) throws InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException;
	
}
