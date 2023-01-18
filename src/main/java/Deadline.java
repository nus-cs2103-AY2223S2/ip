public class Deadline extends Task {
  public Deadline(String title) {
    super(title);
  }

  @Override
  public String toString() {
    return String.format(
      "[D][%c] %s",
      getStatusIcon(), title
    );
  }
}
