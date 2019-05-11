package com.ifi.profile.service;

import static org.neo4j.driver.Values.parameters;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Session;
import org.neo4j.driver.StatementResult;
import org.neo4j.driver.Transaction;

import com.ifi.profile.model.Task;


public class Project {
	Driver driver;
	
	 public static final String uri = "bolt://localhost:7687";
	 public static final String user = "neo4j";
	 public static final String password = "11111111";
	
	public Project(String uri, String user, String password){
		driver = GraphDatabase.driver(uri,AuthTokens.basic(user,password));
	}
	
	// Add project
	public Task addProject(String project){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MERGE (p:Project{project:$project})",
						parameters("project",project));
				tx.success();
			}
		}
		return task;
	}
	
	// add or update more properties  	
	public Task addProjectId(String project, String chargeid){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						"SET p.chargeid= $chargeid",
						parameters("project",project, "chargeid",chargeid));
				tx.success();
			}
		}
		return task;
	}
	
	public Task addProjectStatus(String project, String status){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						"SET p.status= $status",
						parameters("project",project, "status",status));
				tx.success();
			}
		}
		return task;
	}
	
	public Task addProjectDescription(String project, String description){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						 "SET p.description= $description",
						parameters("project",project, "description",description));
				tx.success();
			}
		}
		return task;
	}
	
	public Task addProjectDomain(String project, String domain){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						" SET p.domain= $domain",
						parameters("project",project, "domain",domain));
				tx.success();
			}
		}
		return task;
	}
	
	public Task addProjectStart(String project, String startdate){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						" SET p.startdate= $startdate",
						parameters("project",project, "startdate",startdate));
				tx.success();
			}
		}
		return task;
	}
	
	public Task addProjectFinish(String project, String finishdate){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						"SET p.finishdate= $finishdate",
						parameters("project",project, "finishdate",finishdate));
				tx.success();
			}
		}
		return task;
	}
	
	public Task addProjectCustomer(String project, String customer){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						"SET p.customer= $customer",
						parameters("project",project, "customer",customer));
				tx.success();
			}
		}
		return task;
	}
	
	// Search project
	 public List<Task> searchProject(String initial){
	    	List<Task> task = new ArrayList<Task>();
	        try (Session session = driver.session()){
	        	 StatementResult result = session.run(
	                     "MATCH (a:Project) WHERE a.chargeid = {x} RETURN a.project AS project, a.chargeid AS chargeid, a.status AS status, a.description AS description, a.domain AS domain, a.startdate AS startdate, a.finishdate AS finishdate, a.customer AS customer",
	                     parameters("x", initial));
	             // Each Cypher execution returns a stream of records.
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
	    
	
	// Show list project
	public List<Task> getListTech(String initial)
    {
    	List<Task> ret = new ArrayList<Task>();
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (p:Project) WHERE p.project STARTS WITH {x} RETURN p.project AS project",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
            	Task tmpTask = new Task();
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                tmpTask.setProject(record.get("project").asString());
                ret.add(tmpTask);
            }
        }
        
        return ret;
    }
	
	// remove a project
	public Task removeProject(String project){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Project{project: $project}) DETACH DELETE p",parameters("project",project));
				tx.success();
			}
		}
		return task;
	}
	
	public void close(){
		driver.close();
	}

}
