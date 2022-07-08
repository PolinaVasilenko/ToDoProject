package ru.java.layer;

import java.sql.SQLException;
import java.util.List;

import lombok.Data;
import ru.java.todo.MyToDo.Author;
import ru.java.todo.MyToDo.DBsource;
import ru.java.utils.Settings;


@Data
public abstract class Layer {	
	

	
	private String message;
	private String sql;
	private List<String> commands; 
	private DBsource dBsource;
	private Settings instance;
	
	public abstract LayerResult process(String field, Author currentAutor) throws ClassNotFoundException, SQLException;
	
	public String[] splitString(String field) {
		
		return field.split(",");
	}
	
	
	

}
