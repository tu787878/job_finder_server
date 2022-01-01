package de.tcg.jobFinder.controller.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.tcg.jobFinder.controller.MediaApi;
import de.tcg.jobFinder.dto.PathId;
import de.tcg.jobFinder.exception.ApiRequestException;
import de.tcg.jobFinder.service.impl.EmailServiceImpl;

@RestController
@RequestMapping("/media")
public class MediaApiImpl implements MediaApi {

	@Value("${media.path}")
	String basisPath;
	
	@Autowired
	EmailServiceImpl emailServiceImpl;

	@Override
	public ResponseEntity<?> getImage(PathId pathId, String fileName) {

		String pathString = basisPath + "/image" + pathId.getPath();

		File file = new File(pathString + "/" + fileName);
		byte[] bytes;
		try {
			bytes = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			System.out.println(e);
			throw new ApiRequestException("No such file in server");
		}

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
	}

	@Override
	public ResponseEntity<?> testMedia() throws IOException, MessagingException {
		String pathString = basisPath + "/image" + PathId.sample.getPath();

		System.out.println(pathString + "/test.png");
		System.out.println("ok");

		File file = new File(pathString + "/test.png");
		byte[] bytes = Files.readAllBytes(file.toPath());
		
		emailServiceImpl.sendMail(null, "ApplyJobToBusiness");

		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
	}

}
