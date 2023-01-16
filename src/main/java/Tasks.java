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

  public void addTask(Task task) {
    tasks.add(task);
    System.out.println("Got it. I've added this task:");
    System.out.printf("=> %s\n", task);
    System.out.printf("Now you have %d tasks in the list\n", length());
  }

  public int length() {
    return tasks.size();
  }
  
  public void markTask(int index) {
    Task t = getTask(index);
    if (t == null) {
      System.out.println("Sorry, the task does not exist.");
      return;
    }
    t.mark();
    System.out.println("Nice! I've marked this task as done:");
    System.out.printf("=> %s\n", t);
  }

  public void unmarkTask(int index) {
    Task t = getTask(index);
    if (t == null) {
      System.out.println("Sorry, the task does not exist.");
      return;
    }
    t.unmark();
    System.out.println("OK, I've marked this task as not done yet:");
    System.out.printf("=> %s\n", t);
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
