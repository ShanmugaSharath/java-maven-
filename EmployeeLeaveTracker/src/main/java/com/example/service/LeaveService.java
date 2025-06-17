package com.example.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.Date;
import java.sql.*;

import com.example.JDBCconnection.JDBC;
import com.example.model.Leave;

public class LeaveService {
    //private static List<Leave> l = new ArrayList<>();
    private static Map<Integer,List<Leave>> m=new HashMap<>();

    public static void applyLeave(Leave leave) {
        int id=leave.getEmpId();
        if(!m.containsKey(id))
        {
        	m.put(id, new ArrayList<>());
        }
        
        m.get(id).add(leave);
        
        
    
    }
    
    public static List<Leave>getLeavesByEmpId(int id)
    {
    	
    	if(m.containsKey(id))
    	{
    		return m.get(id);
    	}
    	return new ArrayList<>();
    }
   
    public static String leavestatus()
    {
    	String sql = "select * from employee_leaves";
    	String s="";
    	Connection conn = JDBC.getConnection();
        try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			ResultSet rs=stmt.executeQuery();
			while(rs.next())
			{
				int id=rs.getInt("empId");
				Date fromdate=rs.getDate("FromDate");
				Date todate =rs.getDate("toDate");
				String save="Employee id : "+id+" FormDate : "+fromdate+" todate : "+todate+"\n";
				s=s+save;
			}
			System.out.print(s);
			
			//return s;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return s;

    }
  
    public static Boolean validation(Leave l)
    {
    	//the date contains "year" - "month" - "day"
    	int count=0;
    	String s1[]=l.getFromDate().split("-");
    	String s2[]=l.getToDate().split("-");
    	if((s1.length==3)&& (s2.length==3))
    	{
    		count++;
    	}
    	
    	//if the year not contains 4word like -"2025" --> "202"
    	
    	String date=l.getFromDate().substring(0,4);
    	String date2=l.getToDate().substring(0,4);
    	if((date.length()==4)&& (date2.length()==4))
    	{
    		count++;
    	}
    	
    	//from date <= to date
    	String first[]=l.getFromDate().split("-");
    	String second[]=l.getToDate().split("-");
    	int fromyear=Integer.parseInt(first[0]);
    	int frommonth=Integer.parseInt(first[1]);
    	int fromdate=Integer.parseInt(first[2]);
    	int toyear=Integer.parseInt(second[0]);
    	int tomonth=Integer.parseInt(second[1]);
    	int todate=Integer.parseInt(second[2]);
    	
    	if((fromyear<=toyear) && (frommonth<=tomonth)&& (fromdate<=todate))
    	{
    		count++;
    	}
    	
    	
    	if(count==3)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    
    	
    }
    public static Boolean overlaps(Leave l)
    {
    	int id=l.getEmpId();
    	if(m.containsKey(id))
    	{
    		List<Leave> leave=m.get(id);
    		//if(l2.getFromDate().equals(l.getFromDate()))
    		for(Leave l3:leave)
    		{
    			if(l3.getFromDate().equals(l.getFromDate()) || l3.getToDate().equals(l.getToDate()))
    			{
    				return false;
    			}
    			
    		}
    		return true;
    	}
    	else
    	{
    		return true;
    	}
    }
    


    
}