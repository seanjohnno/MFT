package com.slangsoftware.mediasync.spark.api;

import java.io.File;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.inject.Inject;
import com.slangsoftware.mediasync.spark.api.model.IFileModel;

import rx.Observable;
import spark.Request;
import spark.Response;
import spark.Route;

public class GetFiles implements Route {

	private IFileModel _fm;
	
	@Inject
	GetFiles(IFileModel fm) {
		_fm = fm;
	}
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		JsonArray ja = new JsonArray();
		Observable.from(_fm.getPath().list())
		.map((s) -> {
			return new File(_fm.getPath(), s);
		})
		.subscribe((s) -> {
			JsonObject jo = new JsonObject();
			jo.addProperty("filename", s.getName());
			jo.addProperty("is_file", s.isFile());
			ja.add(jo);
		});
		
		response.header("content-type", "application/json");
		return ja.toString();
	}
}
