public abstract class Task {
  private final String task;
  private boolean done;

  public Task(String task) {
    this.task = task;
  }

  public void setDone(boolean value) {
    this.done = value;
  }

  public abstract String getType();

  @Override
  public String toString() {
    return String.format("[%s][%s] %s", getType(),this.done ? "X" : " ", this.task);
  }
}
