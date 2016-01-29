package com.slangsoftware.mediasync.spark.api;

import java.io.File;

import com.google.inject.Inject;
import com.slangsoftware.mediasync.spark.api.model.IFileModel;

import spark.Request;
import spark.Response;
import spark.Route;

public class PutFile implements Route {

	public static final String FILENAME = "filename";
	
	private IFileModel _fm;
	
	@Inject
	PutFile(IFileModel fm) {
		_fm = fm;
	}
	
	@Override
	public Object handle(Request request, Response response) throws Exception {
		String filename = request.queryParams(FILENAME);
		File fullPath = new File(_fm.getPath(), filename);
		return fullPath.createNewFile();
	}
}
