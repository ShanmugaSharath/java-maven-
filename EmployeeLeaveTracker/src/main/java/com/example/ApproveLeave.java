
package com.example;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

import com.example.JDBCconnection.JDBC;
import com.example.log.Logfile;

import jakarta.servlet.http.*;

public class ApproveLeave extends HttpServlet {
	private static Logger alg=Logfile.getlg();
   
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        int empid = Integer.parseInt(req.getParameter("empId"));
        String fromdate = req.getParameter("fromDate");
        String todate = req.getParameter("toDate");
        
        
        //this employee have pending or approved in employee_leave tabel
        
        String firstquery="select leaveStatus from employee_leaves where empId =? and fromDate=?";
        
        try (Connection conn = JDBC.getConnection()) {
        	
        	PreparedStatement firststmt = conn.prepareStatement(firstquery);
        	firststmt.setInt(1, empid);
        	firststmt.setDate(2, java.sql.Date.valueOf(fromdate));
        	ResultSet  ls=firststmt.executeQuery();
        	String result="";
        	if(ls.next())
        	{
        		
        		result=ls.getString("leaveStatus");
        	}
        	
        	if(result.equals("pending")) {
        	
        	
        	
        	
        	
        	
        	
        

        // fromdate and todate sum to get total days
        int days = 0;
        try {
            String[] s1 = fromdate.split("-");
            String[] s2 = todate.split("-");
            int sum = Integer.parseInt(s2[2]) - Integer.parseInt(s1[2]);
            days = sum+1;
        } catch (Exception e) {
            res.getWriter().write("Invalid date format. Use yyyy-MM-dd");
            return;
        }

       
            // 1. Get current used leaves
            String query = "SELECT used FROM leave_quota WHERE empId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, empid);
                try (ResultSet rs = stmt.executeQuery()) {
                    int used = 0;
                    if (rs.next()) {
                        used = rs.getInt("used");
                    } else {
                        res.getWriter().write("Employee not found in leave_quota.");
                        return;
                    }

                    // 2. Check if within quota
                    int totally = used + days;
                    if (totally > 12) {
                        res.getWriter().write("Leave approval rejected: Quota exceeded.");
                        return;
                    }

                    // 3. Update leave_quota
                    String query1 = "UPDATE leave_quota SET used = ? WHERE empId = ?";
                    try (PreparedStatement stmt1 = conn.prepareStatement(query1)) {
                        stmt1.setInt(1, totally);
                        stmt1.setInt(2, empid);
                        int check = stmt1.executeUpdate();
                        if (check > 0) {
                            res.getWriter().write("Leave quota updated. ");

                            // 4. Update employee_leaves
                            String query2 = "UPDATE employee_leaves SET leaveStatus = ? WHERE empId = ? AND fromDate = ?";
                            try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
                                stmt2.setString(1, "approved");
                                stmt2.setInt(2, empid);
                                stmt2.setDate(3, java.sql.Date.valueOf(fromdate));
                                int check2 = stmt2.executeUpdate();
                                if (check2 > 0) {
                                    res.getWriter().write("Employee leave status updated.");
                                    
                                    alg.info("employee "+empid+" got approved");
                                } else {
                                    res.getWriter().write("Leave record not found for update.");
                                }
                            }
                        } else {
                            res.getWriter().write("Failed to update leave quota.");
                        }
                    }
                }
            }
        	}
        
        else
        {
        	res.getWriter().write("this employee leave is already  approved");
        }
        }catch (SQLException e) {
            res.getWriter().write("Database error: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
}


