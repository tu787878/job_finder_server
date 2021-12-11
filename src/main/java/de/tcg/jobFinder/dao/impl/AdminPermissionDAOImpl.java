package de.tcg.jobFinder.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

import de.tcg.jobFinder.dao.AdminPermissionDAO;
import de.tcg.jobFinder.model.admin.AdminAction;
import de.tcg.jobFinder.model.admin.AdminActionMethod;
import de.tcg.jobFinder.provider.FirebaseInitializer;

@Repository
public class AdminPermissionDAOImpl implements AdminPermissionDAO {

	@Autowired
	FirebaseInitializer db;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<AdminAction> getAdminActions()
			throws InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException {
		ApiFuture<QuerySnapshot> querySnapshot = db.getFireBase().collection("admin_actions").get();
		List<QueryDocumentSnapshot> actions = querySnapshot.get().getDocuments();
		List<AdminAction> results = new ArrayList<AdminAction>();

		for (DocumentSnapshot action : actions) {
			// AdminAction test = action.toObject(AdminAction.class);
			// System.out.println(test.toString());
			AdminAction a = new AdminAction(action.getData().get("name").toString(),
					action.getData().get("id").toString(), action.getData().get("path").toString(),
					action.getData().get("level").toString(), new ArrayList<AdminActionMethod>());
			ArrayList methodText = (ArrayList) action.getData().get("methods");
			List<AdminActionMethod> methods = new ArrayList<AdminActionMethod>();
			methodText.forEach(e -> {
				AdminActionMethod method = new AdminActionMethod(null, null, null);
				String str = e.toString().substring(1, e.toString().length() - 1);
				String fields[] = str.split(", ");
				for (String field : fields) {
					String xxx[] = field.split("=");
					if (xxx[0].equals("name")) {
						method.setName(xxx[1]);
					} else if (xxx[0].equals("id")) {
						method.setId(xxx[1]);
					} else if (xxx[0].equals("path")) {
						method.setPath(xxx[1]);
					}
				}
				methods.add(method);
			});
			a.setMethods(methods);
			results.add(a);
		}

		return results;
	}

}
