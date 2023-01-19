/**
 * Given a task description, construct a Task ToDo object.
 *
 * @param s The description of the task.
 *
 * @author JamesLiuZX
 * AY2223-S2 CS2103T
 */

public class TaskToDo extends Task {
  public TaskToDo(String s) {
    super(s);
  }
  @Override
  public String toString() {
    return "[T]" + super.toString();
  }
}
