package Duke.task;

public class Todo extends Task {
  public Todo(String string) {
    super(string);
  }
  @Override
  public void printType() {
    System.out.print("[T]");
  }

  @Override
  public String toString() {
    return "todo";
  }
  @Override
  public String taskString() {
    String mark;
    if(this.mark) {
      mark = "[X]";
    } else {
      mark = "[ ]";
    }
    String type = "[T]";
    return type + mark + " " + this.string.trim();
  }
}
