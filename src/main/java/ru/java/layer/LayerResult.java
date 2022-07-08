package ru.java.layer;



import java.util.List;

import lombok.Data;
import ru.java.todo.MyToDo.Author;


@Data
public class LayerResult {
	
	private  Author autor;
	private List<String> commands;
}
