package com.aprta.numberGeneration;

import java.util.ArrayList;

public abstract class DisctrictCodeGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/*
	 * generates code of all districts, includes even unused codes like AP00
	 */
 	public  String [] generateDistrictCode()
 	{
 		ArrayList<String> DistrictCodeList = new ArrayList<String>();



 		for(int i=0;i<=9;i++)
 		{

 			for(int j=0;j<=9;j++)
 			{


 				DistrictCodeList.add("AP"+(i+"")+ (j+"") );
 			}



 		}




 		String[] stockArr = new String[DistrictCodeList.size()];
 		stockArr = DistrictCodeList.toArray(stockArr);
 		return (stockArr);


 	}
}

