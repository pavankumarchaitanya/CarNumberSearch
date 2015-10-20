package com.generic.code;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.google.gson.Gson;

public class VehicleDetailFetcherAndhraPradesh extends VehicleDetailFetcher {

	/**
	 * @param args
	 * @throws IOException
	 * @throws MalformedURLException
	 * @throws FailingHttpStatusCodeException
	 * @throws InterruptedException
	 */
	public static void main(String[] args)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException, InterruptedException {
		// TODO Auto-generated method stub
		VehicleDetailFetcherAndhraPradesh v = new VehicleDetailFetcherAndhraPradesh();
		System.out.println(v.getVehicleDetailsFromService("AP13R5977")
				.getOwnerName());
	}

	@Override
	public Vehicle getVehicleDetailsFromService(String RegNumber)
			throws FailingHttpStatusCodeException, MalformedURLException,
			IOException {
		// TODO Auto-generated method stub
		final WebClient webClient = new WebClient();

		// Get the first page
		// https://aptransport.in/APCFSTONLINE/Reports/VehicleRegistrationSearch.aspx
		final HtmlPage page1 = webClient
				.getPage("https://aptransport.in/APCFSTONLINE/Reports/VehicleRegistrationSearch.aspx");

		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		final HtmlForm form = page1.getFormByName("aspnetForm");

		final HtmlSubmitInput button = form
				.getInputByName("ctl00$OnlineContent$btnGetData");
		final HtmlTextInput textField = form
				.getInputByName("ctl00$OnlineContent$txtInput");
		// Change the value of the text field
		textField.setValueAttribute(RegNumber);
		// Now submit the form by clicking the button and get back the second
		// page.
		final HtmlPage page2 = button.click();
		page2.asText();
		Vehicle vehicleObj = new Vehicle();
		try {
			Thread.sleep(1000);

			processHTML(page2.asText(), vehicleObj);

			/*
			 * 
			 * vehicleObj.setOwnerName(((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdOwner")).asText());
			 * vehicleObj.setChasisNo
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdChassisno")).asText());
			 * vehicleObj.setRegistrationNumber
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdRegnNo")).asText());
			 * vehicleObj.setVehicleClass
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdVehClass")).asText());
			 * vehicleObj.setMfgYear
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdMfgYear")).asText());
			 * vehicleObj.setEngineNo
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdEngNo")).asText());
			 * vehicleObj.setPrevRegn
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdPrevRegn")).asText());
			 * vehicleObj.setFuelType
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdFuelType")).asText());
			 * vehicleObj.setVehicleColor
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdColor")).asText());
			 * vehicleObj.setMakersName
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdMkrName")).asText());
			 * vehicleObj.setMakersClass
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdMkrClass")).asText());
			 * vehicleObj.setDateofRegistration
			 * (((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdDOR")).asText());
			 * vehicleObj.setFinancier(
			 * ((HtmlTableDataCell)page2.getHtmlElementById
			 * ("ctl00_OnlineContent_tdFin")).asText());
			 */
		} catch (ElementNotFoundException e) {

			vehicleObj = null;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		
		System.out.println(gson.toJson(vehicleObj));
		return vehicleObj;

	}

	private void processHTML(String asText, Vehicle vehicleObj) {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(asText, "\n");
		ArrayList<String> list = new ArrayList<String>();
		while (st.hasMoreElements()) {

			list.add(st.nextElement().toString());
		}
		int count = 0;
		int startIndex = -1;
		int EndIndex = -1;
		for (String s : list) {
			count++;
			if (s.startsWith("Registration No")) {
				startIndex = count - 1;

			}
			if (s.contains("Status:	")) {
				EndIndex = count - 1;

			}

		}

		ArrayList<String> reqList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (i >= startIndex && i <= EndIndex) {
				reqList.add(list.get(i));
			}
		}
		//System.out.println(reqList);
		ArrayList<String> vehicleDetailsList = new ArrayList<String>();
		for (String str : reqList) {
			/*
			 * if(str.contains("Registration No")) { String fuelType =
			 * str.substring(str.indexOf("Fuel Type:"));
			 * 
			 * fuelType= fuelType.replace("Fuel Type:", "").trim();
			 * 
			 * 
			 * } if(str.contains("Owner Name:")) { String vehicleColor =
			 * str.substring(str.indexOf("Vehicle Color:"));
			 * 
			 * vehicleColor= vehicleColor.replace("Vehicle Color:", "").trim();
			 * 
			 * 
			 * } if(str.contains("Registration No:")) { String makersName =
			 * str.substring(str.indexOf("Maker's Name:"));
			 * 
			 * makersName= makersName.replace("Fuel Type:", "").trim();
			 * 
			 * 
			 * } if(str.contains("Registration No")) { String fuelType =
			 * str.substring(str.indexOf("Fuel Type:"));
			 * 
			 * fuelType= fuelType.replace("Fuel Type:", "").trim();
			 * 
			 * 
			 * } if(str.contains("Registration No")) { String fuelType =
			 * str.substring(str.indexOf("Fuel Type:"));
			 * 
			 * fuelType= fuelType.replace("Fuel Type:", "").trim();
			 * 
			 * 
			 * }
			 */
			String s[] = str.split("\t");

			vehicleDetailsList.add(s[1].replaceAll("(\\r|\\n)", ""));
			vehicleDetailsList.add(s[3].replaceAll("(\\r|\\n)", ""));

		}
		
		vehicleObj.setRegistrationNumber(vehicleDetailsList.get(0));
		vehicleObj.setFuelType(vehicleDetailsList.get(1));
		vehicleObj.setOwnerName(vehicleDetailsList.get(2));
		vehicleObj.setVehicleColor(vehicleDetailsList.get(3));
		vehicleObj.setVehicleClass(vehicleDetailsList.get(4));
		vehicleObj.setMakersName(vehicleDetailsList.get(5));
		vehicleObj.setDateofRegistration(vehicleDetailsList.get(9));
		vehicleObj.setMfgYear(vehicleDetailsList.get(7));
		vehicleObj.setMakersClass(vehicleDetailsList.get(7));
		vehicleObj.setEngineNo(vehicleDetailsList.get(9));
		vehicleObj.setChasisNo(vehicleDetailsList.get(10));
		vehicleObj.setDateofRegistration(vehicleDetailsList.get(11));
		vehicleObj.setPrevRegn(vehicleDetailsList.get(12));
		vehicleObj.setFinancier(vehicleDetailsList.get(11));
		
		
	//	vehicleObj.setVehicleClass(vehicleDetailsList.get(3));
	//	vehicleObj.setVehicleClass(vehicleDetailsList.get(3));
	//	vehicleObj.setVehicleClass(vehicleDetailsList.get(3));

	}

}
