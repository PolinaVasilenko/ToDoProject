package ru.java.todo.MyToDo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import lombok.val;
import ru.java.utils.Settings;

public class DBsource {
	
	private Settings instance;

	public DBsource(){
		instance = Settings.getInstance();
	}

	public List<ToDo> getToDo(String sql, Boolean isAll, String strFind) throws ClassNotFoundException, SQLException {
		
		List<ToDo> listResult = new ArrayList<>();

		try (Connection conn = DriverManager.getConnection(instance.CONNECTION_STR, instance.CONNECTION_USERNAME, instance.CONNECTION_PASSWORD);
				Statement statement = conn.createStatement()) {

			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				ToDo toDo = new ToDo();
				toDo.setId(resultSet.getInt(1));
				toDo.setNameToDo(resultSet.getString(2));
				toDo.setAuthor(resultSet.getString(3));
				toDo.setTime(resultSet.getDate(4));
				toDo.setStatus(resultSet.getBoolean(5));				
				toDo.setNode(resultSet.getInt(6));
				listResult.add(toDo);
			}

		}

		return listResult;

	}

	public Author getUser(String name) throws ClassNotFoundException, SQLException {
			
		Author author = new Author();

		try (Connection conn = DriverManager.getConnection(instance.CONNECTION_STR, instance.CONNECTION_USERNAME, instance.CONNECTION_PASSWORD);
				Statement statement = conn.createStatement()) {

			String sql = String.format(instance.sqlFindName, name);
			
			ResultSet resultSet = statement.executeQuery(sql);
			
			if (resultSet.next()) {
				author.setId(resultSet.getInt(1));
				author.setName(resultSet.getString(2));
			}
		}

		return author;
	}
	
	
	public boolean addUser(String name) throws ClassNotFoundException, SQLException {		

			
		try (Connection conn = DriverManager.getConnection(instance.CONNECTION_STR, instance.CONNECTION_USERNAME, instance.CONNECTION_PASSWORD);
				Statement statement = conn.createStatement()) {
		
			val sqlInsert = String.format(instance.sqlNameInsert, name);
			statement.executeUpdate(sqlInsert);
			
		}		
		
		return true;
	}
	
	
	
	public int getMaxNode(String sql) throws ClassNotFoundException, SQLException {
		
        int max = 0;		
        try (Connection conn = DriverManager.getConnection(instance.CONNECTION_STR, instance.CONNECTION_USERNAME, instance.CONNECTION_PASSWORD);
				Statement statement = conn.createStatement()) {
			
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
			 max = resultSet.getInt(1);
				
			}			
			
		}
		
		
		return max;
	}

	public boolean insertToDo(String sql) throws ClassNotFoundException, SQLException {

		try (Connection conn = DriverManager.getConnection(instance.CONNECTION_STR, instance.CONNECTION_USERNAME, instance.CONNECTION_PASSWORD);
				Statement statement = conn.createStatement()) {	
			
			statement.executeUpdate(sql);
			System.out.println("Ok!");

		}

		return true;

	}
	
	

}
