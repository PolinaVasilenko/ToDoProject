package ru.java.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Settings {
	
	public static Settings instance;
	
	private Settings()
	{
		
	}
	
	public static Settings getInstance() {
		
		if(instance == null) {
			instance = new Settings();
			instance.comands = Arrays.asList("all-task","unr-task","finish-task");	
			
			mapCommands = new HashMap<String, Integer>();
			mapCommands.put(START,2);
			mapCommands.put(NEW_TASK,3);
			mapCommands.put(ALL_TASK,1);
			mapCommands.put(UNREADY_TASK,3);
			mapCommands.put(FINISH_TASK,3);
			mapCommands.put(EXIT,3);
			mapCommands.put(NAME,2);			
		}
		
		return instance;
	}
	
	
	public static final String CONNECTION_STR = "jdbc:h2:~/test";
	public static final String CONNECTION_USERNAME = "root";
	public static final String CONNECTION_PASSWORD = "mypassword";
	
	public static List<String> comands;
	public static Map<String, Integer> mapCommands;
	
	public static String maskAllTask = "Привет %s !\r\n" + "Введи 'all-task', чтобы посмотреть весь список твоих задач,\r\n";
	public static String maskUnrTask = "Привет %s !\r\n" + "Введи 'unr-task', чтобы посмотреть невыполненные задачи \r\n";
	public static String maskFinishTask = "Привет %s !\r\n" + "Введи 'finish-task', чтобы посмотреть выполненные задачи \r\n";
	public static String maskNewTask = "Привет %s !\r\n" + "Введи 'new-task', чтобы посмотреть выполненные задачи \r\n";
	
	public static String sql2 = "select d.id , d.nametodo, t.name , d.deadline , d.status , d.previous , d.nextstep,   d.node \r\n" + 
			"from \r\n  todo_description d,\r\n" + 
			"todo_owner t\r\n" + 
			"where 1=1 \r\n" + 
			"and d.author = t.id";
	
	public static String sqlAllTask = "select d.id , d.nametodo, t.name , d.deadline , d.status , d.previous , d.nextstep,   d.node \r\n" + 
			"				from\r\n" + 
			"				todo_description d, \r\n" + 
			"				todo_owner t \r\n" + 
			"				where 1=1 \r\n" + 
			"				and d.author = t.id and t.name = '%s';";
	
	
	public static String sqlUnreadTask = "select d.id , d.nametodo, t.name , d.deadline , d.status , d.previous , d.nextstep,   d.node \r\n" + 
			"				from\r\n" + 
			"				todo_description d, \r\n" + 
			"				todo_owner t \r\n" + 
			"				where 1=1 \r\n" + 
			"				and d.author = t.id and t.\"name\" = '%s'and status = false;"; 
	
	public static String sqlFinishTask = "select d.id , d.nametodo, t.name , d.deadline , d.status , d.previous , d.nextstep,   d.node \r\n" + 
			"				from\r\n" + 
			"				todo_description d, \r\n" + 
			"				todo_owner t \r\n" + 
			"				where 1=1 \r\n" + 
			"				and d.author = t.id and t.name = '%s'and status = true;";
	
	
	//
	public static String ALL_TASK = "all-task";
	public static String NEW_TASK = "new-task";
	public static String UNREADY_TASK = "unredy-task";
	public static String FINISH_TASK = "finish-task";
	public static String EXIT = "exit";
	public static String START = "start";
	public static String NAME = "name";
	
	public static String sqlFindName = "select id, name from todo_owner  where name = '%s';";
	
	public static String sqlNameInsert = "insert into todo_owner (name) values('%s');";
	
    public static String beginStr = "Введите свое имя! Пример: name, Pavel";
	
	public static String strMaskHiNew = "Привет %s , вы у нас впервые, добро пожаловать!";
	
	public static String wrongCommand = "Не верная команда, повторите снова";
	
	public static String readyTask = "У вас все задачи выполнены!";
	
	public static String haventTask = "У вас таких задач нет!";
	
	public static String seeTask = "Хочешь посмотреть задачи? Напиши команду " + ALL_TASK ;
	
	public static String createNewTask = "Хочешь создать новую задачу напиши наименование и через запятую вермя исполнения \n пример: " + NEW_TASK + ", Составить резюме, 2021-12-06 10:23:00";
	
	public static String unreadyTask = "Невыполненные задачи";	
	
	

}
