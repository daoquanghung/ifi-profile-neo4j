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


import com.ifi.profile.model.User;

public class Person {

	// Driver objects are thread-safe and are typically made available application-wide.
    Driver driver;
    
    public static final String uri = "bolt://localhost:7687";
    public static final String user = "neo4j";
    public static final String password = "11111111";
    
    public Person(String uri, String user, String password)
    {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    // set constraint for person
    public void addConstraint(){
    	try(Session session = driver.session()){
    		try(Transaction tx = session.beginTransaction()){
    			tx.run("CREATE CONSTRAINT ON (p:Person) ASSERT p.id IS UNIQUE");
    			tx.success();
    		}
    	}
    }
    
   
    public User addPersonName(String name){
    	User user = new User();
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session())
        {
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
            try (Transaction tx = session.beginTransaction())
            {
                tx.run("MERGE (a:Person {name: {x}})", parameters("x", name));
                tx.success();  // Mark this write as successful.
            }
        }
        
        return user;
    }
    
    // add person id
    public User addPersonId(String name, String id){
    	User user = new User();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $name}) " +
						"SET p.id= $id",
						parameters("name", name, "id",id));
				tx.success();
			}
		}
		return user;
	}
    
    // add person title
    public User addPersonTitle(String name, String title){
    	User user = new User();
		try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $name}) " +
						"SET p.title= $title",
						parameters("name", name, "title",title));
				tx.success();
			}
		}
		return user;
	}
    
    // add person birthday
    public User addPersonBirthday(String name, int birthday){
    	
		User user = new User();
    	try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $name}) " +
						"SET p.birthday= $birthday", //"SET p.birthday= "+birthday ,
						parameters("name", name, "birthday",birthday+""));
				tx.success();
			}
		}
    	return user;
	}
    
    public User addPersonJoin(String name, int join){
		User user = new User();
    	try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $name}) " +
						"SET p.join= $join",
						parameters("name", name, "join",join));
				tx.success();
			}
		}
    	return user;
	}
    
    public User addPersonStatus(String name, String status){
		User user = new User();
    	try(Session session = driver.session()){
			try(Transaction tx = session.beginTransaction()){
				tx.run("MATCH (p:Person{name: $name}) " +
						"SET p.status= $status",
						parameters("name", name, "status",status));
				tx.success();
			}
		}
    	return user;
	}

   
 // remove person 
 	public User removePerson(String id){
 		User user = new User();
 		try(Session session = driver.session()){
 			try(Transaction tx = session.beginTransaction()){
 				tx.run("MATCH (p:Person{id: $id}) DETACH DELETE p",parameters("id",id));
 				tx.success();
 			}
 		}
 		return user;
 	}
      	
 	// show list person
 	
    public List<User> getListPeople(String initial)
    {
    	List<User> ret = new ArrayList<User>();
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (a:Person) WHERE a.name STARTS WITH {x} RETURN a.name AS name",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
            	User tmpUser = new User();
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                tmpUser.setUserName(record.get("name").asString());
                ret.add(tmpUser);
            }
        }
        
        return ret;
    }
    
    // search person
    public List<User> searchPeople(String initial){
    	List<User> user = new ArrayList<User>();
        try (Session session = driver.session()){
        	 StatementResult result = session.run(
                     "MATCH (a:Person) WHERE a.name STARTS WITH {x} RETURN a.name AS name, a.id AS id, a.title AS title, a.birthday AS birthday, a.join AS join, a.status AS status",
                     parameters("x", initial));
             // Each Cypher execution returns a stream of records.
        	while(result.hasNext()){
        		 Record record = result.next();
        		 User tmpUser = new User();
        		 
    			 tmpUser.setUserName(record.get("name").asString());
    			 tmpUser.setUserId(record.get("id").asString());
    			 tmpUser.setTitle(record.get("title").asString());
    			 try{
    				 tmpUser.setBirthday(record.get("birthday").asInt());
    			 } catch (Exception ex){
    				 tmpUser.setBirthday(Integer.parseInt(record.get("birthday").asString()));
    			 }
    			 try{
    				 tmpUser.setJoin(record.get("join").asInt());
    			 } catch (Exception ex){
    				 tmpUser.setJoin(Integer.parseInt(record.get("join").asString()));
    			 }
	   			 
    			 tmpUser.setStatus(record.get("status").asString());
    			 
    			 user.add(tmpUser);
        		
        	 }
             
        }
        
        return user;
    }
    
    
     
    public void close()
    {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }
}
