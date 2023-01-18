import java.util.ArrayList;

public class TaskList {
  private ArrayList<String> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public boolean addTask(String item) {
    return tasks.add(item);
  }

  public String generateList() {
    StringBuilder sb = new StringBuilder();
    int i = 1;
    for (String task : tasks) {
      String line = String.format("  %d: %s\n", i, task);
      sb.append(line);
      i++;
    }
    return sb.toString();
  }
}
