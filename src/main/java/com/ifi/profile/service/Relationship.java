package com.ifi.profile.service;

import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.List;

/*import java.util.ArrayList;
import java.util.List;*/

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.StatementResult;
import org.neo4j.driver.Transaction;

import com.ifi.profile.model.Rela;
import com.ifi.profile.model.User;
import com.ifi.profile.model.Tech;
import com.ifi.profile.model.Task;

public class Relationship {
	Driver driver;
	 public static final String uri = "bolt://localhost:7687";
	 public static final String user = "neo4j";
	 public static final String password = "11111111";
	
	public Relationship(String uri, String user, String password){
		driver = GraphDatabase.driver(uri,AuthTokens.basic(user,password));
	}
	
	// relationship between person and department
	public Rela relaPersonDepart(String userId, String department){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{id: $id}) " +
						"MATCH (d:Department{name: $name}) " +
						"CREATE (p)-[r:BELONG_TO]->(d)",
						parameters("id", userId, "name", department)
						);
				tx.success();
			}
		}
		return rela;
	}
	
	// print out relationship between person and department
	public Rela printRelaPD(){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			StatementResult result = session.run("MATCH (p)-[:BELONG_TO]->(d) RETURN p.name, d.department");
			while(result.hasNext()){
				Record record = result.next();
				System.out.println(String.format("%s belong to %s", record.get("p.name").asString(),record.get("d.department").asString()));
			}
		}	
		return rela;
	}
	
	// relationship between project and technology
	public Rela relaProTech(String techName, String chargeId){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (t:Technology{name: $name}) " +
						"MATCH (P:Project{chargeid: $chargeid}) " +
						"CREATE (t)-[:USED_IN]->(P)",
						parameters("name", techName, "chargeid", chargeId)
						);
				tx.success();
			}
		}
		return rela;
	}
	
	// print out relationship between project and technology
	public Rela printRelaPT(){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			StatementResult result = session.run("MATCH (t)-[:USED_IN]->(p) RETURN t.techName, p.name");
			while(result.hasNext()){
				Record record = result.next();
				System.out.println(String.format("%s used in %s", record.get("t.techName").asString(),record.get("p.name").asString()));
			}
		}	
		return rela;
	}
	
	// relationship between person and technology
	public Rela relaPerTech(String userId, String techName){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{id: $id}) " +
						"MATCH (t:Technology{name: $name}) " +
						"CREATE (p)-[r:HAS_EXPERIENCE]->(t)",
						parameters("id", userId, "name", techName)
						);
				tx.success();
			}
		}
		return rela;
	}
	
	// print out rela btw person and tech
	public Rela printRelaPt(){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			StatementResult result = session.run("MATCH (p)-[:HAS_EXPERIENCE]->(t) RETURN p.name, t.techName");
			while(result.hasNext()){
				Record record = result.next();
				System.out.println(String.format("%s has experience %s", record.get("p.name").asString(),record.get("t.techName").asString()));
			}
		}	
		return rela;
	}
	
	// relationship between person and project
	public Rela relaPerPro(String userId, String chargeId){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{id: $id}) " +
						"MATCH (P:Project{chargeid: $chargeid}) " +
						"CREATE (p)-[r:WORK_IN]->(P)",
						parameters("id", userId, "chargeid", chargeId)
						);
				tx.success();
			}
		}
		return rela;
	}	
	// print out rela btw person and project
	public Rela printRelaPP(){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			StatementResult result = session.run("MATCH (p)-[:WORK_IN]->(P) RETURN p.name, P.name");
			while(result.hasNext()){
				Record record = result.next();
				System.out.println(String.format("%s work in %s", record.get("p.name").asString(),record.get("P.name").asString()));
			}
		}
		return rela;
	}
	

	
	public Rela printRelaPp(){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			StatementResult result = session.run("MATCH (p)-[:LEAD]->(P) RETURN p.name, P.name");
			while(result.hasNext()){
				Record record = result.next();
				System.out.println(String.format("%s lead %s", record.get("p.name").asString(),record.get("P.name").asString()));
			}
		}	
		return rela;
	}
	
	
	// search person by relation with department
	public List<User> searchByRela(String r, String department){
		List<User> user = new ArrayList<User>();
		try(Session session = driver.session()){
			StatementResult result = session.run(
					"MATCH (a:Person)-[r]->(d:Department{name: $name}) " +
					"WHERE type(r) = $r " +
					"RETURN a.name AS name, a.id AS id, a.title AS title, a.birthday AS birthday, a.join AS join, a.status AS status ",
					parameters("r", r, "name",department)
					);
			while(result.hasNext()){
       		 Record record = result.next();
       		 User tmpUser = new User();
       		 
   			 tmpUser.setUserName(record.get("name").asString());
   			 tmpUser.setUserId(record.get("id").asString());
   			 tmpUser.setTitle(record.get("title").asString());
   			tmpUser.setBirthday(record.get("birthday").asInt());
   			tmpUser.setJoin(record.get("join").asInt());
   			 tmpUser.setStatus(record.get("status").asString());
   			 
   			 user.add(tmpUser);
			}
		}
		
		return user;
	}
	
	// search person by relation with project
		public List<User> searchByProject(String r, String chargeId){
			List<User> user = new ArrayList<User>();
			try(Session session = driver.session()){
				StatementResult result = session.run(
						"MATCH (a:Person)-[r]->(p:Project{chargeid: $chargeid}) " +	
						"WHERE type(r) = $r " +
						"RETURN a.name AS name, a.id AS id, a.title AS title, a.birthday AS birthday, a.join AS join, a.status AS status ",
						parameters("r", r, "chargeid",chargeId)
						);
				while(result.hasNext()){
	       		 Record record = result.next();
	       		 User tmpUser = new User();
	       		 
	   			 tmpUser.setUserName(record.get("name").asString());
	   			 tmpUser.setUserId(record.get("id").asString());
	   			 tmpUser.setTitle(record.get("title").asString());
	   			tmpUser.setBirthday(record.get("birthday").asInt());
	   			tmpUser.setJoin(record.get("join").asInt());
	   			 tmpUser.setStatus(record.get("status").asString());
	   			 
	   			 user.add(tmpUser);
				}
			}
			
			return user;
		}
		
		// search person by relation with technology
		public List<User> searchByTech(String r, String name){
			List<User> user = new ArrayList<User>();
			try(Session session = driver.session()){
				StatementResult result = session.run(	
						"MATCH (a:Person)-[r]->(t:Technology{name: $name}) " +
						"WHERE type(r) = $r " +
						"RETURN a.name AS name, a.id AS id, a.title AS title, a.birthday AS birthday, a.join AS join, a.status AS status ",
						parameters("r", r, "name",name)
						);
				while(result.hasNext()){
	       		 Record record = result.next();
	       		 User tmpUser = new User();
	       		 
	   			 tmpUser.setUserName(record.get("name").asString());
	   			 tmpUser.setUserId(record.get("id").asString());
	   			 tmpUser.setTitle(record.get("title").asString());
	   			tmpUser.setBirthday(record.get("birthday").asInt());
	   			tmpUser.setJoin(record.get("join").asInt());
	   			 tmpUser.setStatus(record.get("status").asString());
	   			 
	   			 user.add(tmpUser);
				}
			}
			
			return user;
		}
	
	// search technology by relation
	
	// Search technology by person
		public List<Tech> searchTechByPer(String r, String name){
			List<Tech> tech = new ArrayList<Tech>();
			try(Session session = driver.session()){
				StatementResult result = session.run(	
						"MATCH (a:Person{name: $name})-[r]->(t:Technology) " +
						"WHERE type(r) = $r " +
						"RETURN t.name AS name, t.description AS description, t.category AS category, t.domain AS domain",
						parameters("r", r, "name",name)
						);
				while(result.hasNext()){
	       		 Record record = result.next();
	       		 Tech tmpTech = new Tech();
	       		 
	   			 tmpTech.setTechName(record.get("name").asString());
	   			 tmpTech.setTechDescription(record.get("description").asString());
	   			 tmpTech.setTechCategory(record.get("category").asString());
	   			 tmpTech.setTechDomain(record.get("domain").asString());
	   			 
	   			 tech.add(tmpTech);
				}
			}
			
			return tech;
		}
		
	// Search technology by Project
		public List<Tech> searchTechByPro(String r, String chargeId){
			List<Tech> tech = new ArrayList<Tech>();
			try(Session session = driver.session()){
				StatementResult result = session.run(	
						"MATCH (t:Technology)-[r]->(p:Project{chargeid :$chargeid}) " +
						"WHERE type(r) = $r " +
						"RETURN t.name AS name, t.description AS description, t.category AS category, t.domain AS domain",
						parameters("r", r, "chargeid",chargeId)
						);
				while(result.hasNext()){
	       		 Record record = result.next();
	       		 Tech tmpTech = new Tech();
	       		 
	   			 tmpTech.setTechName(record.get("name").asString());
	   			 tmpTech.setTechDescription(record.get("description").asString());
	   			 tmpTech.setTechCategory(record.get("category").asString());
	   			 tmpTech.setTechDomain(record.get("domain").asString());
	   			 
	   			 tech.add(tmpTech);
				}
			}
			
			return tech;
		}
		
	// search project by relation
		
		// Search project by person
		public List<Task> searchProByPer(String r, String name){
			List<Task> task = new ArrayList<Task>();
			try(Session session = driver.session()){
				StatementResult result = session.run(
						"MATCH (p:Person{name: $name})-[r]->(a:Project) " +	
						"WHERE type(r) = $r " +
						"RETURN a.project AS project, a.chargeid AS chargeid, a.status AS status, a.description AS description, a.domain AS domain, a.startdate AS startdate, a.finishdate AS finishdate, a.customer AS customer ",
						parameters("r", r, "name",name)
						);
				while(result.hasNext()){
	       		 Record record = result.next();
	       		 Task tmpTask = new Task();
	       		 
	       		tmpTask.setProject(record.get("project").asString());
	       		tmpTask.setChargeid(record.get("chargeid").asString());
	       		tmpTask.setProStatus(record.get("status").asString());
	       		tmpTask.setProDescription(record.get("description").asString());
	       		tmpTask.setProDomain(record.get("domain").asString());
	       		tmpTask.setStartdate(record.get("startdate").asString());
	       		tmpTask.setFinishdate(record.get("finishdate").asString());
	       		tmpTask.setCustomer(record.get("customer").asString());
	   			 
	   			task.add(tmpTask);
				}
			}
			
			return task;
		}
		
		// Search project by Technology
		public List<Task> searchProByTech(String r, String name){
			List<Task> task = new ArrayList<Task>();
			try(Session session = driver.session()){
				StatementResult result = session.run(
						"MATCH (t:Technology{name: $name})-[r]->(a:Project) " +	
						"WHERE type(r) = $r " +
						"RETURN a.project AS project, a.chargeid AS chargeid, a.status AS status, a.description AS description, a.domain AS domain, a.startdate AS startdate, a.finishdate AS finishdate, a.customer AS customer ",
						parameters("r", r, "name",name)
						);
				while(result.hasNext()){
	       		 Record record = result.next();
	       		 Task tmpTask = new Task();
	       		 
	       		tmpTask.setProject(record.get("project").asString());
	       		tmpTask.setChargeid(record.get("chargeid").asString());
	       		tmpTask.setProStatus(record.get("status").asString());
	       		tmpTask.setProDescription(record.get("description").asString());
	       		tmpTask.setProDomain(record.get("domain").asString());
	       		tmpTask.setStartdate(record.get("startdate").asString());
	       		tmpTask.setFinishdate(record.get("finishdate").asString());
	       		tmpTask.setCustomer(record.get("customer").asString());
	   			 
	   			task.add(tmpTask);
				}
			}
			
			return task;
		}
		
	public void close(){
		driver.close();
	}

}
