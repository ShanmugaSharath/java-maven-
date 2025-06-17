package com.example;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.example.model.Leave;
import com.example.service.LeaveService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

 public class LeaveStatusServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int empId = Integer.parseInt(req.getParameter("empId"));
        
        List<Leave> empLeaves = LeaveService.getLeavesByEmpId(empId);

        String result = "Leave History for Emp ID: " + empId + "\n";
        
        for (Leave leave : empLeaves) {
            result += "From: " + leave.getFromDate() + " To: " + leave.getToDate() + "\n";
        }
      
        resp.getWriter().write(result);
        
        
        
        
        //data from database feathing
        
        //String 
       /* ArrayList<Leave> arrayleave=new ArrayList<Leave>();
        String sql = "SELECT empId, fromDate, toDate FROM employee_leaves WHERE empId = ?";

        try (Connection conn = JDBC.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Leave l = new Leave(
                        rs.getInt("empId"),
                        rs.getDate("fromDate").toString(),
                        rs.getDate("toDate").toString()
                );
                arrayleave.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Leave l3:arrayleave)
        {
        	System.out.println("Employee id : "+l3.getEmpId()+" From Date : "+l3.getFromDate()+"To Date : "+l3.getToDate());
        }
        */
       // LeaveService.leavestatus();

    }
    
}

