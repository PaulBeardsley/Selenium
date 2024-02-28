package com.qa.mavenex;

public class QAController {
	private QADatabase qaDb;
	
	public QAController(QADatabase db) {
		this.qaDb = db;
	}
	public String getUserById(int id) {
		return qaDb.getUsernameByID(id);
	}
}
