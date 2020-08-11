package com.strandls.document.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.strandls.document.pojo.GNFinderResponseMap;

public class Dummy {

	public static void main(String[] args) throws MalformedURLException {
//		URL url = new URL("https://indiabiodiversity.org/biodiv/content/documents/document-0162468a-7ce7-499e-ac6d-ead2dc273c35/687.pdf");
//		URL url = new URL("https://threatenedtaxa.org/index.php/JoTT/article/viewFile/3843/6249");
//		URL url = new URL("https://threatenedtaxa.org/index.php/JoTT/article/view/5650/6853");
//		URL url = new URL("http://indianbirds.in/pdfs/IB_16_1_ShaktivelETAL_WhitebrowedCrake.pdf");
		URL url = new URL("https://www.tutorialspoint.com/tika/tika_content_extraction.htm");
//		https://threatenedtaxa.org/index.php/JoTT/article/viewFile/3843/6249
		
		try {
			URLConnection urlConnection= url.openConnection();
			System.out.println(urlConnection.getContentType());
			InputStream in = url.openStream();
			Files.copy(in, Paths.get("/home/ashish/GNRD_Output/url/tp_12Aug.html"), StandardCopyOption.REPLACE_EXISTING);
			in.close();
			in = new FileInputStream("/home/ashish/GNRD_Output/url/tp_12Aug.html");
			ContentHandler contenthandler = new BodyContentHandler();
	        Metadata metadata = new Metadata();
	        Parser parser = new AutoDetectParser();
	        parser.parse(in, contenthandler, metadata, new ParseContext());
	        System.out.println(contenthandler.toString());
//	        System.out.println(metadata);
//			Files.copy(in, Paths.get("/home/ashish/GNRD_Output/url/12Aug.pdf"), StandardCopyOption.REPLACE_EXISTING);
			in.close();
			System.out.println("File downloaded");
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//        PDDocument document;
//        String text = null;
//		try {
//			document = PDDocument.load(new FileInputStream(url.openStream()));
//			PDFTextStripper pdfTextStripper = new PDFTextStripper();
//			pdfTextStripper.setStartPage(1);
//			pdfTextStripper.setEndPage(5);
//			text  = pdfTextStripper.getText(document);
//			System.out.println(text.substring(0, 10000));
//			document.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
	
		
		String gnfinder = "gnfinder find -l eng ";
////		String filePath = "/home/ashish/GNRD_Output/javatest/browedcrake.txt";
//		String filePath = "/home/ashish/GNRD_Output/url/687_1108.pdf";
//		
//		StringBuilder command = new StringBuilder().append("cat \"").append(text).append("\" | ").append(gnfinder);
		
//		StringBuilder command = new StringBuilder().append(gnfinder).append("/home/ashish/GNRD_Output/url/11Aug.txt");
//		try {
//		    Process process = Runtime.getRuntime().exec(command.toString());
//		 
//		    BufferedReader reader = new BufferedReader(
//		            new InputStreamReader(process.getInputStream()));
//		    String line;
//		    StringBuilder stringBuilder = new StringBuilder();
//		    while ((line = reader.readLine()) != null) {
//		    	stringBuilder.append(line);		   
//		    	}
//		    System.out.println(stringBuilder);
//		    ObjectMapper objectMapper = new ObjectMapper();
//			GNFinderResponseMap response = objectMapper.readValue(String.valueOf(stringBuilder), GNFinderResponseMap.class);
//			System.out.println(response);
//		    reader.close();
//		    
//		 
//		} catch (IOException e) {
//		    e.printStackTrace();
//		}
//			
//
}
	}


