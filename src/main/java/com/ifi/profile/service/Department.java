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

import com.ifi.profile.model.Office;



public class Department {
	Driver driver;
	
	 public static final String uri = "bolt://localhost:7687";
	 public static final String user = "neo4j";
	 public static final String password = "11111111";
	
	public Department(String uri, String user, String password){
		driver = GraphDatabase.driver(uri,AuthTokens.basic(user,password));
	}
	
	// create department
	public Office department(String name){
		Office office = new Office();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("CREATE (d:Department{name:$name})",
						parameters("name",name));
				tx.success();
			}
		}
		return office;
	}
	
	public Office description(String name, String description){
		Office office = new Office();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (d:Department{name:$name})" +
						"SET d.description = $description",
						parameters("name",name, "description",description));
				tx.success();
			}
		}
		return office;
	}
	
	 // remove department 
 	public Office removeDepartment(String name){
 		Office office = new Office();
 		try(Session session = driver.session()){
 			try(Transaction tx = session.beginTransaction()){
 				tx.run("MATCH (d:Department{name: $name}) DETACH DELETE d",parameters("name",name));
 				tx.success();
 			}
 		}
 		return office;
 	}
	
 // search department
    public List<Office> searchDepartment(String initial){
    	List<Office> office = new ArrayList<Office>();
        try (Session session = driver.session()){
        	 StatementResult result = session.run(
                     "MATCH (d:Department) WHERE d.name STARTS WITH {x} RETURN d.name AS name, d.description AS description",
                     parameters("x", initial));
             // Each Cypher execution returns a stream of records.
        	while(result.hasNext()){
        		 Record record = result.next();
        		 Office tmpOff = new Office();
        		 
        		 tmpOff.setName(record.get("name").asString());
        		 tmpOff.setDescription(record.get("description").asString());
    			
    			 office.add(tmpOff);
        		
        	 }
             
        }
        
        return office;
    }
    
    // Print department
    public List<Office> printDepartment(){
    	List<Office> office = new ArrayList<Office>();
        try (Session session = driver.session()){
        	 StatementResult result = session.run(
                     "MATCH (d:Department) RETURN d.name AS name, d.description AS description"
                     );
             // Each Cypher execution returns a stream of records.
        	while(result.hasNext()){
        		 Record record = result.next();
        		 Office tmpOff = new Office();
        		 
        		 tmpOff.setName(record.get("name").asString());
        		 tmpOff.setDescription(record.get("description").asString());
    			
    			 office.add(tmpOff);
        		
        	 }
             
        }
        
        return office;
    }
    
	public void close(){
		driver.close();
	}
}
