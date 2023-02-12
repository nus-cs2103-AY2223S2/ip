package Duke.Exceptions;

import Duke.task.Task;

public class EmptyFind extends DukeException {
  public String emptyFind;

  public EmptyFind() {
    this.emptyFind = "OOPS!!! The description of a find" +
        " cannot be empty.";
  }
}
