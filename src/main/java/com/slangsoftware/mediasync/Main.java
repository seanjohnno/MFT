package com.slangsoftware.mediasync;

import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.stop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Named;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.slangsoftware.mediasync.networkservice.IRegisterService;
import com.slangsoftware.mediasync.spark.api.DeleteFile;
import com.slangsoftware.mediasync.spark.api.GetFiles;
import com.slangsoftware.mediasync.spark.api.PostStop;
import com.slangsoftware.mediasync.spark.api.PutFile;

import rx.Observable;
import rx.schedulers.Schedulers;
import spark.Route;

public class Main {

	public static void main(String[] args) throws IOException {
		(new Main()).start();
	}
	
	public Main() {
		
	}
	
	public void start() throws IOException {
		Injector injector = Guice.createInjector(new GuiceModuleRegister());
		final IRegisterService service = injector.getInstance(IRegisterService.class);
				
		Observable.create((s) -> {
			try {
				service.register();
				s.onCompleted();
			} catch(Exception e) {
				s.onError(null);
			}
		} )
		.subscribeOn(Schedulers.io())
		.subscribe(
				// onNext
				(s) -> {
				},
				// onError
				(Throwable e) -> {
					System.out.println("Unable to register network service... ");
					e.printStackTrace();
				},
				// onComplete
				() -> {
					port(6789);
					
					@Named("getfiles") Route getfiles = injector.getInstance(GetFiles.class);
					get("/getfiles",  getfiles);
					
					@Named("deletefile") Route deletefile = injector.getInstance(DeleteFile.class);
					delete("/deletefile", deletefile);
					
					@Named("putfile") Route putfile = injector.getInstance(PutFile.class);
					put("/putfile", putfile);
					
					@Named("poststop") Route postStop = injector.getInstance(PostStop.class);
					post("/stop", postStop);
				}
		);
		
		System.out.println("Enter newline to quit...");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		
		service.unregister();
		stop();
		System.exit(0);
	}
}
