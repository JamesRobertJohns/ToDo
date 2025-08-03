package me.jrj.todo.web.controller;

import me.jrj.todo.model.ToDoItem;
import me.jrj.todo.repository.InMemoryToDoRepository;
import me.jrj.todo.repository.ToDoRepository;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller 
public class ToDoController {
  private ToDoRepository repository = new InMemoryToDoRepository();

  @GetMapping("/")
  public String homePage(Model model) {
    model.addAttribute("toDoListItems", repository.findAll());
    return "home";
  }  

  @GetMapping("/clearCompleted")
  public String clearCompletedItems() {
    List<ToDoItem> completedItems = repository.findByCompletionStatus(true);
    for (ToDoItem item : completedItems) {
      repository.delete(item);
    }
    return "home";
  }

  @GetMapping("/insert")
  public String insertNewToDoItem(@RequestParam String name) {
    repository.insert(name);
    return "home";
  }


  /**
  @GetMapping("/id")


  @GetMapping("/update")

  @GetMapping("/delete")
  */

}
