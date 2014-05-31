import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;
import javax.xml.bind.JAXBException;

import org.hibernate.Session;

import com.aprta.numberGeneration.HyderabadDistrictCodeGenerator;
import com.aprta.numberGeneration.NumberSeriesGenerator;

import java.util.Properties;


public class TestSeriesGeneration {

	/**
	 * @param args
	 */

	private static void createAndStoreEvent(Vehicle vObj) {

		Session session = HibernateUtil.getSessionFactory().openSession();//.getCurrentSession();
		session.beginTransaction();

		session.save(vObj);

		session.getTransaction().commit();

	}


	public static void main(String args[]) throws IOException, InterruptedException, JAXBException
	{
		long sleepTime  = 0;
		VehicleDetailFetcher vFetcher= new VehicleDetailFetcherAndhraPradesh();
		String [] DistrictCodeArray = (new HyderabadDistrictCodeGenerator()).generateDistrictCode();
		ArrayList<String> Series = (new NumberSeriesGenerator()).generateSeriesFromDistrictCode(DistrictCodeArray);
		HashSet<String> IgnoreNumberSeriesSet = new HashSet<String>();
		for (String SeriesNumber: Series)
		{
			System.out.println("Processing series : " + SeriesNumber);
			if(IgnoreNumberSeriesSet.contains(SeriesNumber))
			{
				continue;
			}
			VehicleList VListObj = new VehicleList();
			HashSet<String> RegNumbSet = Util.getRegistrationNumbers(SeriesNumber);
			String Details; 
			int count = 0;
			for(String RegdNum : RegNumbSet)
			{	Details = "" ;
			System.out.println(RegdNum);
				if(regdNumNotInDB(RegdNum))
				{
					sleepTime = (long)(10000*Math.random());
					Thread.sleep(sleepTime);
					System.out.println("Sleep time : " + (sleepTime/1000.0));
					Vehicle vObj = vFetcher.getVehicleDetailsFromService(RegdNum);
					
					if(vObj!=null)
					{
						VehicleDAO.createAndStoreEvent(vObj);
					}
					if(vObj==null)
					{
					IgnoreSeriesBelongingToSameDistrict(RegdNum,Series,IgnoreNumberSeriesSet);
					break;
					}
					
	/*					if(vObj!= null && vObj.getVehicleClass().contains("MCRN")&& isLuxuryCarBrand(Details))
						{
							VListObj.addVehicleToList(vObj);
						}
					System.out.println("Vehicle : "+ (++count) +"/"+RegNumbSet.size()+ " :" +RegdNum);
				if(!isAWorthySeries(count, VListObj.vList.size())||vObj==null)
					{
					IgnoreSeriesBelongingToSameDistrict(RegdNum,Series,IgnoreNumberSeriesSet);
					break;
					}
					*/
				}
			}

		}
	}


	private static boolean regdNumNotInDB(String regdNum) {
		// TODO Auto-generated method stub
		return !VehicleDAO.SearchForVehicleNum(regdNum);
		
	}


	public static boolean isAWorthySeries(int count, int PremiumCarsCount)
	{
		if(count >20 && PremiumCarsCount==0)
		{
			System.out.println("Not a worthy series");
			return false;

		}
		else 
		{return true;
		}

	}



	public static boolean isLuxuryCarBrand(String Details)
	{
		String [] LuxuryBrandList = {"Aston","Audi","Bentley","BMW","Royce","Bugatti","Cadillac","Jaguar","Mercedes","Ferrari","Porsche", "Volvo","Mitsubishi"};

		for(String Brand:LuxuryBrandList)
		{
			if(Details.toUpperCase().contains(Brand.toUpperCase()))
				return true;
		}
		return false;
	}

	public static  void IgnoreSeriesBelongingToSameDistrict(String RegdNum, ArrayList<String> NumberSeries,HashSet<String> IgnoreNumberSeries)
	{
		String distNum = RegdNum.substring(0, 3);
IgnoreNumberSeries.add(distNum);
		
	//	for(String series: NumberSeries)
	//	{
	//		if(series.toUpperCase().contains(distNum.toUpperCase()))
	//			IgnoreNumberSeries.add(series);

	//	}



	}


}
