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

import com.ifi.profile.model.Tech;


public class Technology {
	Driver driver;
	
	 public static final String uri = "bolt://localhost:7687";
	 public static final String user = "neo4j";
	 public static final String password = "11111111";
	
	public Technology(String uri, String user, String password){
		driver = GraphDatabase.driver(uri,AuthTokens.basic(user,password));
	}
	
	// create tech
	public Tech addTechName(String techName){
		Tech tech = new Tech();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run(//"CREATE CONSTRAINT ON (t:Technology) ASSERT t.name IS UNIQUE" +
						"MERGE(t:Technology{name:$name})",
						parameters("name",techName));
				tx.success();
			}
		}
		return tech;
	}
	
	// add or update properties for tech
	public Tech addTechDescription(String techName, String techDescription){
		Tech tech = new Tech();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (t:Techonology{name: $name})"+
						"SET t.description= $description",
						parameters("name",techName ,"description", techDescription));
				tx.success();
			}
		}
		return tech;
	}
	
	
	public Tech addTechCategory(String techName, String techCategory){
		Tech tech = new Tech();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (t:Techonology{name: $name})"+
						"SET t.category= $category",
						parameters("name",techName ,"category", techCategory));
				tx.success();
			}
		}
		return tech;
	}
	
	public Tech addTechDomain(String techName, String techDomain){
		Tech tech = new Tech();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (t:Techonology{name: $name})"+
						"SET t.domain= $domain",
						parameters("name",techName ,"domain", techDomain));
				tx.success();
			}
		}
		return tech;
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
	
	public void close(){
		driver.close();
	}
	
}
