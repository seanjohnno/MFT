package com.slangsoftware.mediasync.networkservice.impl;

import com.slangsoftware.mediasync.networkservice.IRegisterServiceInfo;

public class RegisterServiceInfo implements IRegisterServiceInfo {
	
	public RegisterServiceInfo() {
	}
	
	public String getServiceName() {
		return "Media Sync Service";
	}
	
	public String getServiceType() {
		return "_http._tcp.local.";
	}
	
	public String getServiceDescription() {
		return "Temporary service description";
	}
	
	public int getPort() {
		return 6789;
	}
}
