package de.tcg.jobFinder.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import de.tcg.jobFinder.dao.MenuDAO;
import de.tcg.jobFinder.model.web.Menu;
import de.tcg.jobFinder.provider.FirebaseInitializer;

@Repository
public class MenuDAOImpl implements MenuDAO {

	@Autowired
	FirebaseInitializer db;
	
	@Override
	public List<Menu> getActiveMenu() throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> querySnapshot = db.getFireBase().collection("menu")
				.whereEqualTo("visible", true).get();
		
		List<Menu> menu = new ArrayList<Menu>();
		if (!querySnapshot.get().getDocuments().isEmpty()) {
			
			List<QueryDocumentSnapshot> snapshots = querySnapshot.get().getDocuments();
			snapshots.forEach(e -> {
				menu.add(Menu.firebaseToMenu(e));
			});
		}
		
		return menu;
	}

}
