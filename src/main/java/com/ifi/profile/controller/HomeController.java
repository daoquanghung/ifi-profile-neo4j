package com.ifi.profile.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ifi.profile.model.User;
import com.ifi.profile.service.Person;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		// current Date
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ModelAndView user(@Validated User user) {
		
		// connect to Neo4j database
		Person person = new Person("bolt://localhost:7687", "neo4j", "11111111");
		
		// search list people by a starting string
		List<User> listPeople = person.getListPeople(user.getUserName());
		
        person.close();
        
        // render view
        ModelAndView modelRet = new ModelAndView("user");
        modelRet.addObject("lists", listPeople);
        modelRet.addObject("userName", user.getUserName());
		return modelRet;
	}
}
