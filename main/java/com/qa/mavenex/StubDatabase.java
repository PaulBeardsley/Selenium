package com.qa.mavenex;

public class StubDatabase implements QADatabase {

	@Override
	public String getUsernameByID(int id) {
		String[] names = {"Bob", "Anna", "Mike", "David", "Lily", "Fred", "Kimberly"};
		if(id < names.length)
			return(names[id]);
		else
			return null;
	}

}
