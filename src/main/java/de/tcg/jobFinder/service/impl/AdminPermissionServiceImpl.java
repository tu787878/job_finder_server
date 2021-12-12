package de.tcg.jobFinder.service.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import de.tcg.jobFinder.dao.AdminPermissionDAO;
import de.tcg.jobFinder.model.admin.AdminAction;
import de.tcg.jobFinder.model.admin.AdminActionMethod;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;
import de.tcg.jobFinder.service.AdminPermissionService;

@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

	@Autowired
	private AdminPermissionDAO adminPermissionDAO;
	
	@Override
	public List<AdminAction> getAdminActionsByAdminAccount(List<String> permission) throws InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException {
		List<AdminAction> adminActions = adminPermissionDAO.getAdminActions();
		return filterPermission(permission, adminActions);
	}

	private List<AdminAction> filterPermission(List<String> permission, List<AdminAction> actions) {
		
		return null;
	}
	


}
