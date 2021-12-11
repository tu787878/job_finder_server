package de.tcg.jobFinder.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import de.tcg.jobFinder.dao.MyUserDetailsServiceDAO;
import de.tcg.jobFinder.model.admin.AuthorizationAccount;
import de.tcg.jobFinder.model.admin.Role;
import de.tcg.jobFinder.provider.FirebaseInitializer;

@Repository
public class MyUserDetailsServiceDAOImpl implements MyUserDetailsServiceDAO {

	@Autowired
	FirebaseInitializer db;

	@Override
	public AuthorizationAccount loadUserByUsername(String username) throws InterruptedException, ExecutionException {
		ApiFuture<QuerySnapshot> querySnapshot = db.getFireBase().collection("employee_account")
				.whereEqualTo("username", username).get();

		if (!querySnapshot.get().getDocuments().isEmpty()) {
			DocumentSnapshot snapshot = querySnapshot.get().getDocuments().get(0);
			List<Role> roles = new ArrayList<Role>();
			List<String> authorities = (List<String>) snapshot.get("authorities");
			roles.addAll(authorities.stream().map(e -> {
				return new Role(e);
			}).toList());
			List<String> permissions = (List<String>) snapshot.get("permission");

			return new AuthorizationAccount(snapshot.getData().get("username").toString(),
					snapshot.getData().get("password").toString(), "", roles, permissions);
		}
		
		return null;
	}

}
