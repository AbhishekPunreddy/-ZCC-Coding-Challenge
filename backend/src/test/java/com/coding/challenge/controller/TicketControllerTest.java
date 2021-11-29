package com.coding.challenge.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.coding.challenge.controller.service.TicketService;
import com.coding.challenge.model.PageTicketResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TicketController.class)
public class TicketControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TicketService ticketService;

	@Test
	void testGetAllTickets() throws Exception {
		String json = "{\r\n" + "  \"tickets\": [\r\n" + "    {\r\n"
				+ "      \"url\": \"https://zccabhishekreddy.zendesk.com/api/v2/tickets/1.json\",\r\n"
				+ "      \"id\": 1,\r\n" + "      \"created_at\": \"2021-11-24T22:26:28Z\",\r\n"
				+ "      \"type\": \"incident\",\r\n" + "      \"subject\": \"Sample ticket: Meet the ticket\",\r\n"
				+ "      \"description\": \"Hi there,\\n\\nI am sending an email because I am having a problem setting up your new product. Can you help me troubleshoot?\\n\\nThanks,\\n The Customer\\n\\n\",\r\n"
				+ "      \"priority\": \"normal\",\r\n" + "      \"status\": \"open\",\r\n"
				+ "      \"requester_id\": 1267104573729,\r\n" + "      \"submitter_id\": 1267109606570,\r\n"
				+ "      \"assignee_id\": 1267109606570,\r\n" + "      \"organization_id\": null,\r\n"
				+ "      \"group_id\": 4411136781595\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"url\": \"https://zccabhishekreddy.zendesk.com/api/v2/tickets/2.json\",\r\n"
				+ "      \"id\": 2,\r\n" + "      \"created_at\": \"2021-11-24T23:52:57Z\",\r\n"
				+ "      \"type\": null,\r\n"
				+ "      \"subject\": \"velit eiusmod reprehenderit officia cupidatat\",\r\n"
				+ "      \"description\": \"Aute ex sunt culpa ex ea esse sint cupidatat aliqua ex consequat sit reprehenderit. Velit labore proident quis culpa ad duis adipisicing laboris voluptate velit incididunt minim consequat nulla. Laboris adipisicing reprehenderit minim tempor officia ullamco occaecat ut laborum.\\n\\nAliquip velit adipisicing exercitation irure aliqua qui. Commodo eu laborum cillum nostrud eu. Mollit duis qui non ea deserunt est est et officia ut excepteur Lorem pariatur deserunt.\",\r\n"
				+ "      \"priority\": null,\r\n" + "      \"status\": \"open\",\r\n"
				+ "      \"requester_id\": 1267109606570,\r\n" + "      \"submitter_id\": 1267109606570,\r\n"
				+ "      \"assignee_id\": 1267109606570,\r\n" + "      \"organization_id\": 1260918747109,\r\n"
				+ "      \"group_id\": 4411136781595\r\n" + "    }\r\n" + "  ],\r\n" + "  \"meta\": {\r\n"
				+ "    \"has_more\": true,\r\n"
				+ "    \"after_cursor\": \"eyJvIjoibmljZV9pZCIsInYiOiJhUUlBQUFBQUFBQUEifQ==\",\r\n"
				+ "    \"before_cursor\": \"eyJvIjoibmljZV9pZCIsInYiOiJhUUVBQUFBQUFBQUEifQ==\"\r\n" + "  },\r\n"
				+ "  \"links\": {\r\n"
				+ "    \"prev\": \"https://zccabhishekreddy.zendesk.com/api/v2/tickets?page%5Bbefore%5D=eyJvIjoibmljZV9pZCIsInYiOiJhUUVBQUFBQUFBQUEifQ%3D%3D&page%5Bsize%5D=2\",\r\n"
				+ "    \"next\": \"https://zccabhishekreddy.zendesk.com/api/v2/tickets?page%5Bafter%5D=eyJvIjoibmljZV9pZCIsInYiOiJhUUlBQUFBQUFBQUEifQ%3D%3D&page%5Bsize%5D=2\"\r\n"
				+ "  }\r\n" + "}";
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		PageTicketResponse resp = gson.fromJson(json, PageTicketResponse.class);
		resp.setStatus(200);
		String url = "/getAllTickets";
		String response = gson.toJson(resp);
		Mockito.when(ticketService.getAllTickets()).thenReturn(response);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(url)
				.accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String output = result.getResponse().getContentAsString();
		assertThat(output).isEqualTo(response);
	}

}
