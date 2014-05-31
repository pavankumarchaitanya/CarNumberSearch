package com.aprta.numberGeneration;

import java.util.ArrayList;

public class NumberSeriesGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public ArrayList<String> generateSeriesFromDistrictCode(String [] DistrictCodeArray)
	{
		ArrayList<String> Series = new ArrayList<String>();
 		for(String DistCode:DistrictCodeArray)
 		{
 			Series.addAll( generateSeries(DistCode,'A','C','A','Z'));
 		}
 		
 		return Series;
	}


private static ArrayList<String> generateSeries(String DistrictCode,char seriesFirstCharStart,char seriesFirstCharEnd,char secondCharStart, char SeriesSecondCharEnd)
	{
		ArrayList<String> SeriesList = new ArrayList<String>();



		for(int i=(seriesFirstCharEnd-seriesFirstCharStart); i >= 0;i--)
		{

			for(int j=0;j<=(SeriesSecondCharEnd-secondCharStart);j++)
			{

				String str = new Character((char)('A'+i)).toString()+ new Character((char)('A'+j)).toString();
				SeriesList.add(DistrictCode+ str);
			}



		}




		return (SeriesList);


	}
}