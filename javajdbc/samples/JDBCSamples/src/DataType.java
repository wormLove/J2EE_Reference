/*
 * Copyright 2003 Sun Microsystems, Inc.  ALL RIGHTS RESERVED.
 * Use of this software is authorized pursuant to the terms of the license found at
 * http://developer.java.sun.com/berkeley_license.html.
 */ 

public class DataType {

	private int code;
	private String SQLType;
	private String localType = null;
	private String params = null;
	private boolean needsSetting = true; 
	
	public DataType(int code, String SQLType) {
		this.code = code;
		this.SQLType = SQLType;
	
	}
	
	public boolean needsToBeSet() {
		return needsSetting;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getSQLType() {
		return SQLType;
	}
	
	public String getLocalType() {
		return localType;
	}
	
	public String getParams() {
		return params;
	}
	
	public void setLocalTypeAndParams(String local, String p) {
		if (needsSetting) {
			localType = local;
			params = p;
			needsSetting = false;
		}
	}
}

