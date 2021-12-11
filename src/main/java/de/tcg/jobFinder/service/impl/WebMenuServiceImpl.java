package de.tcg.jobFinder.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.tcg.jobFinder.entity.WebMenu;
import de.tcg.jobFinder.reposity.WebMenuReposity;
import de.tcg.jobFinder.service.WebMenuService;

@Service
public class WebMenuServiceImpl implements WebMenuService {

	@Autowired
	WebMenuReposity menuReposity;

	@Override
	public List<WebMenu> getVisibleMenu() {
		return menuReposity.findByVisible(true);
	}

}
