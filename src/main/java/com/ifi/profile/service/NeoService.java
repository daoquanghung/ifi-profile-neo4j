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

import com.ifi.profile.model.Field;
import com.ifi.profile.model.Node;

public class NeoService {

	// Driver objects are thread-safe and are typically made available application-wide.
    Driver driver;

    public NeoService(String uri, String user, String password)
    {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }
    
    public void addNode(Node node)
    {
        // Sessions are lightweight and disposable connection wrappers.
        try (Session session = driver.session())
        {
        	String tmpQuery = "MERGE ("+node.getLabelNode()+" :"+node.getTypeNode();
        	if((node.getListFields()!=null)&&(!"".equals(node.getListFields()))){
        		tmpQuery += " {";
        		for (Field field : node.getListFields()) {
        			if((field.getKey()!=null)&&(!"".equals(field.getKey()))&&(field.getValue()!=null)){
        				String tmpStr = field.getKey() + ": " + field.getValue() + ",";
        				try {  
        				    Double.parseDouble(field.getValue());      				    
        				} catch(NumberFormatException e){  
        					tmpStr = field.getKey() + ": \'" + field.getValue() + "\',";
        				}
        				tmpQuery += tmpStr;
        			}
            		
    			}
        		if(",".equals(tmpQuery.substring(tmpQuery.length() - 1))){
        			tmpQuery = tmpQuery.substring(0, tmpQuery.length() - 1);
        		}
        		
        		tmpQuery += "}";
        	}
        	
        	tmpQuery += ")";
        	System.out.println("tmpQuery:"+tmpQuery);
            // Wrapping Cypher in an explicit transaction provides atomicity
            // and makes handling errors much easier.
            try (Transaction tx = session.beginTransaction())
            {
                tx.run(tmpQuery);
                tx.success();  // Mark this write as successful.
            }
        }
    }
    
    public void deleteNode(Node node){
    	try(Session session = driver.session()){
    		String tmpQuery = "MATCH ("+node.getLabelNode()+" :"+node.getTypeNode();
    		if((node.getListFields()!=null)&&(!"".equals(node.getListFields()))){
    			tmpQuery += "{";
    			for(Field field : node.getListFields()){
    				if((field.getKey()!=null)&&(!"".equals(field.getKey()))&&(field.getValue()!=null)){
    					String tmpStr = field.getKey() + ": " + field.getValue() + ",";
    					try {
							Double.parseDouble(field.getValue());
						} catch (NumberFormatException e) {
							tmpStr = field.getKey() + ": \'" + field.getValue() + "\',";
						}
    					tmpQuery += tmpStr;
    				}
    			}
    			if(",".equals(tmpQuery.substring(tmpQuery.length() - 1))){
    				tmpQuery = tmpQuery.substring(0, tmpQuery.length() - 1);
    			}
    			tmpQuery += "}";
    		}
    		tmpQuery +=") DETACH DELETE "+node.getLabelNode()+" ";
    		try(Transaction tx = session.beginTransaction()){
    			tx.run(tmpQuery);
    			tx.success();
    		}
    	}
    }
    
    // Add relationship
    public void addRelationship(Node node){
    	try(Session session = driver.session()){
    		String tmpQuery = "MATCH ";
    		try(Transaction tx = session.beginTransaction()){
    			tx.run(tmpQuery);
    			tx.success();
    		}
    	}
    }
    
    
     // Search person
    public List<Node> searchDetailPerson(Node node){
    	List<Node> list = new ArrayList<Node>();
    	try(Session session = driver.session()){
    		String tmpQuery = "MATCH ("+node.getLabelNode()+" :"+node.getTypeNode();
        	if((node.getListFields()!=null)&&(!"".equals(node.getListFields()))){
        		tmpQuery += " {";
        		for (Field field : node.getListFields()) {
        			if((field.getKey()!=null)&&(!"".equals(field.getKey()))&&(field.getValue()!=null)){
        				String tmpStr = field.getKey() + ": " + field.getValue() + ",";
        				try {  
        				    Double.parseDouble(field.getValue());      				    
        				} catch(NumberFormatException e){  
        					tmpStr = field.getKey() + ": \'" + field.getValue() + "\',";
        				}
        				tmpQuery += tmpStr;
        			}
            		
    			}
        		if(",".equals(tmpQuery.substring(tmpQuery.length() - 1))){
        			tmpQuery = tmpQuery.substring(0, tmpQuery.length() - 1);
        		}
        		
        		tmpQuery += "}";
        	}
        	
        	tmpQuery += ") RETURN "+node.getLabelNode()+"";
    		StatementResult result = session.run(tmpQuery);
    		
    		while (result.hasNext()){
    			Node tmpUser = new Node();
    			Record record = result.next();
    			for(Field field : node.getListFields()){
    				if(field.getValue()!=null){
    					tmpUser.setLabelNode(record.get(field.getValue()).asString());
    				}
    			}
    			list.add(tmpUser);
    		}
    	}
    	return list;
    }
    
    // get list person
    public List<Node> getListNodes()
    {
    	List<Node> ret = new ArrayList<Node>();
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (n:Person) RETURN n.name as name");
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
            	Node tmpUser = new Node();
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                try {
                	tmpUser.setLabelNode(record.get("name").asString());
                	System.out.println(record.get("name").asString());
                } catch (Exception ex) {
                	System.out.println("name not string:"+ex.getMessage());
                	tmpUser.setLabelNode(""+record.get("name").asInt());
                }
                
                ret.add(tmpUser);
            }
        }
        
        return ret;
    }
    
    // get list project
    public List<Node> getListProjects()
    {
    	List<Node> ret = new ArrayList<Node>();
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (n:Project) RETURN n.project as project");
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
            	Node tmpProject = new Node();
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                try {
                	tmpProject.setLabelNode(record.get("project").asString());
                	System.out.println(record.get("project").asString());
                } catch (Exception ex) {
                	System.out.println("project not string:"+ex.getMessage());
                	tmpProject.setLabelNode(""+record.get("project").asInt());
                }
                
                ret.add(tmpProject);
            }
        }
        
        return ret;
    }
    
    // get list technology
    public List<Node> getListTechnologies()
    {
    	List<Node> ret = new ArrayList<Node>();
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (n:Technology) RETURN n.name as name");
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
            	Node tmpTechnologies = new Node();
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                try {
                	tmpTechnologies.setLabelNode(record.get("name").asString());
                	System.out.println(record.get("name").asString());
                } catch (Exception ex) {
                	System.out.println("name not string:"+ex.getMessage());
                	tmpTechnologies.setLabelNode(""+record.get("name").asInt());
                }
                
                ret.add(tmpTechnologies);
            }
        }
        
        return ret;
    }
    
    // get list department
    public List<Node> getListDepartments()
    {
    	List<Node> ret = new ArrayList<Node>();
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (n:Department) RETURN n.name as name");
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
            	Node tmpDepartment = new Node();
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                try {
                	tmpDepartment.setLabelNode(record.get("name").asString());
                	System.out.println(record.get("name").asString());
                } catch (Exception ex) {
                	System.out.println("name not string:"+ex.getMessage());
                	tmpDepartment.setLabelNode(""+record.get("name").asInt());
                }
                
                ret.add(tmpDepartment);
            }
        }
        
        return ret;
    }
    public void printPeople(String initial)
    {
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (a:Person) WHERE a.name STARTS WITH {x} RETURN a.name AS name",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                System.out.println(record.get("name").asString());
            }
        }
    }
    
    public List<Node> getListPeople(String initial)
    {
    	List<Node> ret = new ArrayList<Node>();
        try (Session session = driver.session())
        {
            // Auto-commit transactions are a quick and easy way to wrap a read.
            StatementResult result = session.run(
                    "MATCH (a:Person) WHERE a.name STARTS WITH {x} RETURN a.name as name, a.id as id, a as title",
                    parameters("x", initial));
            // Each Cypher execution returns a stream of records.
            while (result.hasNext())
            {
            	Node tmpUser = new Node();
                Record record = result.next();
                // Values can be extracted from a record by index or name.
                tmpUser.setLabelNode(record.get("name").asString());
                ret.add(tmpUser);
                System.out.println(record.get("title").asMap());
            }
        }
        
        return ret;
    }

    public void close()
    {
        // Closing a driver immediately shuts down all open connections.
        driver.close();
    }
}
