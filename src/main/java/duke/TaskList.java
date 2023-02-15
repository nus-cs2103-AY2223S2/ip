package duke;

import java.util.ArrayList;

public class TaskList {
  private ArrayList<Task> taskList;
  
  public TaskList(ArrayList<Task> taskList) {
    this.taskList = taskList;
  }

  public String delete(String textInput) {
      int i = Integer.parseInt(textInput.substring(7));
      Task t = taskList.get(i - 1);
      taskList.remove(i - 1);
      String removedText = String.format("Got it. I've removed this task: %s\n", t.toString());
      String listSize = String.format("Now you have %d tasks in the list", taskList.size());
      return removedText + listSize;
  }

  public String mark(String textInput) {
      int i = Integer.parseInt(textInput.substring(5));
      Task currTask = taskList.get(i - 1);
      currTask.markDone();
      String output = "Nice! I've marked this task as done\n" + currTask.toString();
      return output;
  }

  public String unmark(String textInput) {
      int i = Integer.parseInt(textInput.substring(7));
      Task currTask = taskList.get(i - 1);
      currTask.markUndone();
      String output = "OK, I've marked this task as not done yet:\n" + currTask.toString();
      return output;
  }

  public String todo(String textInput) throws DukeException {
      String[] parts = textInput.split(" ", 2);
      if (parts.length == 1 || parts[1] == "") {
          throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
      }
      Task t = new Task.Todo(textInput.substring(5));
      taskList.add(t);
      String output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t.toString(), taskList.size());
      return output;
  }

  public String deadline(String textInput) {
      String[] parts = textInput.split("/");
      Task t = new Task.Deadline(parts[0].substring(9), parts[1].substring(3));
      taskList.add(t);
      String output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t.toString(), taskList.size());
      return output;
    }

  public String event(String textInput) {
      String[] parts = textInput.split("/");
      Task t = new Task.Event(parts[0].substring(6), parts[1].substring(5), parts[2].substring(3));
      taskList.add(t);
      String output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list", t.toString(), taskList.size());
      return output;
  }

  public String toWrite() {
    int length = this.taskList.size();
    String result = "";
      for (int i = 0; i < length; i++) {
          String item = taskList.get(i).toData();
          if (i == length - 1) {
            result += item;
            continue;
          }
          result += item + "\n";
      }
    return result;
  }

  @Override
  public String toString() {
    String result = "";
    for (int i = 0; i < taskList.size(); i++) {
      String description = taskList.get(i).toString();
      if (i == taskList.size() - 1) {
        String output = String.format("%d. %s", i + 1, description);
        result += output;
        continue;
      }
      String output = String.format("%d. %s", i + 1, description + "\n");
      result += output;
    }
    return result;
  }
}
