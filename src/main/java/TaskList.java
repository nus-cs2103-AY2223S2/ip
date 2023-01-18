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

  public String generateList() {
    StringBuilder sb = new StringBuilder();
    int i = 1;
    for (Task task : tasks) {
      String line = String.format("  %d: %s\n", i, task);
      sb.append(line);
      i++;
    }
    return sb.toString();
  }
}
