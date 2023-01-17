public class Deadline extends Task {
  private String date;

  /**
   * Constructor for a Deadline Task
   * 
   * @param name - The name of the task
   * @param date - The date of when the task is due
   */
  Deadline(String name, String date) {
    super(name);
    this.date = date;
  }

  @Override
  public String toString() {
    return "[D]" + super.toString() + " (by: " + this.date + ")";
  }

}
