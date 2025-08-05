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

  @PostMapping("/clearCompleted")
  public String clearCompletedItem(Model model) {
    List<ToDoItem> completedItems = repository.findByCompletionStatus(true);
    for (ToDoItem item : completedItems) {
      repository.delete(item);
    }
    // redirecting calls the same thing, not DRY
    // model.addAttribute("toDoListItems", repository.findAll()); 
    return "redirect:/";
  }

  @PostMapping("/insert")
  public String insertNewItem(@RequestParam("pName") String pName, Model model) {
    repository.insert(pName);
    return "redirect:/";
  }

  @GetMapping("/id")
  public String findItemById(@RequestParam("pId") Long pId,  Model model) {
    model.addAttribute("toDoListitems", repository.findById(pId));
    return "home";
  }

  @PatchMapping("/completionStatus") 
  public String findItemByCompletionStatus(@RequestParam("pIsCompleted") boolean pIsCompleted, Model model) {
     List<ToDoItem> completedItems = repository.findByCompletionStatus(true);
    return "redirect:/";
  }

  @PatchMapping("/update")
  public String updateItem(@RequestParam("pToDoItem") ToDoItem pToDoItem, Model model) {
    repository.update(pToDoItem);
    return "redirect:/";  
  }

  /**
   * work around using forms, to refactor to true REST using JS
   */
  @PostMapping("/delete")
  public String deleteItem(@RequestParam("pId") Long pId, Model model) {
    repository.delete(repository.findById(pId).get());
    return "redirect:/";
  }

}
