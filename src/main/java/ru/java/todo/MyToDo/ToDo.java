package ru.java.todo.MyToDo;

import java.sql.Date;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(exclude = { "id", "node", "sub_task_id"})
public class ToDo {
	
	Integer id;
	String nameToDo;
	String author;
	Date time;
	Boolean status;
	Integer node;
	Integer sub_task_id;	

}
