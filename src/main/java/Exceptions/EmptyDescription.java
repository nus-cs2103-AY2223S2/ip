package Exceptions;

import task.Task;

public class EmptyDescription extends DukeException {
  public String emptyDescription;
  public EmptyDescription(Task task) {
    this.emptyDescription = "OOPS!!! The description of a "
        + task.toString() +  " cannot be empty.";
  }
  @Override
  public String toString() {
    return emptyDescription;
  }
}
