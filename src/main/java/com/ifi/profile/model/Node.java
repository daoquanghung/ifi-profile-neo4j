package com.ifi.profile.model;

import java.util.List;

public class Node {
	private String typeNode;
	
	private String labelNode;
	
	private List<Field> listFields;

	public String getLabelNode() {
		return labelNode;
	}

	public void setLabelNode(String labelNode) {
		this.labelNode = labelNode;
	}

	public String getTypeNode() {
		return typeNode;
	}

	public void setTypeNode(String typeNode) {
		this.typeNode = typeNode;
	}

	public List<Field> getListFields() {
		return listFields;
	}

	public void setListFields(List<Field> listFields) {
		this.listFields = listFields;
	}
}
