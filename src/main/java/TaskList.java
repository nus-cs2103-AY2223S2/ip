import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public Task getTask(int id) {
    return tasks.get(id - 1);
  }

  public boolean addTask(Task task) {
    return tasks.add(task);
  }

  public Task removeTask(int id) {
    return tasks.remove(id - 1);
  }

  public void markTask(int id, boolean done) {
    tasks.get(id - 1).mark(done);
  }

  public int count() {
    return tasks.size();
  }

  public String printTask(int id) {
    return tasks.get(id - 1).toString();
  }

  public String[] generateList() {
    String[] list = new String[count()];
    for (int i = 0; i < count(); i++) {
      Task t = tasks.get(i);
      list[i] = String.format("%d: %s", i + 1, t);
    }
    return list;
  }
}
