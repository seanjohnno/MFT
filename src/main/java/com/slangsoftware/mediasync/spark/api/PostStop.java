package com.slangsoftware.mediasync.spark.api;

import com.google.inject.Inject;
import com.slangsoftware.mediasync.networkservice.IRegisterService;

import spark.Request;
import spark.Response;
import spark.Route;
import static spark.Spark.*;

public class PostStop implements Route {

	private IRegisterService _service;
	
	@Inject
	PostStop(IRegisterService registerService) {
		_service = registerService;
	}
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		
		// Turn off after a few seconds
		_service.unregister();
		stop();
		
		return "";
	}
}
