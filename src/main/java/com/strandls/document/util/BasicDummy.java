package com.strandls.document.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicDummy {

	public static void main(String[] args) {

//		try {
////			URL url = new URL("http://indianbirds.in/pdfs/IB_16_1_ShaktivelETAL_WhitebrowedCrake.pdf");
//			URL url = new URL("http://indianbirds.in/pdfs/IB_16_1_ShaktivelETAL_WhitebrowedCrake.pdf");
//			BufferedInputStream reader = new BufferedInputStream(url.openStream());
//			String inputLine;
//			FileOutputStream fis = new FileOutputStream("/home/ashish/GNRD_Output/url/crake.pdf");
//	        byte[] buffer = new byte[1024];
//	        int count=0;
//	        while((count = reader.read(buffer,0,1024)) != -1)
//	        {
//	            fis.write(buffer, 0, count);
//	        }
//	        fis.close();
//			reader.close();
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("completed");
//				
//	}
		
		Date date = new Date(); 
        Timestamp ts=new Timestamp(date.getTime());                      
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH:mm:ss");  
        System.out.println(formatter.format(ts));                     
	}
}
