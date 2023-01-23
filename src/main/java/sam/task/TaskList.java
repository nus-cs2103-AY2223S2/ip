package sam.task;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
  private ArrayList<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public Task getTask(int id) {
    if (isValidId(id)) {
      return tasks.get(id - 1);
    }
    return null;
  }

  public boolean addTask(Task task) {
    return tasks.add(task);
  }

  public Task removeTask(int id) {
    if (isValidId(id)) {
      return tasks.remove(id - 1);
    }
    return null;
  }

  public boolean markTask(int id, boolean done) {
    if (isValidId(id)) {
      getTask(id).mark(done);
      return true;
    }
    return false;
  }

  public int count() {
    return tasks.size();
  }

  public List<String> getTasks() {
    List<String> list = new ArrayList<>(count());
    for (int i = 0; i < count(); i++) {
      Task task = tasks.get(i);
      list.add(String.format("%d: %s", i + 1, task));
    }
    return list;
  }

  public List<String> findTasks(String subString) {
    List<String> list = new ArrayList<>();
    for (int i = 0; i < count(); i++) {
      Task t = tasks.get(i);
      if (t.title.contains(subString)) {
        list.add(String.format("%d: %s", i + 1, t));
      }
    }
    return list;
  }

  private boolean isValidId(int id) {
    return !(id <= 0 || id > count());
  }
}
