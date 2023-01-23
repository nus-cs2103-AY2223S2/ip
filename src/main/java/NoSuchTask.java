public class NoSuchTask extends DuckException{
  public int index;
  public String noSuchTask;
  public NoSuchTask(int i) {
    this.index = i + 1;
    this.noSuchTask = "There is no task " + index + " in the list";
  }
}
