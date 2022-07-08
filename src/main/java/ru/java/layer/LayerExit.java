package ru.java.layer;

import ru.java.todo.MyToDo.Author;

public class LayerExit extends Layer{

	@Override
	public LayerResult process(String field, Author author) {
		System.exit(0);
		return null;
	}

}
