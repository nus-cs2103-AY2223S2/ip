public class Task {
  protected String name;
  protected boolean completed;

  public Task(String name) throws DukeException {
    if (name.isEmpty()) {
      throw new DukeException("OOPS!!! The description of a task cannot be empty.");
    }
    this.name = name;
    this.completed = false;
  }

  public String getName() {
    return this.name;
  }

  public boolean isCompleted() {
    return this.completed;
  }

  public void mark() {
    this.completed = true;
  }

  public void unmark() {
    this.completed = false;
  }

  public String getStatusIcon() {
    return isCompleted() ? "X" : " ";
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", getStatusIcon(), getName());
  }
}
