public class Task {
  private String title;
  private boolean isDone;

  public Task(String task) {
    this.title = task;
    this.isDone = false;
  }

  public void mark(boolean done) {
    this.isDone = done;
  }

  @Override
  public String toString() {
    return String.format(
      "[%c] %s",
      isDone ? 'X' : ' ',
      title
    );
  }
}
