package com.example;

import com.example.JDBCconnection.JDBC;
import com.example.textfile.textfileconv;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

public class Monthlysummary extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		int month=Integer.parseInt(req.getParameter("int"));
		String query="SELECT empId,COUNT(*) AS totalLeaves FROM employee_leaves WHERE MONTH(fromDate) = ? GROUP BY empId;";
		
		Connection conn = JDBC.getConnection(); // Assume DBUtil.getConnection() is implemented
        try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setInt(1,month);
			ResultSet rs = stmt.executeQuery();
			String s=" "+month+" month report \n";
			while(rs.next())
			{
				s=s+"this is employee id :"+String.valueOf(rs.getInt("empId"))+" ";
				s=s+"--->"+String.valueOf(rs.getInt("totalLeaves"))+" leaves \n";
			}
			resp.setContentType("text/plain");
			resp.getWriter().write(s);
			
			textfileconv.monthlytextfile(s);
		
        
        
        
        
        
        
        
        
        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}