package me.jrj.todo.repository;

import me.jrj.todo.model.ToDoItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 * Use of ConcurrentHasMap handles null for us gracefully
 */
public class InMemoryToDoRepository implements ToDoRepository {
  private AtomicLong currentId = new AtomicLong();
  private ConcurrentHashMap<Long, ToDoItem> toDos = new ConcurrentHashMap<Long, ToDoItem>();

  @Override
  public List<ToDoItem> findAll() {
    List<ToDoItem> res = new ArrayList<ToDoItem>(toDos.values());
    Collections.sort(res);
    return Collections.unmodifiableList(res);
  }

  @Override
  public Optional<ToDoItem> findById(Long id) {
    return Optional.ofNullable(toDos.get(id)); 
  }

  @Override
  public ToDoItem insert(String name) {
    Long newId = currentId.incrementAndGet();
    ToDoItem newItem = new ToDoItem(newId, name, false); 
    toDos.putIfAbsent(newId, newItem);
    return newItem;
  }

  /**
   * the library handles null for us gracefully
   */
  @Override
  public void update(ToDoItem pToDoItem) {
    toDos.replace(pToDoItem.id(), findById(pToDoItem.id()).get(), pToDoItem);
    return;
  }

  /**
   * the library handles null for us gracefully
   */
  @Override
  public void delete(ToDoItem pToDoItem) {
    toDos.remove(pToDoItem.id());
    return;
  }
}
