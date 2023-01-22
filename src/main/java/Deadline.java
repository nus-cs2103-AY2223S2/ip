public class Deadline extends Task1{
  public String time;
  public Deadline(String string, String time) {
    super(string);
    this.time = time;
  }
  @Override
  public void printType() {
    System.out.print("[D]");
  }

  @Override
  public void printTime() {
    System.out.println(" (by: " + time + ")");
  }

  @Override
  public String toString() {
    return "deadline";
  }
}
