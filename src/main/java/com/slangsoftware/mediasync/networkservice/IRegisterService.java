package com.slangsoftware.mediasync.networkservice;

import java.io.IOException;

public interface IRegisterService {
	public void register() throws IOException;
	public void unregister();
}
