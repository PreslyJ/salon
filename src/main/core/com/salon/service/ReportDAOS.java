package com.salon.service;

import java.util.List;

import com.domain.Client;
import com.inventryPro.util.HibernateTemplate;

public class ReportDAOS extends HibernateTemplate {
	public  List<Object> getclients(){
		
		List<Object> list=getAll(Client.class, " clientId != 0");

		return list;	
	}
}


