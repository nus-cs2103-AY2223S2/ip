package Duke.Command;

import Duke.Exceptions.*;
import Duke.storage.Storage;
import Duke.task.*;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Class generates responses to user's command
 */

public class Parser {
    private TaskList tasks;
    private Storage storage;

    /**
   * Constructor for Parser
   *
   * @param tasks object receives taskList that manages editing current tasks.
   * @param storage object manages the update of the current data file.
   */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
   * execute Parser with user's String info and the command type it's converted to.
   *
   * @param info receives String input from the user
   * @param command provides the command type based the user's input.
   */
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
                throw new EmptyDescription(new Deadline("",
              "2022-01-01 0000"));
            }
            if (strArrP.length < 2) {
                throw new EmptyTime(new Deadline("",
              "2022-01-01 0000"));
            }
            String[] division = info.substring(9).split(" /by ");
            if (division.length < 2) {
                throw new EmptyTime(new Deadline("",
                    "2022-01-01 0000"));
            }
            String input = division[0].trim();
            String time = division[1].trim();
            try {
                curTask = new Deadline(input, time);
            } catch (DateTimeParseException e) {
                throw e;
            }
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
            if (division.length < 2) {
                throw new EmptyTime(new Event("",
                    "2022-01-01 0000", "2022-01-01 0000"));
            }
            String input = division[0].trim();
            String[] timeDivision = division[1].split(" /to ");
            String startTime = timeDivision[0].trim();
            String endTime = timeDivision[1].trim();
            try {
                curTask = new Event(input, startTime, endTime);
            } catch (DateTimeParseException e) {
                throw e;
            }
            tasks.addTask(curTask);
            storage.updateList();
            reply = "Got it. I've added this task:\n\t"
                + curTask.taskString()
                + "\n" + "Now you have "
                + tasks.size()
                + " task(s) in the list.";
            break;
        }
        case SORTEVENT: {
            ArrayList<Event> events = tasks.sortEvents();
            StringBuilder sb = new StringBuilder();
            for (Task t: events) {
                sb.append(t.taskString()).append("\n\t");
            }
            reply = "Here are your events sorted by time:\n\t"
                + sb;
            break;
        }
        case SORTDEADLINE: {
            ArrayList<Deadline> deadlines = tasks.sortDeadlines();
            StringBuilder sb = new StringBuilder();
            for (Task t: deadlines) {
                sb.append(t.taskString()).append("\n\t");
            }
            reply = "Here are your deadlines sorted by time:\n\t"
                + sb;
            break;
        }
        case MARK: {
            if (strArr.length < 2) {
                throw new EmptyOrder("mark");
            }
            int curIndex;
            try {
                curIndex = Integer.parseInt(strArr[1]) - 1;
            } catch (NumberFormatException e) {
                throw new EmptyOrder("mark");
            }
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
            int curIndex;
            try {
                curIndex = Integer.parseInt(strArr[1]) - 1;
            } catch (NumberFormatException e) {
                throw new EmptyOrder("mark");
            }
            if (curIndex > tasks.size() - 1) {
                throw new NoSuchTask(curIndex);
            }
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
        //level 9 dealing with "find" command"
        case FIND: {
            if (strArr.length < 2) {
                throw new EmptyFind();
            }
            StringBuilder sb = new StringBuilder();
            String input = info.substring(5).trim();
            for (Task t: this.tasks.getTaskList()) {
                int numOfSameChar = 0;
                for (int i = 0; i < Math.min(input.length(),
                    t.getString().length()); i++) {
                    if (input.charAt(i) == t.getString().charAt(i)) {
                        numOfSameChar++;
                    }
                }
                if (numOfSameChar > 2) {
                    sb.append(t.taskString()).append("\n");
                }
            }
            reply = "Here are the matching tasks in your list:\n"
                + sb;
            break;
        }
        case LIST: {
            reply = "Here are the tasks in your list:\n"
                + tasks.listTask();
            break;
        }
        case DELETE: {
            if (strArr.length < 2) {
                throw new EmptyOrder("delete");
            }
            int curIndex;
            try {
                curIndex = Integer.parseInt(strArr[1]) - 1;
            } catch (NumberFormatException e) {
                throw new EmptyOrder("mark");
            }
            if (curIndex > tasks.size() - 1) {
                throw new NoSuchTask(curIndex);
            }
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
        default: {
            throw new DukeException();
        }
        }
        return reply;
    }
}
