import java.util.ArrayList;

public class TaskArrayList {
  private ArrayList<Task> list;

  public TaskArrayList(ArrayList<Task> list) {
    this.list = list;
  }

  public int size() {
    return list.size();
  }

  public Task get(int i) throws DukeException {
    try {
      return list.get(i);
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Unable to get task.");
    }
  }

  public void add(Task t) {
    list.add(t);
  }

  public Task remove(int i) throws DukeException {
    try {
      Task t = list.remove(i);
      return t;
    } catch (IndexOutOfBoundsException e) {
      throw new DukeException("Unable to remove task.");
    }
  }
}