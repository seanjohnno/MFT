package com.slangsoftware.mediasync;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.slangsoftware.mediasync.networkservice.IRegisterService;
import com.slangsoftware.mediasync.networkservice.IRegisterServiceInfo;
import com.slangsoftware.mediasync.networkservice.impl.RegisterService;
import com.slangsoftware.mediasync.networkservice.impl.RegisterServiceInfo;
import com.slangsoftware.mediasync.spark.api.DeleteFile;
import com.slangsoftware.mediasync.spark.api.GetFiles;
import com.slangsoftware.mediasync.spark.api.PostStop;
import com.slangsoftware.mediasync.spark.api.PutFile;
import com.slangsoftware.mediasync.spark.api.model.FileModel;
import com.slangsoftware.mediasync.spark.api.model.IFileModel;

import spark.Route;

public class GuiceModuleRegister extends AbstractModule {

	private static final String Base_Loc = "C:\\Users\\MrLenovo\\Development\\Test\\MediaSync\\";
	private static IFileModel _singleton;
	
	@Override
	protected void configure() {
		bind(IRegisterService.class).to(RegisterService.class);
		bind(IRegisterServiceInfo.class).to(RegisterServiceInfo.class);
			
		bind(Route.class).annotatedWith(Names.named("DeleteFile")).to(DeleteFile.class);
		bind(Route.class).annotatedWith(Names.named("GetFiles")).to(GetFiles.class);
		bind(Route.class).annotatedWith(Names.named("PostStop")).to(PostStop.class);
		bind(Route.class).annotatedWith(Names.named("PutFile")).to(PutFile.class);
		
		bind(IFileModel.class).toProvider(() -> {
			if(_singleton == null) {
				_singleton = new FileModel(Base_Loc);
			}
			return _singleton;
		});
	}
}
