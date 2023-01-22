public class Event extends Task1{
  public String startTime;
  public String endTime;
  public Event(String string, String startTime, String endTime) {
    super(string);
    this.startTime = startTime;
    this.endTime = endTime;
  }
  @Override
  public void printType() {
    System.out.print("[E]");
  }
  @Override
  public void printTime() {
    System.out.println(" (from: " + startTime
        + " to: " + endTime + ")");
  }
}
