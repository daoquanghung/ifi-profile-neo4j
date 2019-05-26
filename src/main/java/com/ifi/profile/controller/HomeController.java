package com.ifi.profile.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.ifi.profile.model.Node;
import com.ifi.profile.service.NeoService;
import com.ifi.profile.utils.Constants;

@Controller
public class HomeController {

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		
		// connect to Neo4j database
		NeoService neoService = new NeoService(Constants.URL_IFI, Constants.USER_IFI, Constants.PASS_IFI);
				
		// list nodes
		List<Node> listNodes = neoService.getListNodes();
		List<Node> listProjects = neoService.getListProjects();
		List<Node> listTechnologies = neoService.getListTechnologies();
		List<Node> listDepartments = neoService.getListDepartments();
		neoService.close();
		
		ModelAndView modelRet = new ModelAndView("home");
        modelRet.addObject("lists", listNodes);
        modelRet.addObject("listProjects", listProjects);
        modelRet.addObject("listTechnologies", listTechnologies);
        modelRet.addObject("listDepartments", listDepartments);
		return modelRet;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add(@Validated Node node) {
				
		// connect to Neo4j database
		NeoService neoService = new NeoService(Constants.URL_IFI, Constants.USER_IFI, Constants.PASS_IFI);
		
		if((!"".equals(node.getTypeNode()))&&(node.getTypeNode()!=null)&&(node.getLabelNode()!=null)&&(!"".equals(node.getLabelNode()))){
			neoService.addNode(node);
		} else {
			System.out.println("error: node empty");
		}
		
		// get list nodes
		List<Node> listPeople = neoService.getListNodes();
		
        neoService.close();
        
        // render view
        ModelAndView modelRet = new ModelAndView("home");
        modelRet.addObject("lists", listPeople);
		return modelRet;
	}
	
	// Delete node
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@Validated Node node){
		// connect to Neo4j database
		NeoService neoService = new NeoService(Constants.URL_IFI, Constants.USER_IFI, Constants.PASS_IFI);
		
		if((!"".equals(node.getTypeNode()))&&(node.getTypeNode()!=null)&&(node.getLabelNode()!=null)&&(!"".equals(node.getLabelNode()))){
			neoService.deleteNode(node);
		} else {
			
		}
		
		ModelAndView modelRet = new ModelAndView("home");
		
		return modelRet;
	}
}
