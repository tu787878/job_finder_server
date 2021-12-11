package de.tcg.jobFinder.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.WebMenuApi;
import de.tcg.jobFinder.dto.SuccessResponse;
import de.tcg.jobFinder.entity.WebMenu;
import de.tcg.jobFinder.service.WebMenuService;

@RestController
@RequestMapping("/api/menu/web")
public class WebMenuApiImpl implements WebMenuApi {
	
	@Autowired
	WebMenuService webMenuService;

	@Override
	public ResponseEntity<?> getVisibleMenu(String lang, String position) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		List<WebMenu> webMenus = webMenuService.getVisibleMenu();
		data.put("menus", webMenus);
		
		return ResponseEntity.ok(new SuccessResponse(0, "success", data));
	}

}
