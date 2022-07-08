package ru.java.layer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.val;
import ru.java.todo.MyToDo.Author;
import ru.java.todo.MyToDo.DBsource;
import ru.java.utils.Settings;

public class SubLayer extends Layer {

	DBsource dBsource;
	Settings instance;
	

	SubLayer() {
		dBsource = new DBsource();
		instance = Settings.getInstance();
	}

	@Override
	public LayerResult process(String field, Author author) throws ClassNotFoundException, SQLException {

		String[] paramVal = field.split(",");

		val aqlFindMaxNode = "SELECT MAX (node) FROM todo_description";

		int maxNode = dBsource.getMaxNode(aqlFindMaxNode);

		val sqlToDoInsert = "INSERT INTO public.todo_description (nametodo,author,deadline,status,node) VALUES ('%s',%s,'%s',false,%s)";

		val sqlVal = String.format(sqlToDoInsert, paramVal[0], paramVal[2], paramVal[1], "false", maxNode);

		System.err.println(sqlVal);

		try {
			dBsource.insertToDo(sqlVal);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		LayerResult layerResult = new LayerResult();
		List<String> commands = new ArrayList<String>();
		commands.add(instance.START);
		commands.add(instance.NAME);
		commands.add(instance.ALL_TASK);
		commands.add(instance.UNREADY_TASK);
		commands.add(instance.FINISH_TASK);
		commands.add(instance.NEW_TASK);

		layerResult.setAutor(author);
		layerResult.setCommands(commands);

		return layerResult;
	}

}
