public class Task {
  public final String task;
  public boolean done;

  public Task(String task) {
    this.task = task;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", this.done ? "X" : " ", this.task);
  }
}
