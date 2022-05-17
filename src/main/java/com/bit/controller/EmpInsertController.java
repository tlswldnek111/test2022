package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.util.BitMy;

public class EmpInsertController extends HttpServlet {

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	   req.setCharacterEncoding("utf-8");
	   String[] param = {
         req.getParameter("empno")
         ,req.getParameter("ename")
         ,req.getParameter("sal")
      };
      int result=0;
      int empno=Integer.parseInt(param[0].trim());
      String ename=param[1].trim();
      int sal=Integer.parseInt(param[2].trim());
      String errMsg="";
      String sql="insert into emp (empno,ename,sal,hiredate) values ("+empno+",'"+ename+"',"+sal+",now())";
      try(
         Connection conn=BitMy.getConnection();
         Statement stmt = conn.createStatement();
            ){
         result=stmt.executeUpdate(sql);
      }catch (SQLException e) {
         e.printStackTrace();
         errMsg=e.getMessage();
      }
      	//json파일에 붙히기 
      resp.setContentType("application/json");
      PrintWriter pw = resp.getWriter();
      pw.print("{\"insert\":[{\"result\":");
      pw.print(result>0?true:false);
      pw.print(",\"err\":\""+errMsg);
      pw.println("\"}]}");

   }
}