package com.generic.code;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public abstract class VehicleDetailFetcher {

	public abstract Vehicle getVehicleDetailsFromService(String RegNumber)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException;

}
