package com.coding.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.coding.challenge.controller.service.TicketService;

@RestController
public class TicketController {

	@Autowired
	TicketService ticketService;

	@GetMapping("/")
	public String home() {
		return "HOME";
	}

	@GetMapping("/getAllTickets")
	public String getAllTickets() {
		return ticketService.getAllTickets();
	}

	@GetMapping("/getNextOrPreviousTickets/{pagination}/{cursor}")
	public String getNextOrPreviousTickets(@PathVariable("pagination") String pagination,
			@PathVariable("cursor") String cursor) {
		return ticketService.getNextOrPreviousTickets(pagination, cursor);
	}
}
