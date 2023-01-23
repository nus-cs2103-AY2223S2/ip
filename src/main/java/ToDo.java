public class ToDo extends Task {
  public ToDo(String description) {
    super(description);
  }

  @Override
  public String toString() {
    return "[T]" + super.toString();
  }

  @Override
  public String fileOutput() {
    return String.format("T|%d|%s", this.isDone ? 1 : 0, this.description);
  }
}
