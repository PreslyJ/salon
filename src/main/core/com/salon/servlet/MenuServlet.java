package com.salon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.domain.AccessibleMenu;
import com.domain.Menu;
import com.salon.service.MenuDAO;

@WebServlet("/MenuServletS")
public class MenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		String method=request.getParameter("method");
  		
  		
  		switch(method){
  		
		case "getAllMenus" :    int officerTypeId=(Integer) request.getSession().getAttribute("OfficerTypeId");
								List<Menu> menus= MenuDAO.getAllMenuByOfficer(officerTypeId);
								JSONObject jsonObject = new JSONObject();
								JSONArray array1 = new JSONArray();
								JSONArray array2 = new JSONArray();
								JSONArray array3 = new JSONArray();
								JSONArray array4 = new JSONArray();
								JSONArray array5 = new JSONArray();
								JSONArray array6 = new JSONArray();
								JSONArray array7 = new JSONArray();
								JSONArray array8 = new JSONArray();
								for (Menu menu : menus) {
									JSONArray array = new JSONArray();
									switch (String.valueOf(menu.getNode())) {
									case "1":
										array.put(menu.getLabel());
										array.put(menu.getAnchorId());
										array1.put(array);
										break;

									case "2":
										array.put(menu.getLabel());
										array.put(menu.getAnchorId());
										array2.put(array);
										break;
									case "3":
										array.put(menu.getLabel());
										array.put(menu.getAnchorId());
										array3.put(array);
										break;
									case "4":
										array.put(menu.getLabel());
										array.put(menu.getAnchorId());
										array4.put(array);
										break;
									case "5":
										array.put(menu.getLabel());
										array.put(menu.getAnchorId());
										array5.put(array);
										break;
									case "6":
										array.put(menu.getLabel());
										array.put(menu.getAnchorId());
										array6.put(array);
										break;
									case "7":
										array.put(menu.getLabel());
										array.put(menu.getAnchorId());
										array7.put(array);
										break;
									case "8":
										array.put(menu.getLabel());
										array.put(menu.getAnchorId());
										array8.put(array);
										break;
									default:
										break;
									}
									
								}
								try {
									jsonObject.put("menu1", array1);
									jsonObject.put("menu2", array2);
									jsonObject.put("menu3", array3);
									jsonObject.put("menu4", array4);
									jsonObject.put("menu5", array5);
									jsonObject.put("menu6", array6);
								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								PrintWriter out = response.getWriter();
								response.setContentType("application/json");
								out.print(jsonObject.toString());
								break;
								
		case "getAllSelectedMenusByNode" : officerTypeId=0;
								officerTypeId=Integer.parseInt(request.getParameter("OfficerTypeId"));
								int node = Integer.parseInt(request.getParameter("nodeId"));
								List<AccessibleMenu> menuList= MenuDAO.getAllMenuByNodeNOfficer(node, officerTypeId);
								JSONArray arrayMenu = new JSONArray();
								for (AccessibleMenu menu : menuList) {
									JSONArray array = new JSONArray();
									array.put(menu.getMenuId());
									arrayMenu.put(array);
								}
								PrintWriter outMenu = response.getWriter();
								response.setContentType("application/json");
								outMenu.print(arrayMenu.toString());
								break;
  		}
  	}

}
