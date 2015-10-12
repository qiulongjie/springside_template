/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.common.template.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	@RequestMapping(method = RequestMethod.GET)
	public String index() {
		return "allpages/index2-";
	}
	
	@RequestMapping(value = "welcome" ,method = RequestMethod.GET)
	public String index2() {
		return "allpages/welcome";
	}
	
	@RequestMapping(value = "indexShow" ,method = RequestMethod.GET)
	public String indexShow() {
		return "allpages/indexShow";
	}
	
	@RequestMapping(value = "top" ,method = RequestMethod.GET)
	public String top() {
		return "allpages/top";
	}
	@RequestMapping(value = "left" ,method = RequestMethod.GET)
	public String left() {
		return "allpages/left";
	}

}
