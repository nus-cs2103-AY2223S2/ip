package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Span;

/**
 * Parser to parse the command and process the necessary actions associated with the command.
 *
 * @author Andre Lin HuiKai
 * @version CS2103T AY22/23 Semester 2
 */
public class Parser {
    private TaskList tasks;

    /**
     * Constructor for parser.
     * @param tasks TaskList of tasks for the Parser to retrieve the tasks and act on it accordingly.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the user's input and execute the necessary actions associated with the command before returning an acknowledgement.
     * @param command
     * @param echo The user input to be parsed to be used in the execution of its associated command.
     * @return a string that describes the action done.
     * @throws DukeException if there is missing required information in the user's input.
     */
    public String execute(Command command, String echo) throws DukeException {
        // to be converted into the right (sub) task below
        Task task = null;
        String ret = "";
        switch (command) {
        case BYE: {
            return "BYE";
        }
        case LIST: {
            ret = "Here are the tasks in your list:\n" + tasks.listTasks();
            break;
        }
        case MARK: {
            if (echo.length() < 6 || !echo.matches("mark\\s+\\d+")) {
                throw new DukeException("<mark> command should receive a task number!");
            }
            int taskNum = Integer.parseInt(echo.substring(5).trim());
            if (1 <= taskNum && taskNum <= tasks.getSize()) {
                String marked = tasks.toggleMark(taskNum);
                ret = "Nice! I've marked this task as done:\n\t" + marked;
            } else {
                throw new InvalidIndexException(taskNum);
            }
            break;
        }
        case UNMARK: {
            if (echo.length() < 8 || !echo.matches("unmark\\s+\\d+")) {
                throw new DukeException("<unmark> command should receive a task number!");
            }
            int taskNum = Integer.parseInt(echo.substring(7).trim());
            if (1 <= taskNum && taskNum <= tasks.getSize()) {
                String unmarked = tasks.toggleUnmark(taskNum);
                ret = "OK, I've marked this task as not done yet:\n\t" + unmarked;
            } else {
                throw new InvalidIndexException(taskNum);
            }
            break;
        }
        case DELETE: {
            if (echo.length() < 8 || !echo.matches("delete\\s+\\d+")) {
                throw new DukeException("<delete> command should receive a task number!");
            }
            int taskNum = Integer.parseInt(echo.substring(7).trim());
            if (1 <= taskNum && taskNum <= tasks.getSize()) {
                Task removed = tasks.delete(taskNum);
                ret = "Noted. I've removed this task:\n\t"
                        + removed.getStatusIcon()
                        + "\n" + "Now you have "
                        + tasks.getSize()
                        + " task(s) in the list.";
            } else {
                throw new InvalidIndexException(taskNum);
            }
            break;
        }
        case TODO: {
            if (echo.length() < 6 || !echo.matches("todo\\s.*")) {
                throw new DukeException("The description of a <todo> cannot be empty!");
            }
            String info = echo.substring(5).trim();
            task = new ToDo(info);
            break;
        }
        case DEADLINE: {
            if (echo.length() < 10) {
                throw new DukeException("The description of a <deadline> cannot be empty!");
            }
            String info = echo.substring(9);
            if (!info.matches(".+ /by .+")) {
                throw new DukeException("<deadline> is to be used as such: $ deadline <des> /by <YYYY-MM-DD>");
            }
            String[] sp = info.split(" /by ");
            if (sp[1].trim().equals("")) {
                throw new DukeException("<deadline> is missing <YYYY-MM-DD>");
            }
            task = new Deadline(sp[0].trim(), sp[1].trim());
            break;
        }
        case EVENT: {
            if (echo.length() < 7) {
                throw new DukeException("The description of a <event> cannot be empty!");
            }
            String info = echo.substring(6);
            if (!info.matches(".+ /from .+ /to .+")) {
                throw new DukeException("<event> is to be used as such: "
                        + "$ event <des> /from <YYYY-MM-DD> /to <YYYY-MM-DD>");
            }
            String[] sp = info.split(" /from ");
            if (sp[1].trim().equals("")) {
                throw new DukeException("<event> is missing start-time <YYYY-MM-DD>");
            }
            String[] time = sp[1].split(" /to ");
            if (time[1].trim().equals("")) {
                throw new DukeException("<event> is missing end-time <YYYY-MM-DD>");
            }
            task = new Event(sp[0].trim(), time[0].trim(), time[1].trim());
            break;
        }
        case GETEVENTSON: {
            if (echo.length() < 12) {
                throw new DukeException("Date input in the format of YYYY-MM-DD required!");
            }
            LocalDate date = LocalDate.parse(echo.substring(11).trim());
            ret = "Here are the deadlines/events on "
                    + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ":\n" + tasks.listAllOnDate(date);
            break;
        }
        case FIND: {
            if (echo.length() < 6) {
                throw new DukeException("Please supply a word to find!");
            }
            String toFind = echo.substring(5);
            ret = tasks.find(toFind);
            break;
        }
        default:
            break;
        }
        if (task != null) {
            tasks.addTask(task);
            ret = "Got it. I've added this task:\n\t"
                    + task.getStatusIcon()
                    + "\n" + "Now you have "
                    + tasks.getSize()
                    + " task(s) in the list.";
        }
        return ret;
    }
}
