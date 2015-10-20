package com.generic.code;

import java.io.IOException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 * @throws FailingHttpStatusCodeException
	 * @throws IOException
	 * @throws InterruptedException
	 */

	public static void main(String[] args)
			throws FailingHttpStatusCodeException, Exception, IOException {

		VehicleDetailFetcher vehicleDetailFetcher = new VehicleDetailFetcherAndhraPradesh();
		Vehicle vObj = vehicleDetailFetcher.getVehicleDetailsFromService("AP");
		if (vObj != null)
			VehicleDAO.createAndStoreEvent(vObj);

		HibernateUtil.getSessionFactory().close();
	}

}
