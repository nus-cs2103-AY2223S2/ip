package Command;

import Exceptions.*;
import storage.Storage;
import task.*;

import java.io.IOException;

public class Parser {
  public TaskList tasks;
  public Storage storage;

  public Parser(TaskList tasks, Storage storage) {
    this.tasks = tasks;
    this.storage = storage;
  }

  public String execute(String info, Command command) throws DukeException, IOException {
    String reply = "";
    String[] strArr = info.split(" ");
    String[] strArrP = info.split("/");
    Task curTask;

    switch (command) {
      case BYE: {
        reply = "Bye. Hope to see you again soon!";
        break;
      }
      case TODO: {
        if (strArr.length < 2) {
          throw new EmptyDescription(new Todo(""));
        }
        String input = info.substring(5).trim();
        curTask = new Todo(input);
        tasks.addTask(curTask);
        storage.updateList();
        reply = "Got it. I've added this task:\n\t"
            + curTask.taskString()
            + "\n" + "Now you have "
            + tasks.size()
            + " task(s) in the list.";
        break;
      }
      case DEADLINE: {
        if (strArr.length < 2) {
          throw new EmptyDescription(new Deadline("", "2022-01-01 0000"));
        }
        if (strArrP.length < 2) {
          throw new EmptyTime(new Deadline("", "2022-01-01 0000"));
        }
        String[] division = info.substring(9).split(" /by ");
        String input = division[0].trim();
        String time = division[1].trim();
        curTask = new Deadline(input, time);
        tasks.addTask(curTask);
        storage.updateList();
        reply = "Got it. I've added this task:\n\t"
            + curTask.taskString()
            + "\n" + "Now you have "
            + tasks.size()
            + " task(s) in the list.";
        break;
      }
      case EVENT: {
        if (strArr.length < 2) {
          throw new EmptyDescription(new Event("",
              "2022-01-01 0000", "2022-01-01 0000"));
        }
        if (strArrP.length < 3) {
          throw new EmptyTime(new Event("",
              "2022-01-01 0000", "2022-01-01 0000"));
        }
        String[] division = info.substring(6).split(" /from ");
        String input = division[0].trim();
        String[] timeDivision = division[1].split(" /to ");
        String startTime = timeDivision[0].trim();
        String endTime = timeDivision[1].trim();
        curTask = new Event(input, startTime, endTime);
        tasks.addTask(curTask);
        storage.updateList();
        reply = "Got it. I've added this task:\n\t"
            + curTask.taskString()
            + "\n" + "Now you have "
            + tasks.size()
            + " task(s) in the list.";
        break;
      }
      case MARK: {
        if (strArr.length < 2) {
          throw new EmptyOrder("mark");
        }
        int curIndex = Integer.parseInt(strArr[1]) - 1;
        if (curIndex > tasks.size() - 1) {
          throw new NoSuchTask(curIndex);
        }
        Task object = tasks.get(curIndex);
        object.mark();
        storage.updateList();
        reply = "Nice! I've marked this task as done: \n"
            + object.taskString();
        break;
      }
      case UNMARK: {
        if (strArr.length < 2) {
          throw new EmptyOrder("unmark");
        }
        int curIndex = Integer.parseInt(strArr[1]) - 1;
        if (curIndex > tasks.size() - 1) {
          throw new NoSuchTask(curIndex);
        }
        Task object = tasks.get(curIndex);
        object.unmark();
        storage.updateList();
        reply = "OK, I've marked this task as not done yet:\n"
            + object.taskString();
        break;
      }
      case LIST: {
        reply = "Here are the tasks in your list:\n" + tasks.listTask();
        break;
      }
      case DELETE: {
        if (strArr.length < 2) {
          throw new EmptyOrder("delete");
        }
        int curIndex = Integer.parseInt(strArr[1]) - 1;
        if (curIndex > tasks.size() - 1) {
          throw new NoSuchTask(curIndex);
        }
        Task object = tasks.get(curIndex);
        tasks.delete(curIndex);
        storage.updateList();
        reply = "Noted. I've removed this task:\n"
            + object.taskString();
        break;
      }
    }
    return reply;
  }
}
