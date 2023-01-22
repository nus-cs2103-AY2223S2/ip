public class EmptyTime extends DuckException {
  public String emptyTime;
  public EmptyTime(Task1 task) {
    this.emptyTime = "☹ OOPS!!! The time of a "
        + task.toString() +  " cannot be empty." +
        "\n missing due date(for deadline) OR \n" +
        "missing start time or end time(for event)";
  }
}
