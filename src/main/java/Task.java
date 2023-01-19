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
  public void demark(){
    this.mark = false;
  }
}
