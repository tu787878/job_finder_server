package de.tcg.jobFinder.dao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.tcg.jobFinder.model.admin.AdminAction;

public interface AdminPermissionDAO {

	public List<AdminAction> getAdminActions() throws InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException;
	
}
