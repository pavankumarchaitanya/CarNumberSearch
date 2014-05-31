import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.omg.CORBA.NameValuePair;




public class Util {
    public static HashSet<Integer> getNumbersBelowNAddingUpToNine(int N)
    {
        HashSet<Integer> numSet = new HashSet<Integer>();
        for(int i =1; i <= N; i++)
        {
            if (i % 9 == 0)
            {
                numSet.add(i);
            }
        }
        return numSet;
    }

   
    public static    HashSet<String> getSpecialRegistrationNumbers(String series){
       
       
        HashSet<String> RegistrationNumbersSet = getRegistrationNumbersAddingTo9( series);
       
       
        //HashSet<Integer> numset = new HashSet<Integer>();
       
        for(int i=1;i<=100;i++)
        {
            if(i<10)
            {
                RegistrationNumbersSet.add(series+"000"+i);
           
            }else if (i<100)
            {
                RegistrationNumbersSet.add(series+"00"+i);
            }
           
        }
       
       
        return RegistrationNumbersSet;
    }
   
   
   
public static    HashSet<String> getRegistrationNumbersAddingTo9(String series)
    {
        HashSet<Integer> numset = getNumbersBelowNAddingUpToNine(9999);
        HashSet<String> RegistrationNumbersSet = new HashSet<String>();
        for (Integer i : numset)
        {
           
               
                if(i<10)
                {
                    RegistrationNumbersSet.add(series+"000"+i);
               
                }else if (i<100)
                {
                    RegistrationNumbersSet.add(series+"00"+i);
                }else if (i<1000)
                {
                    RegistrationNumbersSet.add(series+"0"+i);
                }else
                {
                    RegistrationNumbersSet.add(series+i);
                   
                }
            }
       
        return RegistrationNumbersSet;
    }
public static    HashSet<String> getRegistrationNumbersTill9999(String series)
{
   // HashSet<Integer> numset = getNumbersBelowNAddingUpToNine(9999);
    HashSet<String> RegistrationNumbersSet = new HashSet<String>();
    for (int i=1;i<=9999;i++)
    {
       
           
            if(i<10)
            {
                RegistrationNumbersSet.add(series+"000"+i);
           
            }else if (i<100)
            {
                RegistrationNumbersSet.add(series+"00"+i);
            }else if (i<1000)
            {
                RegistrationNumbersSet.add(series+"0"+i);
            }else
            {
                RegistrationNumbersSet.add(series+i);
               
            }
        }
   
    return RegistrationNumbersSet;
}

public static    HashSet<String> getRegistrationNumbers(String series)
{
	return getRegistrationNumbersTill9999(series);
	//getRegistrationNumbersAddingTo9(series);
	
}

public static String FetchInitialPageAsHTML(String URLToFetch,String Parameters)
{

     try {
            URL url;
            URLConnection urlConnection;
            DataOutputStream outStream;
            DataInputStream inStream;
     
           
            String body =Parameters;
            // Build request body
            if(body.length()==0)
            {
             body =
            "fName=" + URLEncoder.encode("Atli", "UTF-8") +
            "&lName=" + URLEncoder.encode("Þór", "UTF-8");
     
            }
          
            // Create connection URL
           
           
            url = new URL(URLToFetch); //http://210.212.213.82/Search/VehicleRegistrationSearch.aspx
            urlConnection = url.openConnection();
            ((HttpURLConnection)urlConnection).setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setUseCaches(false);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length", ""+ body.length());
     
            // Create I/O streams
            outStream = new DataOutputStream(urlConnection.getOutputStream());
            inStream = new DataInputStream(urlConnection.getInputStream());
     
            // Send request
            outStream.writeBytes(body);
            outStream.flush();
            outStream.close();
     
            // Get Response
            // - For debugging purposes only!
            String buffer,Response="";
            while((buffer = inStream.readLine()) != null) {
               // System.out.println(buffer);
                Response= Response+buffer+"\n";
            }
          //  System.out.println(Response);
            // Close I/O streams
            inStream.close();
            outStream.close();
            return Response;
        }
        catch(Exception ex) {
            System.out.println("Exception cought:\n"+ ex.toString());
        }   
     return "";
}


public static String doGet(String URLToGet,String urlParameters) throws IOException
{
    String response="";
    URL myUrlObj = new URL(URLToGet);
    URLConnection yc = myUrlObj.openConnection();
    BufferedReader in = new BufferedReader(
                            new InputStreamReader(
                            yc.getInputStream()));
    String inputLine;

    while ((inputLine = in.readLine()) != null)
    {
        response = response+"\n" + inputLine;
    }
    //    System.out.println(response);
    in.close();
   
   
     
   
    return response;
}



public static String doPost(String URLToPost,String urlParameters) throws IOException
{
   
    URL url = new URL(URLToPost);
    URLConnection conn = url.openConnection();

    conn.setDoOutput(true);

    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

    writer.write(urlParameters);
    writer.flush();

    String line,response="";
    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

    while ((line = reader.readLine()) != null) {
        response = response+"\n" + line;
    }
    writer.close();
    reader.close();  
   
    return response;
}

public static String doHttpsPost(String URLToPost,String urlParameters) throws IOException
{
	String httpsURL = URLToPost;//"https://www.abcd.com/auth/login/";


	URL myurl = new URL(httpsURL);
	String query = urlParameters;
	String response ="", line="";
	HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
	con.setRequestMethod("POST");

	con.setRequestProperty("Content-length", String.valueOf(query.length())); 
	con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0;Windows98;DigExt)"); 
	con.setDoOutput(true); 
	con.setDoInput(true); 

	DataOutputStream output = new DataOutputStream(con.getOutputStream());  

	
	output.writeBytes(query);

	
	
	BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream() ));
	

    while ((line = reader.readLine()) != null) {
        response = response+"\n" + line;
    }
/*DataInputStream input = new DataInputStream( con.getInputStream() ); 



for( int c = input.read(); c != -1; c = input.read() )
{
System.out.print( (char)c );
response +=(char)c ;

}

input.close();
*/output.close();	
    reader.close();
return response; 
}

public static String httpsPost(String URLToPost,String urlParameters) throws IOException
{
	String httpsURL = URLToPost;//"https://www.abcd.com/auth/login/";


	urlParameters = "ctl00$OnlineContent$ScriptManager1=ctl00$OnlineContent$Updatepanel1|ctl00$OnlineContent$btnGetData&__EVENTTARGET=&__EVENTARGUMENT=&__VIEWSTATE=%2FwEPDwUKMTE0MzI5ODM0MA9kFgJmD2QWAgIBD2QWAgIJD2QWAgIFD2QWAmYPZBYEAgEPDxYCHgRUZXh0ZWRkAg0PFgIeB1Zpc2libGVnFh4CAQ8WAh4JaW5uZXJodG1sBQlBUDEzUjU5NzdkAgMPFgIfAgUGRElFU0VMZAIFDxYCHwIFHFBBVkFOIEtVTUFSICBDSEFJVEFOWUEgTEFOREFkAgcPFgIfAgUKQVpVUkUgR1JFWWQCCQ8WAh8CBQlNT1RPUiBDQVJkAgsPFgIfAgUWTUFSVVRJIFNXSUZUIFZESS1CU0lJSWQCDQ8WAh8CBQowMS8wMi8yMDEwZAIPDxYCHwIFFk1BUlVUSSBTV0lGVCBWREktQlNJSUlkAhEPFgIfAgULRDEzQTEzNjA3MTlkAhMPFgIfAgUKMDMvMDcvMjAxMGQCFQ8WAh8CBRRNQTNGS0VCMVMwMDUyMDE1NypCQWQCFw8WAh8CBRNTVEFURSBCQU5LIE9GIElORElBZAIZDxYCHwIFDEFQMDlUUVRSNzU4MmQCGw8WAh8CBRBSVEEtSFlERVJBQkFELVdaZAIdDxYCHwIFBkFDVElWRWRklkAnskjAA7UiWcP2p9mxaQJtHTE%3D&ctl00$OnlineContent$ddlInput=R&ctl00$OnlineContent$txtInput=ap13r5977&ctl00$OnlineContent$btnGetData=Get%20Data";
	URL myurl = new URL(httpsURL);
	HttpsURLConnection conn = (HttpsURLConnection) myurl.openConnection();
	conn.setReadTimeout(10000);
	conn.setConnectTimeout(15000);
	conn.setRequestMethod("POST");
	conn.setDoInput(true);
	conn.setDoOutput(true);

	

	OutputStream os = conn.getOutputStream();
	BufferedWriter writer = new BufferedWriter(
	        new OutputStreamWriter(os, "UTF-8"));
	writer.write(urlParameters);
	String line, response="";

	BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream() ));
	

    while ((line = reader.readLine()) != null) {
        response = response+"\n" + line;
    }
	writer.flush();
	writer.close();
	os.close();

	conn.connect();	
	return response;

}


}