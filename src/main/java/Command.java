enum Command {
  BYE("bye"),
  LIST("list"),
  MARK("mark"),
  UNMARK("unmark"),
  TODO("todo"),
  EVENT("event"),
  DEADLINE("deadline"),
  DELETE("delete");

  private final String command;

  private Command(String command) {
    this.command = command;
  }

  public boolean matches(String command) {
    return command.equals(this.command);
  }
}
