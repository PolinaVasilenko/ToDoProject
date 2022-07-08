package ru.java.layer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.val;
import ru.java.todo.MyToDo.Author;
import ru.java.todo.MyToDo.DBsource;
import ru.java.todo.MyToDo.ToDo;
import ru.java.utils.Settings;

public class LayerShowAllTask extends Layer {

	DBsource dBsource;
    Settings instance;
    
	public LayerShowAllTask() {
		dBsource = new DBsource();
		instance = Settings.getInstance();
	}
	
	
	@Override
	public LayerResult process(String field, Author author) throws ClassNotFoundException, SQLException {

		String[] paramVal = field.split(",");
		
		val sqlAllTask = "select id, nametodo, author, deadline,status,node  from  public.todo_description where author="+ author.getId();
		
		List<ToDo> listToDo = new ArrayList<ToDo>();	

		try 
		{
			listToDo = dBsource.getToDo(sqlAllTask, null, null);
		} 
		catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
		}
		
		listToDo.forEach(v->{
			System.out.println(String.format("Id = %s, описание %s, срок выполенения: %s" ,  v.getId(), v.getNameToDo(), v.getTime()));
		});
		
		LayerResult layerResult = new LayerResult();
		List<String> commands = new ArrayList<String>();
		commands.add(instance.ALL_TASK);
		commands.add(instance.UNREADY_TASK);
		commands.add(instance.FINISH_TASK);	
		commands.add(instance.NEW_TASK);
		
		layerResult.setAutor(author);
		layerResult.setCommands(commands);

		return layerResult;
	}

}
