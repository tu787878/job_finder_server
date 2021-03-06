package de.tcg.jobFinder.provider;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

@Service
public class FirebaseInitializer {

	@PostConstruct
	private void init() throws IOException {

		InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase/firebase_service.json");

		FirebaseOptions options;
		options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();
		if (FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}

	}

	public Firestore getFireBase() {
		return FirestoreClient.getFirestore();
	}

}