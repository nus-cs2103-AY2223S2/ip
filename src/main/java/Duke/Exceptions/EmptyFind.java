package Duke.Exceptions;

/**
 * Exceptions when user inputs find command without
 * specific content.
 */
public class EmptyFind extends DukeException {
  public String emptyFind;

  public EmptyFind() {
    this.emptyFind = "OOPS!!! The description of a find" +
        " cannot be empty.";
  }
}
