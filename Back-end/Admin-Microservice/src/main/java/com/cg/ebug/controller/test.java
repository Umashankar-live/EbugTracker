package com.cg.ebug.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {
	   @RequestMapping(value="/", method= RequestMethod.GET)
	    public @ResponseBody String displayStartPage(){
	        return "{hello}";
	    }
}
