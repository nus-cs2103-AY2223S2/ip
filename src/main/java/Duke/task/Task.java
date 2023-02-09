package Duke.task;

public class Task {
  public String string;
  public Boolean mark;

  public Task(String string) {
    this.string = string;
    this.mark = false;
  }
  public void mark() {
    this.mark = true;
  }
  public void unmark() {
    this.mark = false;
  }
  public void printType() {
  }
  public void printTime() {
    System.out.println();
  }
  public String taskString(){ return ""; }
}
