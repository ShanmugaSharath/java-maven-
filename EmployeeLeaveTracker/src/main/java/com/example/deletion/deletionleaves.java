package com.example.deletion;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

import com.example.JDBCconnection.JDBC;
import com.example.log.Logfile;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class deletionleaves extends HttpServlet
{
	private static Logger alg=Logfile.getlg();
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException 
	{
		
		 int empId = Integer.parseInt(req.getParameter("empId"));
        String fromDate = req.getParameter("fromDate");

         String sql = "DELETE FROM employee_leaves WHERE empId = ? AND fromDate = ?";
         Connection conn = JDBC.getConnection();
         PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empId);
			stmt.setDate(2, java.sql.Date.valueOf(fromDate));
			int check = stmt.executeUpdate();
			
			if (check > 0) {
				//ResultSet  rs= stmt.executeQuery();
				
                resp.getWriter().write("Leave deleted successfully.");
                alg.info("Leave deleted for empId: " + empId + ", fromDate: " + fromDate);
            } else {
                resp.getWriter().write("No leave entry found to delete.");
                alg.warning("No deletion occurred: empId=" + empId + ", fromDate=" + fromDate);
            }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
