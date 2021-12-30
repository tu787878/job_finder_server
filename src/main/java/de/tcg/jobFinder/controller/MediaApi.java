package de.tcg.jobFinder.controller;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import de.tcg.jobFinder.dto.PathId;

@RequestMapping("/media")
public interface MediaApi {

	@GetMapping(value = "/image/{pathId}/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<?> getImage(@PathVariable(name ="pathId") PathId pathId, @PathVariable(name ="fileName") String fileName);

	@GetMapping(value = "/test", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<?> testMedia() throws IOException, MessagingException;
}
