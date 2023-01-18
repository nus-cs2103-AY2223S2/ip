enum Command {
  BYE("bye"),
  LIST("list"),
  MARK("mark"),
  UNMARK("unmark"),
  TODO("todo"),
  EVENT("event"),
  DEADLINE("deadline"),
  DEFAULT("");

  private final String command;

  private Command(String command) {
    this.command = command;
  }

  public boolean matches(String input) {
    String[] arr = input.split(" ", 2);
    return arr[0].equals(command);
  }
}
