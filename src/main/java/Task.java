abstract class Task {
  protected String title;
  protected boolean isDone;

  public Task(String title) {
    this(title, false);
  }

  public Task(String title, boolean isDone) {
    this.title = title;
    this.isDone = isDone;
  }

  public void mark(boolean done) {
    this.isDone = done;
  }

  protected char getStatusIcon() {
    return isDone ? 'X' : ' ';
  }

  protected int getStatusNo() {
    return isDone ? 1 : 0;
  }

  abstract public String toSaveFormat();
}
