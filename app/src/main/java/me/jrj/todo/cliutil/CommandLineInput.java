package me.jrj.todo.cliutil;

/**
 * Defines the set of valid commands, appending its corresponding key mapping and description
 *
 * Any invalid command is assigned a special enum value of INVALID_INPUT. Part of the
 * cli util library for the use of the CliApp.
 */

public enum CommandLineInput {
  FIND_ALL_ITEM('a', "(a)ll items"),
  FIND_ITEM_BY_ID('f', "(f)ind item a specific item by ID"),
  INSERT_ITEM('i', "(i)nsert a new item"),
  UPDATE_ITEM('u', "(u)pdate an existing item"),
  DELETE_ITEM('d', "(d)elete an existing item"),
  EXIT_APP('e', "(e)xit"),
  INVALID_INPUT('\0', "Invalid option. Try again.");

  private final char aKey;
  private final String aDescription;

  CommandLineInput(char pKey, String pDescription) {
    aKey = pKey;
    aDescription = pDescription;
  }

  public char getKey() {
    return aKey; 
  }

  public String getDescription() {
    return aDescription;
  }

  public boolean isValid() {
    return this != INVALID_INPUT;
  }

  /**
   * Get the corresponding action mapped to the key entered in command line.
   *
   * @param pKey the input character representing a command 
   * @return the corresponding CommandLineInput enum constant, or INVALID_INPUT if no match
   *
   */
  public static CommandLineInput getAction(char pKey) {
    for (CommandLineInput i : values()) {
      if (Character.toLowerCase(pKey)==i.aKey) return i;
    }
    return INVALID_INPUT;
  }
}
