enum Command {
  BYE("bye", 0),
  LIST("list", 0),
  ADD("", 0);

  private final String command;
  private final int args;

  private Command(String command, int args) {
    this.command = command;
    this.args = args;
  }

  public boolean matches(String input) {
    String[] arr = input.split(" ");
    return arr[0].equals(command) && arr.length == args + 1;
  }
}
