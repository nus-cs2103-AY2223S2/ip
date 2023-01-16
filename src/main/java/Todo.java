public class Todo extends Task {
  public Todo(String name) throws DukeException {
    super(name);
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }
}
