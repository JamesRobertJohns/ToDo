package me.jrj.todo.web.controller;

import me.jrj.todo.model.ToDoItem;
import me.jrj.todo.repository.InMemoryToDoRepository;
import me.jrj.todo.repository.ToDoRepository;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @GetMapping("/displayAllTasks") 
  public String displayAllTasks() {
    return "redirect:/";
  }

  @PostMapping("/clearCompletedTasks")
  public String clearCompletedTasks(Model model) {
    List<ToDoItem> completedItems = repository.findByCompletionStatus(true);
    for (ToDoItem item : completedItems) {
      repository.delete(item);
    }
    // redirecting calls the same thing, not DRY
    // model.addAttribute("toDoListItems", repository.findAll()); 
    return "redirect:/";
  }

  @PostMapping("/insertNewTask")
  public String insertNewTask(@RequestParam("pName") String pName, Model model) {
    repository.insert(pName);
    return "redirect:/";
  }

  @GetMapping("/id")
  public String findTaskById(@RequestParam("pId") Long pId,  Model model) {
    model.addAttribute("toDoListitems", repository.findById(pId));
    return "home";
  }

  @GetMapping("/taskWithCompletionStatus") 
  public String findTaskByCompletionStatus(@RequestParam("pIsCompleted") boolean pIsCompleted, Model model) {
     List<ToDoItem> completedItems = repository.findByCompletionStatus(pIsCompleted);
    model.addAttribute("toDoListItems", completedItems);
    return "home";
  }

  @PostMapping("/toggleCompletionStatus") 
  public String toggleItemCompletionStatus(@RequestParam("pId") Long pId, Model model) {
    repository.update(repository.findById(pId).get().toggleCompletionStatus());  
    return "redirect:/";
  }
    

  @PostMapping("/updateTask")
  public String updateTask(@RequestParam("pId") Long pId, @RequestParam("pName") String pName, @RequestParam("pStatus") boolean pStatus, Model model) {
    ToDoItem newItem = new ToDoItem(pId, pName, pStatus);
    repository.update(newItem);
    return "redirect:/";  
  }

  /**
   * work around using forms, to refactor to "true" REST using JS
   */
  @PostMapping("/deleteTask")
  public String deleteTask(@RequestParam("pId") Long pId, Model model) {
    repository.delete(repository.findById(pId).get());
    return "redirect:/";
  }

}
