package task;

import java.util.ArrayList;

public class TaskList {
  public static ArrayList<Task> tasks;

  public TaskList() {
    tasks = new ArrayList<>();
  }

  public void addTask(Task task) {
    tasks.add(task);
  }

  public void delete(int index) {
    tasks.remove(index - 1);
  }

  public int size() {
    return tasks.size();
  }

  public Task get(int curIndex) { return tasks.get(curIndex); }

  public String listTask() {
    StringBuilder sb = new StringBuilder();
    for(Task t: tasks) {
      int num = tasks.indexOf(t) + 1;
      sb.append(num).append(".").append(t.taskString()).append("\n");
    }
    return sb.toString();
  }

  public static String arrayListToString() {
    StringBuilder arrayString = new StringBuilder();
    for (Task t : tasks) {
      arrayString.append(t.toString()).append(" ");
      arrayString.append(t.string);
      if(t instanceof Deadline) {
        arrayString.append(" /by").append(" ").append(((Deadline) t).strTime);
      }
      if(t instanceof Event) {
        arrayString.append(" /from").append(" ").append(((Event) t).strStartTime)
            .append(" ").append(" /to").append(" ").append(((Event) t).strEndTime);
      }
      arrayString.append("\n");
      if (t.mark) {
        arrayString.append("mark ").append(tasks.indexOf(t) + 1).append("\n");
      }
    }
    return arrayString.toString();
  }
}

