public class Deadline extends Task {
  protected String by;

  public Deadline(String title, String by) {
    super(title);
    this.by = by;
  }

  @Override
  public String toSaveFormat() {
    return String.format(
      "D | %d | %s | %s",
      getStatusNo() , title, by
    );
  }

  @Override
  public String toString() {
    return String.format(
      "[D][%c] %s (by: %s)",
      getStatusIcon(), title, by
    );
  }
}
