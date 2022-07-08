package ru.java.todo.MyToDo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import lombok.val;
import ru.java.layer.Layer;
import ru.java.layer.LayerExit;
import ru.java.layer.LayerNewTask;
import ru.java.layer.LayerResult;
import ru.java.layer.LayerShowAllTask;
import ru.java.layer.LayerUser;
import ru.java.utils.Settings;

public class Runner {

	private DBsource dBsource;
	private Settings instance;
	private Map<String, Layer> mapLayers;

	private int poolTask = 0;
	private Author currentAutor;
	LayerResult currentLayer;

	Runner() {
		dBsource = new DBsource();
		instance = Settings.getInstance();
		fillMapLayer();
	}

	private void fillMapLayer() {
		mapLayers = new HashMap<String, Layer>();

		Layer layer = new LayerNewTask();
		layer.setMessage(instance.createNewTask);
		layer.setCommands(Arrays.asList(instance.EXIT));
		mapLayers.put(instance.NEW_TASK, layer);
		//
		layer = new LayerExit();
		layer.setMessage(instance.EXIT);
		layer.setCommands(Arrays.asList(instance.EXIT));
		mapLayers.put(instance.EXIT, layer);
		//
		layer = new LayerUser();
		layer.setMessage(instance.beginStr);
		layer.setCommands(Arrays.asList(instance.EXIT));
		mapLayers.put(instance.NAME, layer);

		layer = new LayerShowAllTask();
		layer.setMessage(instance.seeTask);
		layer.setCommands(Arrays.asList(instance.EXIT));
		mapLayers.put(instance.ALL_TASK, layer);

	}

	private String user;

	public void run() throws ClassNotFoundException, SQLException {

		boolean isRun = true;
		currentLayer = new LayerResult();
		currentLayer.setCommands(Arrays.asList(instance.NAME));

		while (isRun) {

			currentLayer.getCommands().forEach(v -> {

				if (mapLayers.containsKey(v)) {
					System.out.println(mapLayers.get(v).getMessage());
				}

			});
			;

			Scanner sc = new Scanner(System.in);
			String line = sc.nextLine();

			String command = testLineValid(line);

			if (command.length() > 0 && !command.isEmpty()) {
				Layer layer = mapLayers.get(command);

				currentLayer = layer.process(line, currentAutor);

				if (!currentLayer.getAutor().getName().isEmpty()) {
					currentAutor = currentLayer.getAutor();
				}

			} else {
				System.out.println(instance.wrongCommand);
			}

		}


	}

	private String testLineValid(String line) {

		String command = "";
		String[] arrayLine = null;
		int countSetParam = 1;

		if (line.contains(",")) {
			arrayLine = line.split(",");
			command = arrayLine[0].trim();
			countSetParam = arrayLine.length;
		} else {
			command = line;
		}

		if (currentLayer.getCommands().contains(command)) {

			if (instance.mapCommands.containsKey(command)) {

				val countParam = instance.mapCommands.get(command);

				if (countParam == countSetParam) {
					return command;
				}
			}

		}
		return "";

	}
	

	public void inserUser() throws SQLException {

		try (Connection conn = DriverManager.getConnection(instance.CONNECTION_STR, instance.CONNECTION_USERNAME,
				instance.CONNECTION_PASSWORD); Statement statement = conn.createStatement();) {

			String sql = "insert into todo_owner (name) values('Timur')";
			statement.executeUpdate(sql);
		}

	}
	

	public void inserTodoDescription() throws SQLException {

		try (Connection conn = DriverManager.getConnection(instance.CONNECTION_STR, instance.CONNECTION_USERNAME,
				instance.CONNECTION_PASSWORD); Statement statement = conn.createStatement();) {

			String sql = "insert into TODO_DESCRIPTION ( nametodo, author, deadline, status, node, sub_task_id) values('read a book', 1 , '2021-12-17 18:10:00', true, 3, 1)";
			statement.executeUpdate(sql);

		}

	}
	

	public List<String> listSql() {
		List<String> sqlList = new ArrayList<>();
		sqlList.add(
				"CREATE TABLE IF NOT EXISTS TODO_DESCRIPTION (id INTEGER AUTO_INCREMENT, nametodo VARCHAR(100), author  INTEGER, deadline TIMESTAMP, \r\n"
						+ "status   BOOLEAN, node  INTEGER, sub_task_id  INTEGER);");

		sqlList.add("CREATE TABLE IF NOT EXISTS todo_owner (id INTEGER auto_increment, name VARCHAR(100));");
		return sqlList;
	}

	//создает таблицу

	public void createTable() {
		try (Connection conn = DriverManager.getConnection(instance.CONNECTION_STR, instance.CONNECTION_USERNAME,
				instance.CONNECTION_PASSWORD); Statement statement = conn.createStatement();) {
			List<String> sqlList = listSql();
			sqlList.forEach(sql -> {
				try {
					statement.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			});

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
