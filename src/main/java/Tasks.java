import java.util.ArrayList;

public class Tasks {
  private ArrayList<Task> tasks;

  public Tasks() {
    tasks = new ArrayList<>();
  }
  
  public boolean taskExists(int index) {
    return index >= 0 && index < tasks.size();
  }

  public Task getTask(int index) throws DukeException {
    if (!taskExists(index)) {
      throw new DukeException("OOPS!!! The index given is out of range.");
    }
    return tasks.get(index);
  }

  public void addTask(Task task) {
    tasks.add(task);
    System.out.println("Got it. I've added this task:");
    System.out.printf("=> %s\n", task);
    System.out.printf("Now you have %d tasks in the list\n", length());
  }

  public void deleteTask(int index) throws DukeException {
    Task t = getTask(index);
    tasks.remove(index);
    System.out.println("Noted. I've removed this task");
    System.out.printf("=> %s\n", t);
    System.out.printf("Now you have %d tasks in the list\n", length());
  }

  public int length() {
    return tasks.size();
  }
  
  public void markTask(int index) throws DukeException {
    Task t = getTask(index);
    t.mark();
    System.out.println("Nice! I've marked this task as done:");
    System.out.printf("=> %s\n", t);
  }

  public void unmarkTask(int index) throws DukeException {
    Task t = getTask(index);
    t.unmark();
    System.out.println("OK, I've marked this task as not done yet:");
    System.out.printf("=> %s\n", t);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("");
    for (int i = 0; i < tasks.size(); i++) {
      sb.append(String.format("%d. %s", i+1, tasks.get(i)));

      if (i < tasks.size() - 1) {
        sb.append('\n');
      }
    }
    return sb.toString();
  }
}
