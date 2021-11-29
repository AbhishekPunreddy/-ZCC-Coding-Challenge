package com.coding.challenge.controller.service;

import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.coding.challenge.model.PageTicketResponse;
import com.google.gson.Gson;

@Service
public class TicketServiceImpl implements TicketService {
	Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Value("${domain}")
	private String domain;

	@Value("${pagecount}")
	private String pagecount;

	@Value("${user}")
	private String username;

	@Value("${password}")
	private String password;

	@Override
	public String getAllTickets() {
		String url = domain + "?page[size]=" + pagecount;
		return getTicketDetails(url);
	}

	@Override
	public String getNextOrPreviousTickets(String pagination, String cursor) {
		String url = domain + "?page[size]=" + pagecount + "&page[" + pagination + "]=" + cursor;
		return getTicketDetails(url);
	}

	private String getTicketDetails(String url) {
		logger.debug("getTicketDetails url is " + url);
		HttpEntity<Object> request = new HttpEntity<Object>(createHeaders());
		ResponseEntity<String> response = null;
		HttpStatus status = null;
		Gson gson = new Gson();
		PageTicketResponse resp = new PageTicketResponse();
		try {
			response = new RestTemplate().exchange(url, HttpMethod.GET, request, String.class);
		} catch (Exception e) {
			logger.debug("Exception while fetching response from the server: ", e);
			e.printStackTrace();
		}
		if (response == null) {
			logger.debug("No response. Authorization Error");
			resp.setStatus(403);
			resp.setError("Authorization Error");
			return gson.toJson(resp);
		}
		status = response.getStatusCode();
		resp.setStatus(response.getStatusCode().value());
		if (status.is2xxSuccessful()) {
			String json = response.getBody();
			resp = gson.fromJson(json, PageTicketResponse.class);
		} else if (status.is4xxClientError()) {
			logger.debug("Client Error");
			resp.setError("CLIENT ERROR");
		} else if (status.is5xxServerError()) {
			logger.debug("Server Error");
			resp.setError("SERVER ERROR");
		}
		return gson.toJson(resp);
	}

	private HttpHeaders createHeaders() {
		HttpHeaders headers = new HttpHeaders();
		try {
			String authStr = username + ":" + password;
			String base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
			headers.add("Authorization", "Basic " + base64Creds);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return headers;
	}

}
