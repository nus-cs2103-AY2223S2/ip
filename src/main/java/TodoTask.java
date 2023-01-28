public class TodoTask extends Task{
  public TodoTask(String name) {
    super(name);
  }

  public TodoTask(String name, Boolean isDone) {
    super(name, isDone);
  }

  @Override
  public String toString() {
    return "[T] " + super.toString();
  }
}
