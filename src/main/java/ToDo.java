public class ToDo extends Task {
  public ToDo(String title) {
    super(title);
  }

  @Override
  public String toSaveFormat() {
    return String.format(
      "T | %d | %s",
      getStatusNo(), title
    );
  }

  @Override
  public String toString() {
    return String.format(
      "[T][%c] %s",
      getStatusIcon(), title
    );
  }
}
