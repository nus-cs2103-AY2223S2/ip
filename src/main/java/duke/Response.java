package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDos;

/**
 * Response is a method that determines what Duke should respond given the type of the user input
 * and details of the input
 * @author CShuwen
 * @version 1.0
 * @since 0.0
 */
public class Response {

    public Response() {
    }

    public String getByeResponse() {
        return "Bye Shin-Chan. Hope to see you again soon!";
    }

    public String getListResponse(ArrayList<Task> taskList) {
        try {
            if (taskList.size() == 0) {
                String errMsg = "Shin-Chan, you have not upload any task yet.";
                throw new DukeException(errMsg);
            }
            String res = "Here are the tasks in Shin-Chan's list: \n";
            int i = 1;
            for (Task task : taskList) {
                res += i + ". " + task.toString() + "\n";
                i++;
            }
            return res;
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String getDeleteResponse(String input, ArrayList<Task> taskList, Storage storage) {
        try {
            int num = Integer.parseInt(input.substring(7));
            Task currTask = taskList.get(num - 1);
            taskList.remove(currTask);
            storage.update_data(taskList);
            return "Ai-Chan has removed this task: \n  " + currTask.toString() + "\n"
                    + "Now you have " + (taskList.size()) + " tasks in the list";
        } catch (IndexOutOfBoundsException err1) {
            String errMsg = "The task number that Shin-Chan have keyed in is invalid.";
            return errMsg;
        } catch (NumberFormatException err2) {
            String errMsg = "Shin-Chan! Please key in a valid number.";
            return errMsg;
        }
    }

    public String getMarkResponse(String input, ArrayList<Task> taskList, Storage storage) {
        try {
            int num = Integer.parseInt(input.substring(5));
            Task currTask = taskList.get(num - 1);
            currTask.markAsDone();
            taskList.set(num - 1, currTask);
            storage.update_data(taskList);
            return "Good Job Shin-Chan! I've marked this task as done\n" + currTask.getStatusIcon() + " " + currTask.getDes();
        } catch (IndexOutOfBoundsException err1) {
            String errMsg = "The task number that Shin-Chan have keyed in is invalid..";
            return errMsg;
        } catch (NumberFormatException err2) {
            String errMsg = "Shin-Chan! Please key in a valid number.";
            return errMsg;
        }
    }

    public String getUnmarkResponse(String input, ArrayList<Task> taskList, Storage storage) {
        try {
            int num = Integer.parseInt(input.substring(7));
            Task currTask = taskList.get(num - 1);
            currTask.unMark();
            taskList.set(num - 1, currTask);
            storage.update_data(taskList);
            return "Ok Shin-Chan, I've marked this task as not done yet\n"
                    + currTask.getStatusIcon() + " " + currTask.getDes();
        } catch (IndexOutOfBoundsException err1) {
            String errMsg = "The task number that Shin-Chan have keyed in is invalid.";
            return errMsg;
        } catch (NumberFormatException err2) {
            String errMsg = "Shin-Chan! Please key in a valid number.";
            return errMsg;
        }
    }

    public String getToDoResponse(String input, ArrayList<Task> taskList, Storage storage) {
        if (input.length() <= 5) {
            return "The description of a todo cannot be empty";
        }
        ToDos todo = new ToDos(input.substring(5), 0);
        taskList.add(todo);
        storage.update_data(taskList);
        return "added: " + todo + "\n"
                + "Now you have " + taskList.size() + " tasks in the list";
    }

    public String getDeadlineResponse(String input, ArrayList<Task> taskList, Storage storage) {
        try {
            String[] ddlStringArr = input.split(" /");
            if (input.length() <= 9 || ddlStringArr.length <= 1 || ddlStringArr[0].length() < 10) {
                String errMsg = "OOPS!!! The description or date of a deadline cannot be empty";
                throw new DukeException(errMsg);
            }
            try {
                LocalDate deadlineTime = LocalDate.parse(ddlStringArr[1]);
                Deadline deadline = new Deadline(ddlStringArr[0].substring(9), deadlineTime, 0);
                taskList.add(deadline);
                storage.update_data(taskList);
                return "added: " + deadline + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list";
            } catch (DateTimeParseException e) {
                String errMsg = "The description or date of a "
                        + "deadline is not right, please key in the"
                        + "date in the format of yyyy-mm-dd, eg. 2001-02-10\n"
                        + "You may key in: deadline hw1 /2001-02-10, "
                        + "Duke.Duke will record your deadline for hw1 as"
                        + "by: 10 Feb 2001";
                throw new DukeException(errMsg);
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String getEventResponse(String input, ArrayList<Task> taskList, Storage storage) {
        try {
            String[] eventStringArr = input.split(" /");
            if (input.length() <= 9 || eventStringArr.length <= 2 || eventStringArr[0].length() < 7) {
                String errMsg = "OOPS!!! The description or date of a event cannot be empty";
                throw new DukeException(errMsg);
            }
            try {
                LocalDate from = LocalDate.parse(eventStringArr[1]);
                LocalDate to = LocalDate.parse(eventStringArr[2]);
                if (from.isAfter(to)) {
                    String errMsg = "OOPS!!! Your time range is from a date to another date that is earlier"
                            + "than the former. Please key in a valid time range";
                    throw new DukeException(errMsg);
                }
                Event event = new Event(eventStringArr[0].substring(6), from, to, 0);
                taskList.add(event);
                storage.update_data(taskList);
                return "Got it. I've added this task \n" + "added: " + event + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list";
            } catch (DateTimeParseException e) {
                String errMsg = "The description or date for the "
                        + "event is not right, plase key in the"
                        + "date in the format of yyyy-mm-dd, eg. 2001-02-10\n"
                        + "You may key in: event hw1 /2001-02-10 /2001-02-12,"
                        + " Duke.Duke will record your event hw1 as"
                        + "from: 10 Feb 2001 to: 12 Feb 2001";
                throw new DukeException(errMsg);
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String getFindResponse(String input, ArrayList<Task> taskList) {
        try {
            String[] fileInputArr = input.split(" ");
            if (taskList.size() == 0) {
                String errMsg = "You have not upload any task yet";
                throw new DukeException(errMsg);
            }
            String res = "Here are the matching tasks in Shin-Chan's task list:\n";
            int i = 1;
            for (Task task : taskList) {
                if (task.getDescription().contains(fileInputArr[1])) {
                    res += i + ". " + task + "\n";
                    i++;
                }
            }
            if (i == 1) {
                return "There are no matching task";
            }
            return res;
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String getErrorResponse() {
        try {
            String errMsg = "Sorry Shin-Chan, I don't know what you mean";
            throw new DukeException(errMsg);
        } catch (DukeException e) {
            return e.toString();
        }
    }

    public String getUndoResponse(Storage storage) {
        storage.undo();
        return "Previous action Undone! (You may only undo the most recent command)";
    }

}