package com.salon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import com.domain.AccessibleMenu;
import com.domain.Menu;
import com.domain.OfficerType;
import com.salon.service.AccessGroupSDAO;
import com.salon.service.OfficerTypeDAOS;
import com.service.AccessGroupDAO;
import com.service.MenuDAO;

@WebServlet("/AccessGroupSServlet")
public class AccessGroupSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method=request.getParameter("method");
		switch(method){
			case "getOfficerTypes" :	List<OfficerType> officertypes=OfficerTypeDAOS.getAllOfficerTypes();
												JSONArray officerTypesArray=new JSONArray();
												JSONArray array = new JSONArray();
													for (OfficerType officerType : officertypes) {
														JSONArray array3 = new JSONArray();
														array3.put(officerType.getOfficerTypeId());
														array3.put(officerType.getCode());
														officerTypesArray.put(array3);
													}
													JSONArray titlesOfficerTypesArray=new JSONArray();
													titlesOfficerTypesArray.put(array);
													titlesOfficerTypesArray.put(officerTypesArray);
													PrintWriter out = response.getWriter();
													response.setContentType("application/json");
													out.print(titlesOfficerTypesArray.toString());	
													break;
			case "getAll"	:			int node = Integer.parseInt(request.getParameter("node"));
										List<Menu> menus = com.salon.service.MenuDAO.getAllMenuByNode(node);
										JSONArray arrayMenu = new JSONArray();
										for (Menu menu : menus) {
											JSONArray arrayM = new JSONArray();
											arrayM.put(menu.getLabel());
											arrayM.put(menu.getMenuId());
											
											arrayMenu.put(arrayM);
										}
										PrintWriter outM = response.getWriter();
										response.setContentType("application/json");
										outM.print(arrayMenu.toString());
										break;
			case "create" :			String result =addAccessibleMenu(request);
									System.out.println("ok  "+result);
								    response.setContentType("text/plain");
								    response.setCharacterEncoding("UTF-8");
								    response.getWriter().write(result);
									break;
			case "modify" :			result =modifyAccessibleMenu(request);
									System.out.println("ok  "+result);
								    response.setContentType("text/plain");
								    response.setCharacterEncoding("UTF-8");
								    response.getWriter().write(result);
									break;
		}
	}

	public static String addAccessibleMenu(HttpServletRequest request){
		String result="";
		try {
			JSONArray selectedMenu = null;
				selectedMenu = new JSONArray(request.getParameter("selectedMenu"));
				int nodeId = Integer.parseInt(request.getParameter("nodeId"));
				int officerTypeId = Integer.parseInt(request.getParameter("officerType"));
				List<AccessibleMenu> accessibleMenus = new ArrayList<AccessibleMenu>();
				for (int i = 0; i < selectedMenu.length(); i++) {
					AccessibleMenu accessibleMenu = new AccessibleMenu();
					JSONArray array = new JSONArray();
					array = (JSONArray) selectedMenu.get(i);	
					accessibleMenu.setOfficerTypeId(officerTypeId);
					accessibleMenu.setMenuId(array.getInt(0));
					accessibleMenu.setNodeid(nodeId);
					accessibleMenus.add(accessibleMenu);				
				}
				
				
				
				
			result = AccessGroupSDAO.addAccessibleMenu(accessibleMenus);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return result;
  	}
	
	public static String modifyAccessibleMenu(HttpServletRequest request){
		String result="";
		try {
			JSONArray selectedMenu = null;
			selectedMenu = new JSONArray(request.getParameter("selectedMenu"));
			int nodeId = Integer.parseInt(request.getParameter("nodeId"));
			int officerTypeId = Integer.parseInt(request.getParameter("officerType"));
			List<AccessibleMenu> accessibleMenus = new ArrayList<AccessibleMenu>();
			for (int i = 0; i < selectedMenu.length(); i++) {
				AccessibleMenu accessibleMenu = new AccessibleMenu();
				JSONArray array = new JSONArray();
				array = (JSONArray) selectedMenu.get(i);	
				accessibleMenu.setOfficerTypeId(officerTypeId);
				accessibleMenu.setMenuId(array.getInt(0));
				accessibleMenu.setNodeid(nodeId);
				accessibleMenus.add(accessibleMenu);				
			}
			result = AccessGroupSDAO.modifyAccessibleMenu(accessibleMenus);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return result;
  	}
}
