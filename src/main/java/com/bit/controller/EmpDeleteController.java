package com.bit.controller;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.util.BitMy;

public class EmpDeleteController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int empno=Integer.parseInt(req.getParameter("empno"));
		String sql="delete from emp where empno="+empno;
		try(
				Connection conn=BitMy.getConnection();
				Statement stmt=conn.createStatement();
				
			){
			stmt.executeUpdate(sql);
			resp.setStatus(HttpServletResponse.SC_OK);
		} catch (SQLException e) {
			e.printStackTrace();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}
