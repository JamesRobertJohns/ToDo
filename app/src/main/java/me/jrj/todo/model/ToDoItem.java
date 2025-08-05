package me.jrj.todo.model;

/**
 * An immutable data record modelling a single to-do task 
 *
 * @param id Unique identifier of the to-do item
 * @param name Description of the to-do item 
 * @param isCompleted Indicates whether the task is completed 
 * 
 * */
public record ToDoItem(Long id, String name, boolean isCompleted) implements Comparable<ToDoItem> {
  @Override
  public int compareTo(ToDoItem other) {
    return this.id.compareTo(other.id);
  }

  public ToDoItem toggleCompletionStatus() {
    return new ToDoItem(id, name, !isCompleted);
  }
}

