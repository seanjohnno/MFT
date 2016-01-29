package com.slangsoftware.mediasync.spark.api.model;

import java.io.File;

import com.google.inject.Inject;

public class FileModel implements IFileModel {

	public File _basePath;
	
	@Inject
	public FileModel(String loc) {
		_basePath = new File(loc);
	}
	
	public File getPath() {
		return _basePath;
	}
}
