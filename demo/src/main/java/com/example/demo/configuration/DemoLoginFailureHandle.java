package com.example.demo.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DemoLoginFailureHandle implements AuthenticationFailureHandler{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		ObjectMapper objectMapper = new ObjectMapper();
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		JsonGenerator jsonGenerator = objectMapper.getJsonFactory()
				.createJsonGenerator(response.getOutputStream(), JsonEncoding.UTF8);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		try {
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", false);
			result.put("message", "Incorrect ID or Password.");
			objectMapper.writeValue(jsonGenerator, result);

		} catch (JsonProcessingException ex) {
			throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
		}
		
	}

}
