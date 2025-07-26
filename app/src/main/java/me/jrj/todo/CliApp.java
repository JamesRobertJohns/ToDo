package me.jrj.todo;

import me.jrj.todo.cliutil.CommandLineInput;
import me.jrj.todo.cliutil.CommandLineInputHandler;

public class CliApp {
  public static void main(String args[]) {
    CommandLineInputHandler clihandler = new CommandLineInputHandler();
    CommandLineInput cmd;
    clihandler.printMenu();
    do {
      cmd = clihandler.readInput();
      clihandler.processInput(cmd);
    } while(cmd != CommandLineInput.EXIT_APP);
    return;
  }
}
