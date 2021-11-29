package com.coding.challenge.controller.service;

public interface TicketService {

	public String getAllTickets();

	public String getNextOrPreviousTickets(String pagination, String cursor);
}
