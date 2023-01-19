/**
 * Given a task and a time range, constructs a Task event.
 *
 * @param task     The event to be completed.
 * @param from     The event's start.
 * @param to     The event's end.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */

public class TaskEvent extends Task{
  protected String from;
  protected String to;

  public TaskEvent(String task, String from, String to) {
    super(task);
    this.from = from;
    this.to = to;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString()
            + String.format(" (%s%s)", this.from, this.to);
  }
}