package com.generic.code;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(namespace = "APRTA")
public class VehicleList {
	  @XmlElementWrapper(name = "VehicleList")
	  
	  @XmlElement(name = "Vehicle")
	  public ArrayList<Vehicle> vList = new ArrayList<Vehicle>();
	  private String name;
	  private String location;
	  
	  
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	  
	  public void addVehicleToList(Vehicle vObj)
	  {
		  this.vList.add(vObj);
	  }

}
