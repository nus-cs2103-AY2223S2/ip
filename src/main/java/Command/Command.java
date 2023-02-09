package Command;

import Exceptions.DontKnow;

public enum Command {
  TODO("todo"),
  DEADLINE("deadline"),
  EVENT("event"),
  LIST("list"),
  MARK("mark"),
  UNMARK("unmark"),
  DELETE("delete"),
  GETEVENTSON("geteventson"),
  FIND("find"),
  BYE("bye");

  private final String input;

  Command(String input) {
    this.input = input;
  }

  public static Command searchCommand (String input) throws DontKnow {
    for (Command c : values()) {
      if (c.input.equals(input)) {
        return c;
      }
    }
    throw new DontKnow();
  }
}
