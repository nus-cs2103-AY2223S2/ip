/**
 * Parent Task object that represents each task inputted by the user.
 *
 * @param description     The task name to be completed.
 * @param isDone          The task's completion status denoted by X or " "
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */

public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
    this.description = description;
    this.isDone = false;
  }

  public String getStatusIcon() {
    return (isDone ? "X" : " "); // mark done task with X
  }

  public void markDone() {
    this.isDone = true;
  }

  public void markUndone() {
    this.isDone = false;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s",
            this.isDone ? "X": " ", this.description);
  }
}
