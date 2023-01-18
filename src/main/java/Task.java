abstract class Task {
  protected String title;
  protected boolean isDone;

  public Task(String task) {
    this.title = task;
    this.isDone = false;
  }

  public void mark(boolean done) {
    this.isDone = done;
  }

  protected char getStatusIcon() {
    return isDone ? 'X' : ' ';
  }
}
