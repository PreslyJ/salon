package com.salon.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import base.util.ExcelGenerator;
import base.util.ReflectionUtil;

import com.domain.Client;
import com.salon.service.ReportDAOS;

@WebServlet("/ReportServletS")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		String repNo=request.getParameter("repNo");
		switch(method){
			case "getReport" 				:	  
												  if(repNo.equalsIgnoreCase("01")){ 	 
													  List<Object> objList=new ReportDAOS().getclients();
													  Map<Integer, List<Object>> hm=new HashMap<Integer, List<Object>>();
													  for (Object object : objList) {
														  int x=1;
														  List<Object> lst=new ArrayList<Object>();
														  Client cl=Client.class.cast(object);
														  lst.add(cl.getClientId());
														  lst.add(cl.getFirstName());
														  lst.add(cl.getLastName());
														  lst.add(cl.getGender());
														  lst.add(cl.getPhoneNo());
														  lst.add(cl.getEmail());
														  x++;
													  }
													  
													  List<Field> fields=ReflectionUtil.getAllFields(objList.get(0).getClass());	
													  String[] reportFields=ReflectionUtil.getReportFields(objList.get(0), fields);
													  
													  try {
														response=ExcelGenerator.printReport2(response, hm, reportFields,  "Clients", "Total client base ", ExcelGenerator.simpdate.format(new Date()));
													} catch (Exception e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
//															 
												  }
												  break;
					
		}	
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	
}
