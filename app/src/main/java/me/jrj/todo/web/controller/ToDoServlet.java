package me.jrj.todo.web.controller;

import me.jrj.todo.model.ToDoItem;
import me.jrj.todo.repository.InMemoryToDoRepository;
import me.jrj.todo.repository.ToDoRepository;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
public class ToDoServlet {
  private ToDoRepository repository = new InMemoryToDoRepository();

  @GetMapping("/all")
  public List<ToDoItem> getAllToDoItems() {
    return repository.findAll(); 
  }

  /**
  @GetMapping("/id")

  @GetMapping("/insert")

  @GetMapping("/update")

  @GetMapping("/delete")
  */
}
