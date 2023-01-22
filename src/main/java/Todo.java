public class Todo extends Task1{
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
}
