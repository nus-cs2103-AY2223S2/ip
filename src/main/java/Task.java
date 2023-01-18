abstract class Task {
  protected String title;
  protected boolean isDone;

  public Task(String title) {
    this.title = title;
    this.isDone = false;
  }

  public void mark(boolean done) {
    this.isDone = done;
  }

  protected char getStatusIcon() {
    return isDone ? 'X' : ' ';
  }
}
