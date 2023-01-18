public class Deadline extends Task {
  public Deadline(String task) {
    super(task);
  }

  @Override
  public String toString() {
    return String.format(
      "[D][%c] %s",
      getStatusIcon(), title
    );
  }
}
