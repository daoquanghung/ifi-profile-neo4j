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

public class Relationship {
	Driver driver;
	 public static final String uri = "bolt://localhost:7687";
	 public static final String user = "neo4j";
	 public static final String password = "11111111";
	
	public Relationship(String uri, String user, String password){
		driver = GraphDatabase.driver(uri,AuthTokens.basic(user,password));
	}
	
	// relationship between person and department
	public Rela relaPersonDepart(String userName, String department){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $name}) " +
						"MATCH (d:Department{department: $department}) " +
						"CREATE (p)-[r:BELONG_TO]->(d)",
						parameters("name", userName, "department", department)
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
	public Rela relaProTech(String techName, String project){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (t:Technology{technology: $technology}) " +
						"MATCH (P:Project{project: $project}) " +
						"CREATE (t)-[:USED_IN]->(P)",
						parameters("technology", techName, "project", project)
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
	public Rela relaPerTech(String userName, String techName){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $name}) " +
						"MATCH (t:Technology{technology: $technology}) " +
						"CREATE (p)-[r:HAS_EXPERIENCE]->(t)",
						parameters("name", userName, "technology", techName)
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
	public Rela relaPerPro(String userName, String project){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $name}) " +
						"MATCH (P:Project{project: $project}) " +
						"CREATE (p)-[r:WORK_IN]->(P)",
						parameters("name", userName, "project", project)
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
	
	// still relation btw person project but this's one for leader
	public Rela relaPersonProject2(String userName, String project){
		Rela rela = new Rela();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $userName}) " +
						"MATCH (P:Project{project: $project}) " +
						"CREATE (p)-[r:LEAD]->(P)",
						parameters("userName", userName, "project", project));
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
	public List<User> searchByRela(String rela){
		List<User> user = new ArrayList<User>();
		try(Session session = driver.session()){
			StatementResult result = session.run(
					"MATCH (a:Person)-[r:BELONG_TO]->(d:Department)" +
					"RETURN a.name AS name, a.id AS id, a.title AS title, a.birthday AS birthday, a.join AS join, a.status AS status ",
					parameters("rela", rela)
					);
			while(result.hasNext()){
       		 Record record = result.next();
       		 User tmpUser = new User();
       		 
   			 tmpUser.setUserName(record.get("name").asString());
   			 tmpUser.setUserId(record.get("id").asString());
   			 tmpUser.setTitle(record.get("title").asString());
   			 tmpUser.setBirthday(record.get("birthday").asString());
   			 tmpUser.setJoin(record.get("join").asString());
   			 tmpUser.setStatus(record.get("status").asString());
   			 
   			 user.add(tmpUser);
			}
		}
		
		return user;
	}
	
	// search person by relation with project
		public List<User> searchByProject(String rela){
			List<User> user = new ArrayList<User>();
			try(Session session = driver.session()){
				StatementResult result = session.run(
						"MATCH (a:Person)-[r:WORK_IN]->(p:Project) " +	
						"RETURN a.name AS name, a.id AS id, a.title AS title, a.birthday AS birthday, a.join AS join, a.status AS status ",
						parameters("rela", rela)
						);
				while(result.hasNext()){
	       		 Record record = result.next();
	       		 User tmpUser = new User();
	       		 
	   			 tmpUser.setUserName(record.get("name").asString());
	   			 tmpUser.setUserId(record.get("id").asString());
	   			 tmpUser.setTitle(record.get("title").asString());
	   			 tmpUser.setBirthday(record.get("birthday").asString());
	   			 tmpUser.setJoin(record.get("join").asString());
	   			 tmpUser.setStatus(record.get("status").asString());
	   			 
	   			 user.add(tmpUser);
				}
			}
			
			return user;
		}
		
		// search person by relation with technology
		public List<User> searchByTech(String rela){
			List<User> user = new ArrayList<User>();
			try(Session session = driver.session()){
				StatementResult result = session.run(	
						"MATCH (a:Person)-[r:HAS_EXPERIENCE]->(t:Technology) " +
						"RETURN a.name AS name, a.id AS id, a.title AS title, a.birthday AS birthday, a.join AS join, a.status AS status ",
						parameters("rela", rela)
						);
				while(result.hasNext()){
	       		 Record record = result.next();
	       		 User tmpUser = new User();
	       		 
	   			 tmpUser.setUserName(record.get("name").asString());
	   			 tmpUser.setUserId(record.get("id").asString());
	   			 tmpUser.setTitle(record.get("title").asString());
	   			 tmpUser.setBirthday(record.get("birthday").asString());
	   			 tmpUser.setJoin(record.get("join").asString());
	   			 tmpUser.setStatus(record.get("status").asString());
	   			 
	   			 user.add(tmpUser);
				}
			}
			
			return user;
		}
	
	// search 
	
	public void close(){
		driver.close();
	}

}
