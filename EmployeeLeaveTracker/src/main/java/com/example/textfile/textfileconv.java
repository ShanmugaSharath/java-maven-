package com.example.textfile;
import com.example.model.Leave;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;


import com.example.log.Logfile;

import jakarta.servlet.http.HttpServletRequest;

public class textfileconv
{
	private static Logger textfilelog=Logfile.getlg();
	public static void main(String args[]) throws FileNotFoundException
	{
		//Leave l=new Leave(101,"2025-6-10","2025-7-10");
		System.out.print("normal java runing");
		
	}
	
	public static void textfilesaver(HttpServletRequest req, Leave l) throws IOException {
	    try {
	        //String realPath = req.getServletContext().getRealPath("/WEB-INF/output1.txt");
	        String realPath="C:\\Users\\shanmugasharath.a\\Desktop\\output1.txt";
	        
	        FileWriter fw = new FileWriter(realPath,true); 
            PrintWriter p = new PrintWriter(fw);
	        
	        
	        String s = l.getEmpId() + " " + l.getFromDate() + " " + l.getToDate();
	        p.println("this is details of employee leaves");
	        p.println(s);
	        p.close();
	        //System.out.println("File write successful at: " + realPath);
	        textfilelog.info("in text file writer class  your data saved/Not--> file write status = SUCCESS");
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }
	}
	public static void monthlytextfile(String s) throws IOException
	{
		String realPath="C:\\Users\\shanmugasharath.a\\Desktop\\monthlyreport.txt";
        
        FileWriter fw = new FileWriter(realPath,true); 
        PrintWriter p = new PrintWriter(fw);
        p.println(s);
        p.close();
	}


}