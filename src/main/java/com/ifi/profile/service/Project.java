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
import com.ifi.profile.model.Tech;

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
	public Task addProjectId(String project, String chargeId){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						"SET p.chargeId= $chargeId",
						parameters("project",project, "chargeId",chargeId));
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
	
	public Task addProjectStart(String project, String startDate){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						" SET p.startDate= $startDate",
						parameters("project",project, "startDate",startDate));
				tx.success();
			}
		}
		return task;
	}
	
	public Task addProjectFinish(String project, String finishDate){
		Task task = new Task();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAIN ON (p:Project) ASSERT p.chargId IS UNIQUE" +
						"MATCH (p:Project{project:$project})"+
						"SET p.finishDate= $finishDate",
						parameters("project",project, "finishDate",finishDate));
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
	
	public void printProject(){
		try(Session session = driver.session()){
			StatementResult result = session.run("MATCH (p:Project) RETURN p.project AS project");
			while(result.hasNext()){
				Record record = result.next();
				System.out.println(record.get("project").asString());
			}
		}
	}
	
	public List<Tech> getListTech(String initial)
    {
    	List<Tech> ret = new ArrayList<Tech>();
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (t:Technology) WHERE t.name STARTS WITH {x} RETURN t.name AS name",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
            	Tech tmpTech = new Tech();
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                tmpTech.setTechName(record.get("name").asString());
                ret.add(tmpTech);
            }
        }
        
        return ret;
    }
	
	// remove a project
	public void removeProject(String project){
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Project{project: $project}) DETACH DELETE p",parameters("project",project));
				tx.success();
			}
		}
	}
	
	public void close(){
		driver.close();
	}

}
