package me.jrj.todo.cliutil;

import java.util.Scanner;
import java.util.List;
import org.apache.commons.lang3.CharUtils;
import java.util.Optional;

import me.jrj.todo.model.ToDoItem;
import me.jrj.todo.repository.ToDoRepository;
import me.jrj.todo.repository.InMemoryToDoRepository;


public class CommandLineInputHandler {
  private final Scanner scanner = new Scanner(System.in);
  private ToDoRepository repository = new InMemoryToDoRepository();

  /**
   * @pre pCmd is a valid enum constant defined CommandLineInput
   */
  public void processInput(CommandLineInput pCmd) {
    switch (pCmd) {
      case INVALID_INPUT: 
        printInvalidInputError();
        break;

      case INSERT_ITEM:
        insertToDoItem();
        break;

      case DELETE_ITEM: 
        deleteToDoItem();
        break;

      case UPDATE_ITEM:
        updateToDoItem();
        break;

      case FIND_ALL_ITEM:
        printAllToDoItem();
        break;

      case FIND_ITEM_BY_ID:
        findToDoItemById();
        break;

      case EXIT_APP:
        break;
    }
  }



  private void printBanner() {
    String banner = """
    ========================
    To Do Application 
    ========================
    """;
    System.out.println(banner);
  }

  private void printOptions() {
    for (CommandLineInput i : CommandLineInput.values()) {
      if (i.isValid()) System.out.println(i.getDescription());
    }
  }

  private void printAllToDoItem() {
    List<ToDoItem> allItems = repository.findAll();
    if (allItems.isEmpty()) 
    System.out.println("Nothing in the list. Add items to start.");
    else {
      for (ToDoItem i : allItems) 
      System.out.println(i);
    }
  }

  public void printMenu() {
    printBanner();
    System.out.println("Select from the options below: ");
    printOptions();
    System.out.println("\n> Enter option: ");
  }

  private void printInvalidInputError() {
    System.out.println(CommandLineInput.INVALID_INPUT.getDescription());
  }

  /**
     * handles null
     */
  public CommandLineInput readInput() {
    char key = CharUtils.toChar(scanner.nextLine(), '\0');
    return CommandLineInput.getAction(key);
  }

  /**
   *
   *
   * */
  private Long requestToDoItemId() {
    while(true) {
      System.out.println("Enter ID of the To Do Item: ");
      String id = scanner.nextLine();
      try {
        return Long.parseLong(id);
      } catch (NumberFormatException e) {
        System.out.println("Invalid ID. Enter valid numerals. Try again.");
      }
    }
  }

  private void deleteToDoItem() {
    Long itemId = requestToDoItemId();
    Optional<ToDoItem> optItem = repository.findById(itemId);
    if (optItem.isPresent()) {
      repository.delete(optItem.get());
      System.out.println("Item: " + optItem.get() + " deleted successfully.");
    } 
    else {
      System.out.println("Item is not found in To Do list.");
    }
    return;
  }

  private void updateToDoItem() {
    Long itemId = requestToDoItemId();
    Optional<ToDoItem> optItem = repository.findById(itemId);
    if (optItem.isPresent()) {
      String newName = "";     
      do {
        System.out.println("Enter the new name of the To Do Item: ");
        newName = scanner.nextLine();
      } while(newName.isEmpty());
      boolean newStatus = false;
      int tmp = 0;
      while(tmp != 1 && tmp != 2) {
        System.out.println("Set the completion status: "); 
        System.out.println("""
          1. True
          2. False
          """);
        try {
          tmp = Integer.parseInt(scanner.nextLine());
          newStatus = tmp==1? true : false;
        } catch (NumberFormatException e) {
          System.out.println("Enter only valid numerals.");
        }
      }
      ToDoItem newItem = new ToDoItem(itemId, newName, newStatus);
      repository.update(newItem);
      System.out.println("To Do Item updated successfully.");
    }
    else {
      System.out.println("Item is not found in To Do list.");
    }
    return;
  }

  private void findToDoItemById() {
    Long itemId = requestToDoItemId();
    Optional<ToDoItem> optItem = repository.findById(itemId);
    if (optItem.isPresent()) {
      System.out.println("Item found: " + optItem.get());
    }
    else {
      System.out.println("Item is not found in To Do list.");
    }
    return;
  }

  private void insertToDoItem() {
    String name = "";
    while(name.isEmpty()) {
      System.out.println("Enter name of the task: ");
      name = scanner.nextLine();
    }
    ToDoItem item = repository.insert(name);
    System.out.println("New item created successfully with id: " + item.id());
  }

}
