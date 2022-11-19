package de.tcg.jobFinder.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import de.tcg.jobFinder.dto.SuccessResponse;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	public @Override void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
			throws IOException {

//	    String message = RestResponse.builder()
//	        .status(UNAUTHORIZED)
//	        .error("Unauthenticated")
//	        .message("Insufficient authentication details")
//	        .path(request.getRequestURI())
//	        .json();

		String jsonString = new JSONObject(new SuccessResponse(1, "Token invalid or expired!", null)).toString();

		response.setStatus(500);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(jsonString);
	}
}