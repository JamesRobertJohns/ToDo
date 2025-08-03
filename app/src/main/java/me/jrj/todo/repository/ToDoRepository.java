package me.jrj.todo.repository;

import me.jrj.todo.model.ToDoItem;
import java.util.List;
import java.util.Optional;

public interface ToDoRepository {
  List<ToDoItem> findAll();

  /**
   * Retrieves the To Do Item by its id
   *
   * @pre id must not be null
   * @param id the unique identifier id of the To Do Item
   * @return An {@link Optional} containing the found {@link ToDoItem}, or {@link Optional#empty()} if not found. 
   */
  Optional<ToDoItem> findById(Long id);

  ToDoItem insert(String toDoItemName);

  /**
   * Updates an Existing To Do Item
   *
   * @param pToDoItem the existing To Do Item to be updated
   */
  void update(ToDoItem pToDoItem);

  /**
   *
   */
  void delete(ToDoItem pToDoItem);

  List<ToDoItem> findByCompletionStatus(boolean pIsCompleted);
}
