/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.common.template.web.controllers;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.template.service.kpi.PVUVService;


@Controller
@RequestMapping(value = "/kpi")
public class KPIController extends BaseController {
	
	@Resource
	private PVUVService pvuvService;

	@RequestMapping(method = RequestMethod.GET)
	public String kpi() {
		return "allpages/charts/kpis";
	}
	
	@RequestMapping(value = "kpi2",method = RequestMethod.GET)
	public String kpi2() {
		return "allpages/charts/kpis2";
	}
	
	@RequestMapping(value = "getPVData")
	public String getPVData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String period = request.getParameter("period");
		String date = request.getParameter("date");
		String jsonData = pvuvService.getPVUVData(period, date);
		sendData(response, jsonData);
		return null;
	}
}
