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
	public Office department(String department){
		Office office = new Office();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MERGE (d:Department{department:$department})",
						parameters("department",department));
				tx.success();
			}
		}
		return office;
	}
	
	public Office description(String department, String description){
		Office office = new Office();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (d:Department{department:$department})" +
						"SET d.description = $description",
						parameters("department",department, "description",description));
				tx.success();
			}
		}
		return office;
	}
	
	 // remove department 
 	public Office removeDepartment(String department){
 		Office office = new Office();
 		try(Session session = driver.session()){
 			try(Transaction tx = session.beginTransaction()){
 				tx.run("MATCH (d:Department{department: $department}) DETACH DELETE p",parameters("department",department));
 				tx.success();
 			}
 		}
 		return office;
 	}
	
 // search person
    public List<Office> searchDepartment(String initial){
    	List<Office> office = new ArrayList<Office>();
        try (Session session = driver.session()){
        	 StatementResult result = session.run(
                     "MATCH (a:Department) WHERE a.Department STARTS WITH {x} RETURN a.department AS department, a.description AS description",
                     parameters("x", initial));
             // Each Cypher execution returns a stream of records.
        	while(result.hasNext()){
        		 Record record = result.next();
        		 Office tmpOff = new Office();
        		 
        		 tmpOff.setDepartment(record.get("department").asString());
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
