package com.example;
import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

import com.example.JDBCconnection.JDBC;
import com.example.log.Logfile;
import com.example.model.Leave;
import com.example.service.LeaveService;
import com.example.textfile.textfileconv;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ApplyLeaveServlet extends HttpServlet {
	
	
	private static Logger alg=Logfile.getlg();
	//log.info("Logger initialized");
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int empId = Integer.parseInt(req.getParameter("empId"));
        String fromDate = req.getParameter("fromDate");
        String toDate = req.getParameter("toDate");
        String leavetype=req.getParameter("leaveType");
        Leave l= new Leave(empId, fromDate, toDate,leavetype);
        String che1=fromDate.substring(0, 4);
        String che2=toDate.substring(0, 4);
        if(LeaveService.validation(l))
        {
        	if(LeaveService.overlaps(l))
        	{
        		
        	
        	LeaveService.applyLeave(l);
        	textfileconv.textfilesaver( req, l);
        	alg.info("employee id : "+l.getEmpId()+" leave saved in log ");
        	
        	//saving data in database
        	String sql = "INSERT INTO employee_leaves (empId, fromDate, toDate, leaveType, leaveStatus) VALUES (?, ?, ?, ?,'pending')";
        	Connection conn = JDBC.getConnection();
            try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setInt(1, l.getEmpId());
				stmt.setDate(2,(java.sql.Date.valueOf( l.getFromDate())));
				stmt.setDate(3, java.sql.Date.valueOf(l.getToDate()));
				stmt.setString(4,l.leaveType);
				stmt.executeUpdate();
				resp.getWriter().write("your leave is saved ");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	}
        	else
        	{
        		resp.getWriter().write("your leave log is already have ");
        		alg.warning("same date entering!!");
        	}
        }
       else {
    	   resp.getWriter().write("YOUR ARE Entering date is worng or worng year or month - PLEASE CORRECT IT");
        	alg.warning("validation error or worng date!!");
        }
    }
}