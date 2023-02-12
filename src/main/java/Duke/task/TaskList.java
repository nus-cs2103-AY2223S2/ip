package Duke.task;

import java.util.ArrayList;

/**
 * Class manages tasks in an Arraylist
 */
public class TaskList {
  public static ArrayList<Task> tasks;

  /**
   * Constructor for TaskList
   * initialize the field tasks ,
   * so it's not null.
   */
  public TaskList() {
    tasks = new ArrayList<>();
  }

  /**
   * method to add a new task into the arraylist.
   *
   * @param task receives the task needed to be added.
   */
  public void addTask(Task task) {
    tasks.add(task);
  }

  /**
   * method to delete a task from the arraylist.
   *
   * @param index receives the index of the task needed to be deleted.
   */
  public void delete(int index) {
    tasks.remove(index);
  }

  /**
   * method to get how many tasks are in the TaskList.
   */
  public int size() {
    return tasks.size();
  }

  /**
   * return the task of a particular index in the list.
   *
   * @param curIndex receives the index of the task to get.
   */
  public Task get(int curIndex) { return tasks.get(curIndex); }

  /**
   * list out the tasks in the list with their string information.
   */
  public String listTask() {
    StringBuilder sb = new StringBuilder();
    for(Task t: tasks) {
      int num = tasks.indexOf(t) + 1;
      sb.append(num).append(".").append(t.taskString()).append("\n");
    }
    return sb.toString();
  }

  /**
   * convert the current taskList into String commands,
   * which will be written into the data file in the future.
   */
  public static String arrayListToString() {
    StringBuilder arrayString = new StringBuilder();
    for (Task t : tasks) {
      arrayString.append(t.toString()).append(" ");
      arrayString.append(t.string);
      if(t instanceof Deadline) {
        arrayString.append(" /by").append(" ")
            .append(((Deadline) t).strTime);
      }
      if(t instanceof Event) {
        arrayString.append(" /from").append(" ")
            .append(((Event) t).strStartTime)
            .append(" ").append(" /to").append(" ")
            .append(((Event) t).strEndTime);
      }
      arrayString.append("\n");
      if (t.isMark) {
        arrayString.append("mark ").
            append(tasks.indexOf(t) + 1).append("\n");
      }
    }
    return arrayString.toString();
  }
}

