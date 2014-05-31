import java.io.IOException;
import java.net.MalformedURLException;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTableDataCell;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


@Entity
public class Vehicle {
	@Id
    private String RegistrationNumber;//  - ctl00_ContentPlaceHolder1_TxtregnNo -
    private String OwnerName;// - ctl00_ContentPlaceHolder1_lblowner
    private String  VehicleClass;// - ctl00_ContentPlaceHolder1_lblvehclass
    private String      MfgYear;// - ctl00_ContentPlaceHolder1_lblmfg
    private String          EngineNo;// - ctl00_ContentPlaceHolder1_lblengno
    private String              ChasisNo;// - ctl00_ContentPlaceHolder1_lblchasisno
    private String                  PrevRegn;// No - ctl00_ContentPlaceHolder1_Label2
    private String                            FuelType;// - ctl00_ContentPlaceHolder1_lblfuel
    private String                              VehicleColor;//     - ctl00_ContentPlaceHolder1_lblvehclr
    private String                                 MakersName;// -  ctl00_ContentPlaceHolder1_lblmkrnm
    private String                                 MakersClass;// - ctl00_ContentPlaceHolder1_lblmkrcls
    private String                                 DateofRegistration;// - ctl00_ContentPlaceHolder1_lblregdt
    private String                                 Financier;// - ctl00_ContentPlaceHolder1_lblfinnm
   
   
    public String getRegistrationNumber() {
        return RegistrationNumber;
    }
    public void setRegistrationNumber(String registrationNumber) {
        RegistrationNumber = registrationNumber;
    }
    public String getOwnerName() {
        return OwnerName;
    }
    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }
    public String getVehicleClass() {
        return VehicleClass;
    }
    public void setVehicleClass(String vehicleClass) {
        VehicleClass = vehicleClass;
    }
    public String getMfgYear() {
        return MfgYear;
    }
    public void setMfgYear(String mfgYear) {
        MfgYear = mfgYear;
    }
    public String getEngineNo() {
        return EngineNo;
    }
    public void setEngineNo(String engineNo) {
        EngineNo = engineNo;
    }
    public String getChasisNo() {
        return ChasisNo;
    }
    public void setChasisNo(String chasisNo) {
        ChasisNo = chasisNo;
    }
    public String getPrevRegn() {
        return PrevRegn;
    }
    public void setPrevRegn(String prevRegn) {
        PrevRegn = prevRegn;
    }
    public String getFuelType() {
        return FuelType;
    }
    public void setFuelType(String fuelType) {
        FuelType = fuelType;
    }
    public String getVehicleColor() {
        return VehicleColor;
    }
    public void setVehicleColor(String vehicleColor) {
        VehicleColor = vehicleColor;
    }
    public String getMakersName() {
        return MakersName;
    }
    public void setMakersName(String makersName) {
        MakersName = makersName;
    }
    public String getMakersClass() {
        return MakersClass;
    }
    public void setMakersClass(String makersClass) {
        MakersClass = makersClass;
    }
    public String getDateofRegistration() {
        return DateofRegistration;
    }
    public void setDateofRegistration(String dateofRegistration) {
        DateofRegistration = dateofRegistration;
    }
    public String getFinancier() {
        return Financier;
    }
    public void setFinancier(String financier) {
        Financier = financier;
    }

@Deprecated
    public static Vehicle getVehicleDetails(String RegNumber) throws IOException
    {
        final String AP_RTA_URL = "https://aptransport.in/CFSTONLINE/Reports/VehicleRegistrationSearch.aspx";//"http://202.65.142.140/CFSTONLINE/Reports/VehicleRegistrationSearch.aspx";
        String InitialHTML = Util.httpsPost(AP_RTA_URL, "") ;//Util.doHttpsPost(AP_RTA_URL, ""); // Util.doPost(AP_RTA_URL,"");
        
//        String eventValidation = getValueOfAttribute("__EVENTVALIDATION","value",InitialHTML);
        String ViewState= getValueOfAttribute("__VIEWSTATE","value",InitialHTML);
        String RegistrationNumber = RegNumber;
//        String EMPTYSTRING = "" ;
        // "__VIEWSTATE=" + ViewState + "&"+ "ctl00%24ContentPlaceHolder1%24txtreg="+ RegistrationNumber + "&" + "ctl00%24ContentPlaceHolder1%24txteng=" +EMPTYSTRING+ "&" + "ctl00%24ContentPlaceHolder1%24txtchasis=" +EMPTYSTRING+ "&"+"ctl00%24ContentPlaceHolder1%24txttran="+EMPTYSTRING+ "&"+"ctl00%24ContentPlaceHolder1%24Button1="+"GetData" + "&"+"__EVENTVALIDATION=" +eventValidation;
        String inputParamString ="ctl00$OnlineContent$ScriptManager1=ctl00$OnlineContent$Updatepanel1|ctl00$OnlineContent$btnGetData&__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE="+ViewState +"&ctl00$OnlineContent$ddlInput=R&ctl00$OnlineContent$txtInput="+RegistrationNumber + "&ctl00$OnlineContent$btnGetData=Get%20Data";
        String FinalHTML = Util.httpsPost(AP_RTA_URL, inputParamString) ;//Util.doHttpsPost(AP_RTA_URL, inputParamString);//Util.doPost(AP_RTA_URL, inputParamString);
    //    System.out.println(InitialHTML);
    System.out.println("****************************************");
        Vehicle vehicleObj = new Vehicle();
        vehicleObj.setChasisNo(getValueInsideSpan("ctl00_OnlineContent_tdChassisno",FinalHTML));
        vehicleObj.setRegistrationNumber(getValueInsideSpan("ctl00_OnlineContent_tdRegnNo",FinalHTML));
        vehicleObj.setOwnerName(getValueInsideSpan("ctl00_OnlineContent_tdOwner",FinalHTML));
        vehicleObj.setVehicleClass(getValueInsideSpan("ctl00_OnlineContent_tdVehClass",FinalHTML));
        vehicleObj.setMfgYear(getValueInsideSpan("ctl00_OnlineContent_tdMfgYear",FinalHTML));
        vehicleObj.setEngineNo(getValueInsideSpan("ctl00_OnlineContent_tdEngNo",FinalHTML));
        vehicleObj.setPrevRegn(getValueInsideSpan("ctl00_OnlineContent_tdPrevRegn",FinalHTML));
        vehicleObj.setFuelType(getValueInsideSpan("ctl00_OnlineContent_tdFuelType",FinalHTML));
        vehicleObj.setVehicleColor(getValueInsideSpan("ctl00_OnlineContent_tdColor",FinalHTML));
        vehicleObj.setMakersName(getValueInsideSpan("ctl00_OnlineContent_tdMkrName",FinalHTML));
        vehicleObj.setMakersClass(getValueInsideSpan("ctl00_OnlineContent_tdMkrClass",FinalHTML));
        vehicleObj.setDateofRegistration(getValueInsideSpan("ctl00_OnlineContent_tdDOR",FinalHTML));
        vehicleObj.setFinancier(getValueInsideSpan("ctl00_OnlineContent_tdFin",FinalHTML));
       
        return vehicleObj;
    }

@Deprecated
    private static  String getValueOfAttribute(String HTMLElementID, String AttributeName,String StringToLookInto )
    {
        //System.out.println("inside getValueOfAttribute");

        int tempIndex = 0;

        String lines[] = StringToLookInto.split("\\r?\\n");
        for(String line:lines )
        {
            if(line.contains(HTMLElementID))
            {
                tempIndex = line.indexOf("value=\"");
                //    System.out.println(tempIndex);
                String Values[] = line.substring(tempIndex, line.length()).split("\"", 3);
                return Values[1];
            }

        }

        return "";
    }
@Deprecated    
private static  String getValueInsideSpan(String HTMLElementID, String StringToLookInto )
    {
        //System.out.println("inside getValueOfAttribute");

        int tempStartIndex = 0,endIndex = 0;   
        String lines[] = StringToLookInto.split("\\r?\\n");
        for(String line:lines )
        {
            if(line.contains(HTMLElementID))
            {
                tempStartIndex = line.indexOf(">",line.indexOf(HTMLElementID));
                endIndex = line.indexOf("<",tempStartIndex);
                //    System.out.println(tempIndex);

                return line.substring(tempStartIndex+1, endIndex);
            }

        }

        return "";
    }


}