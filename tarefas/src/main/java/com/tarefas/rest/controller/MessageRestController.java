package com.tarefas.rest.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.rest.domain.Message;

@RestController
public class MessageRestController {

	@RequestMapping("/rest/message")
	public String messageHome(){
		return "Request RestController message";
	}
	
	@RequestMapping(value="/rest/message/{player}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public Message message(@PathVariable String player){
		//Endpoint
		Message message = new Message(player, "Message: " + player);
		return message;
	}
	
}
