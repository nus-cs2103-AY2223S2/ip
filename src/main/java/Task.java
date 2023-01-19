public abstract class Task {
  public final String task;
  public boolean done;

  public Task(String task) {
    this.task = task;
  }

  public abstract String getType();

  @Override
  public String toString() {
    return String.format("[%s][%s] %s", getType(),this.done ? "X" : " ", this.task);
  }
}
