package com.example.Leavestatus;

import java.io.IOException;

import com.example.service.LeaveService;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LeaveStatus extends HttpServlet
{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{
		String s=LeaveService.leavestatus();
		//resp.getWriter().write(s);
		resp.getWriter().write(s);
	}
}