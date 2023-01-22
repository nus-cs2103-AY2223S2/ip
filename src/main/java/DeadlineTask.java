public class DeadlineTask extends Task{
  private String date;

  public DeadlineTask(String name, String date) {
    super(name);
    this.date = date;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date + ")";
  }
}
