package ru.java.todo.MyToDo;

import java.sql.SQLException;

import lombok.Data;

@Data

public class Main {	
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {		

		Runner runner = new Runner();
		runner.createTable();
		
		runner.inserUser ();
		runner.inserTodoDescription();
		
		runner.run();

		System.out.println("Ok!");

	}
	
	

	

}
