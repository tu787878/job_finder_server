package de.tcg.jobFinder.dao;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.tcg.jobFinder.model.web.Menu;

public interface MenuDAO {

	public List<Menu> getActiveMenu() throws InterruptedException, ExecutionException;
	
}
