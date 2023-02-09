package Duke.Exceptions;

import Duke.task.Task;

public class EmptyTime extends DukeException {
  public String emptyTime;
  public EmptyTime(Task task) {
    this.emptyTime = "OOPS!!! The time of a "
        + task.toString() +  " cannot be empty." +
        "\n missing due date(for deadline) OR \n" +
        "missing start time or end time(for event)";
  }
  @Override
  public String toString() {
    return emptyTime;
  }
}
