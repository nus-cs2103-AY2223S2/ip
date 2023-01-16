public class Task {
  protected String description;
  protected boolean isDone = false;
  protected int id;
  protected static int runningId = 1;

  public Task(String description) {
    this.id = runningId;
    runningId++;
    this.description = description;
  }

  public void setDone() {
    this.isDone = true;
  }

  public void setUndone() {
    this.isDone = false;
  }

  @Override
  public String toString() {
    char marker = this.isDone ? 'X' : ' ';
    return String.format("[%c] %s", marker, this.description);
  }
}
