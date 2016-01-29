package com.slangsoftware.mediasync.networkservice;

public interface IRegisterServiceInfo {
	public String getServiceType();
	public String getServiceName();
	public String getServiceDescription();
	public int getPort();
}