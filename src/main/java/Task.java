public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
      this.description = description;
      this.isDone = false;
  }

  public static class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
  }

  public static class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
  }

  public static class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;

    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + "to: " + to + ")";
    }
  }

  public String getStatusIcon() {
      return (isDone ? "X" : " "); // mark done task with X
  }

  public void markDone() {
      this.isDone = true;
  }

  public void markUndone() {
      this.isDone = false;
  }

  @Override
  public String toString() {
      String result = String.format("[%s] %s", this.getStatusIcon(), this.description);
      return result;
  }
}