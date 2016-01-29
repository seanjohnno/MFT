package com.slangsoftware.mediasync.networkservice.impl;

import java.io.IOException;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.slangsoftware.mediasync.networkservice.IRegisterService;

@Singleton
public class RegisterService implements IRegisterService {
	
	private JmDNS _jmdns;
	
	private ServiceInfo _serviceInfo;
	
	private RegisterServiceInfo _rsi;
	
	@Inject
	public RegisterService(RegisterServiceInfo rsi) {
		_rsi = rsi;
	}
	
	public void register() throws IOException {
		_jmdns = JmDNS.create();
		_serviceInfo = ServiceInfo.create(_rsi.getServiceType(), _rsi.getServiceName(), _rsi.getPort(), _rsi.getServiceDescription());
		_jmdns.registerService(_serviceInfo);
	}
	
	public void unregister() {
		_jmdns.unregisterService(_serviceInfo);
	}
}
