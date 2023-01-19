public class Deadline extends Task {
  private final String time;

  public Deadline(String task, String time) {
    super(task);
    this.time = time;
  }

  @Override
  public String getType() { return "D"; }

  @Override
  public String toString() {
    return String.format("%s (by: %s)", super.toString(), time);
  }
}
