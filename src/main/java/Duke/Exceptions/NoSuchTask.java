package Duke.Exceptions;

public class NoSuchTask extends DukeException {
  public int index;
  public String noSuchTask;
  public NoSuchTask(int i) {
    this.index = i + 1;
    this.noSuchTask = "There is no Duke.task " + index + " in the list";
  }
  @Override
  public String toString() {
    return noSuchTask;
  }
}
