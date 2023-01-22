public class Task1 {
  public String string;
  public Boolean mark;

  public Task1(String string) {
    this.string = string;
    this.mark = false;
  }
  public void mark() {
    this.mark = true;
  }
  public void demark() {
    this.mark = false;
  }
  public void printType() {
  }
  public void printTime() {
    System.out.println();
  }
}
