package com.ifi.profile.controller;

import java.text.DateFormat;

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
import com.ifi.profile.model.Tech;
import com.ifi.profile.service.Technology;
import com.ifi.profile.model.Task;
import com.ifi.profile.service.Project;
import com.ifi.profile.model.Rela;
import com.ifi.profile.service.Relationship;
import com.ifi.profile.model.Office;
import com.ifi.profile.service.Department;

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
	

	// Person
	@RequestMapping(value = {"/form"}, method = RequestMethod.POST)
	public String form(@Validated User user, Model model){
		
		Person person = new Person("bolt://localhost:7687", "neo4j", "11111111");
		
		User addName = person.addPersonName(user.getUserName());
		
		User addId = person.addPersonId(user.getUserName(), user.getUserId());
		
		User addTitle = person.addPersonTitle(user.getUserName(), user.getTitle());
		User addBirth = person.addPersonBirthday(user.getUserName(), user.getBirthday());
		User addJoin = person.addPersonJoin(user.getUserName(),user.getJoin());
		User addStatus = person.addPersonStatus(user.getUserName(), user.getStatus());
		

		
		person.close();
		
		model.addAttribute("addName", addName);
		model.addAttribute("addID", addId);
		model.addAttribute("addTitle", addTitle);
		model.addAttribute("addBirth", addBirth);
		model.addAttribute("addJoin", addJoin);
		model.addAttribute("addStatus", addStatus);

		
		return "form";
	}
	
	// Remove person
	
	@RequestMapping(value = {"/remove"}, method = RequestMethod.GET)
	public String remove(@Validated User user, Model model){
		Person person = new Person("bolt://localhost:7687", "neo4j", "11111111");
		User remove = person.removePerson(user.getUserId());
		
		person.close();
		
		model.addAttribute("remove", remove);
		return "remove";
	}
	
	// Search person
	@RequestMapping(value = {"/searchPerson"}, method = RequestMethod.GET)
	public String searchPerson(@Validated User user, Model model){
		Person person = new Person("bolt://localhost:7687", "neo4j", "11111111");
		
		List<User> listUser = person.searchPeople(user.getUserName());
		
		person.close();
		
		model.addAttribute("lists", listUser);
		model.addAttribute("userName", user.getUserName());
		
		for(User us : listUser){
			System.out.println("" +us.getUserName());
			System.out.println("" + us.getUserId());
			System.out.println("" +us.getTitle());
			System.out.println("" +us.getBirthday());
			System.out.println("" +us.getJoin());
			System.out.println("" +us.getStatus());
		}
			
		return "searchPerson";
	}
	
	// Add Technology
	@RequestMapping(value = "/techForm", method = RequestMethod.GET)
	public String techForm(@Validated Tech tech ,Model model){
		Technology technology = new Technology("bolt://localhost:7687", "neo4j", "11111111");
		
		// add technology name
		Tech addTechName = technology.addTechName(tech.getTechName());
		//add technology description
		Tech addDescription = technology.addTechDescription(tech.getTechName(), tech.getTechDescription());
		// add technology category
		Tech addCategory = technology.addTechCategory(tech.getTechName(), tech.getTechCategory());
		// add technology domain
		Tech addDomain = technology.addTechDomain(tech.getTechName(), tech.getTechDomain());
		
		technology.close();
		
		model.addAttribute("addTechName", addTechName);
		model.addAttribute("addDescription", addDescription);
		model.addAttribute("addCategory", addCategory);
		model.addAttribute("addDomain", addDomain);
		return "techForm";
	}
	
	// Remove technology
	
		@RequestMapping(value = {"/removeTech"}, method = RequestMethod.GET)
		public String removeTech(@Validated Tech tech, Model model){
			Technology technology = new Technology("bolt://localhost:7687", "neo4j", "11111111");
			Tech remove = technology.removeTech(tech.getTechName());
			
			technology.close();
			
			model.addAttribute("remove", remove);
			return "removeTech";
		}
		
	// Search technology
		
		@RequestMapping(value = {"/searchTech"}, method = RequestMethod.GET)
		public String searchTech(@Validated Tech tech, Model model){
			Technology technology = new Technology("bolt://localhost:7687", "neo4j", "11111111");
			
			List<Tech> listTech = technology.searchTech(tech.getTechName());
			
			technology.close();
			
			model.addAttribute("lists", listTech);
			model.addAttribute("techName", tech.getTechName());
			
			return "searchTech";
		}
		
	
	
	// Add Project
	@RequestMapping(value = "/project", method = RequestMethod.GET)
	public String project(@Validated Task task, Model model) {
		Project pro = new Project("bolt://localhost:7687", "neo4j", "11111111");
		
		// Add project info
		// Add project name
		Task addProName = pro.addProject(task.getProject());
		// Add project id
		Task addProId = pro.addProjectId(task.getProject(), task.getChargeid());
		// Add project status
		Task addProStatus = pro.addProjectStatus(task.getProject(), task.getProStatus());
		// Add project description
		Task addProDes = pro.addProjectDescription(task.getProject(), task.getProDescription());
		// Add project domain
		Task addProDomain = pro.addProjectDomain(task.getProject(), task.getProDomain());
		// Add project start date
		Task addProStart = pro.addProjectStart(task.getProject(), task.getStartdate());
		// Add project finish date
		Task addProFinish = pro.addProjectFinish(task.getProject(), task.getFinishdate());
		// Add project customer
		Task addCustomer = pro.addProjectCustomer(task.getProject(), task.getCustomer());
		
		pro.close();
		
		model.addAttribute("addProName", addProName);
		model.addAttribute("addProId", addProId);
		model.addAttribute("addProStatus", addProStatus);
		model.addAttribute("addProDes", addProDes);
		model.addAttribute("addProDomain", addProDomain);
		model.addAttribute("addProStart", addProStart);
		model.addAttribute("addProFinish", addProFinish);
		model.addAttribute("addCustomer", addCustomer);
		return "project";
	}
	
	// Remove project
	
		@RequestMapping(value = {"/removeProject"}, method = RequestMethod.GET)
		public String removeProject(@Validated Task task, Model model){
			Project pro = new Project("bolt://localhost:7687", "neo4j", "11111111");
			Task remove = pro.removeProject(task.getProject());
			
			pro.close();
			
			model.addAttribute("remove", remove);
			return "removeProject";
		}
	
	// Search project
		
		@RequestMapping(value = {"/searchProject"}, method = RequestMethod.GET)
		public String searchProject(@Validated Task task, Model model){
			Project pro = new Project("bolt://localhost:7687", "neo4j", "11111111");
			
			List<Task> listTask = pro.searchProject(task.getChargeid());
			
			pro.close();
			
			model.addAttribute("lists", listTask);
			model.addAttribute("projectName", task.getProject());
				
			return "searchProject";
		}
		

	
	// Add Relation
	@RequestMapping(value = "/relation", method = RequestMethod.GET)
	public String relation(@Validated Rela rela, Model model){
		Relationship relate = new Relationship("bolt://localhost:7687", "neo4j", "11111111");
		
		// relation between person and department
		Rela relaPerDe = relate.relaPersonDepart(rela.getUserId(), rela.getDepartment());
		// relation between project and technology
		Rela relaProTech = relate.relaProTech(rela.getTechName(), rela.getChargeId());
		//relation between person and technology
		Rela relaPerTech = relate.relaPerTech(rela.getUserId(), rela.getTechName());
		// relation between person and project (work in)
		Rela relaPerPro = relate.relaPerPro(rela.getUserId(), rela.getChargeId());
		
		relate.close();
		
		model.addAttribute("relaPerDe", relaPerDe);
		model.addAttribute("relaProTech", relaProTech);
		model.addAttribute("relaPerTech", relaPerTech);
		model.addAttribute("relaPerPro", relaPerPro);
		
		
		return "relation";
	}
	
	// Add Department
	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public String department(@Validated Office off, Model model){
		Department dep = new Department("bolt://localhost:7687", "neo4j", "11111111");
		
		Office addDepartment = dep.department(off.getName());
		Office addDescription = dep.description(off.getName(), off.getDescription());
		
		dep.close();
		
		model.addAttribute("addDepartment", addDepartment);
		model.addAttribute("addDescription", addDescription);
		
		return "department";
	}
	
	// Remove department
	@RequestMapping(value = {"/removeDepartment"}, method = RequestMethod.GET)
	public String removeDepartment(@Validated Office off, Model model){
		Department dep = new Department("bolt://localhost:7687", "neo4j", "11111111");
		Office remove = dep.removeDepartment(off.getName());
		
		dep.close();
		
		model.addAttribute("remove", remove);
		return "removeDepartment";
	}
	
	// Search department
	@RequestMapping(value = {"/searchDep"}, method = RequestMethod.GET)
	public String searchDep(@Validated Office off, Model model){
		Department dep = new Department("bolt://localhost:7687", "neo4j", "11111111");
		
		List<Office> listOff = dep.searchDepartment(off.getName());
		
		dep.close();
		
		model.addAttribute("lists", listOff);
		model.addAttribute("name", off.getName());
			
		return "searchDep";
	}
	
	// Search person by relationship
	@RequestMapping(value = {"/searchPersonByRela"}, method = RequestMethod.GET)
	public String searchPersonByRela(@Validated Rela rela, Model model){
		Relationship relat = new Relationship("bolt://localhost:7687", "neo4j", "11111111");
		// search by department
		List<User> listPerson = relat.searchByRela(rela.getRelation(), rela.getDepartment());
		// search by project
		List<User> listPersonByProject = relat.searchByProject(rela.getRelation(), rela.getChargeId());
		// search by technology
		List<User> listPersonByTech = relat.searchByTech(rela.getRelation(), rela.getTechName());
		
		relat.close();
		
		model.addAttribute("lists", listPerson);
		model.addAttribute("listByPro", listPersonByProject);
		model.addAttribute("listByTech", listPersonByTech);
		
		model.addAttribute("relation", rela.getRelation());

		return "searchPersonByRela";
	}
	
	// Search Technology by relationship
	@RequestMapping(value = {"/searchTechByRela"}, method = RequestMethod.GET)
	public String searchTechByRela(@Validated Rela rela, Model model){
		Relationship relat = new Relationship("bolt://localhost:7687", "neo4j", "11111111");
		// search by project
		List<Tech> listTechByProject = relat.searchTechByPro(rela.getRelation(), rela.getChargeId());
		// search by technology
		List<Tech> listTechByPerson = relat.searchTechByPer(rela.getRelation(), rela.getUserName());
		
		relat.close();
		
		
		model.addAttribute("listByPro", listTechByProject);
		model.addAttribute("listByPer", listTechByPerson);
		
		model.addAttribute("relation", rela.getRelation());

		return "searchTechByRela";
	}
	
	// Search Project by relationship
	@RequestMapping(value = {"/searchProjectByRela"}, method = RequestMethod.GET)
	public String searchProjectByRela(@Validated Rela rela, Model model){
		Relationship relat = new Relationship("bolt://localhost:7687", "neo4j", "11111111");
		// search by project
		List<Task> listProjectByPerson = relat.searchProByPer(rela.getRelation(), rela.getUserName());
		// search by technology
		List<Task> listrojectByTech = relat.searchProByTech(rela.getRelation(), rela.getTechName());
		
		relat.close();
		
		model.addAttribute("listByPer", listProjectByPerson);
		model.addAttribute("listByTech", listrojectByTech);
		
		model.addAttribute("relation", rela.getRelation());

		return "searchProjectByRela";
	}
}
