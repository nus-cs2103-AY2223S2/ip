import java.util.ArrayList;

public class Tasks {
  private ArrayList<Task> tasks;

  public Tasks() {
    tasks = new ArrayList<>();
  }
  
  public boolean taskExists(int index) {
    return index >= 0 && index < tasks.size();
  }

  public Task getTask(int index) {
    if (taskExists(index)) {
      return tasks.get(index);
    }
    return null;
  }

  public void addTask(String name) {
    tasks.add(new Task(name));
  }

  public boolean markTask(int index) {
    Task t = getTask(index);
    if (t == null) {
      return false;
    }
    t.mark();
    return true;
  }

  public boolean unmarkTask(int index) {
    Task t = getTask(index);
    if (t == null) {
      return false;
    }
    t.unmark();
    return true;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < tasks.size(); i++) {
      sb.append(String.format("%d. %s\n", i+1, tasks.get(i)));
    }
    return sb.toString();
  }
}
