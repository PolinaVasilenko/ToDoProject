package ru.java.layer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.java.todo.MyToDo.Author;
import ru.java.todo.MyToDo.DBsource;
import ru.java.utils.Settings;

public class LayerUser extends Layer {
	
	String message;
	String sql;
	List<String> commands; 
	DBsource dBsource;
	Settings instance;
	
	
	public LayerUser(){
		dBsource =  new DBsource();
		instance = Settings.getInstance();
	}
	

	public LayerResult process(String field, Author author) {
		
		String setName = splitString(field)[1].trim();
		
		author = new Author();
		boolean isNewAuthor = false;
		
		
		try 
		{
			author  = dBsource.getUser(setName);
			if(author.getName()==null || author.getName().isEmpty()) 
			{
				dBsource.addUser(setName);
				isNewAuthor = true;
			}
			author  = dBsource.getUser(setName);
		} 
		catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		
		
		
		LayerResult layerResult = new LayerResult();
		
	
		List<String> commands = new ArrayList<String>();
		
		if(!isNewAuthor) {
			commands.add(instance.ALL_TASK);
			commands.add(instance.UNREADY_TASK);
			commands.add(instance.FINISH_TASK);
			commands.add(instance.EXIT);
		}		
	
		commands.add(instance.NEW_TASK);
		layerResult.setAutor(author);
		layerResult.setCommands(commands);
	
		
		return layerResult;
	}
	
	
	
	

}
