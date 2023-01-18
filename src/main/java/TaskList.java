import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public boolean addTask(Task task) {
    return tasks.add(task);
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
      list[i] = String.format("%d: %s", i + 1, tasks.get(i));
    }
    return list;
  }
}
