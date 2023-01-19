public class InvalidCommandException extends DukeException{
  public InvalidCommandException() {
    super("Sorry, I don't know what that line means. You could try typing from our list of commands:\n"
    + Duke.commandList.toString());
  }
}
