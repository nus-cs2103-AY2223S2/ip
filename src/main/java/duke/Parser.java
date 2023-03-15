package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    private static final DateTimeFormatter savedDateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Returns Task by parsing a saved string.
     *
     * @param savedTask
     * @return Task
     */
    public static Task parseSavedTask(String savedTask) {
        String[] taskArr = savedTask.split("\\|");
        Task newTask;
        switch (taskArr[0]) {
        case "T":
            newTask = new Todo(taskArr[2]);
            break;
        case "D":
            LocalDate by = parseDate(taskArr[3]);
            Deadline newDeadline = new Deadline(taskArr[2], by);
            if (taskArr.length == 5 && taskArr[4].equals("S")) {
                newDeadline.snooze();
            }
            newTask = newDeadline;
            break;
        case "E":
            LocalDate from = parseDate(taskArr[3]);
            LocalDate to = parseDate(taskArr[4]);
            Event newEvent = new Event(taskArr[2], from, to);
            if (taskArr.length == 6 && taskArr[5].equals("S")) {
                newEvent.snooze();
            }
            newTask = newEvent;
            break;
        default:
            throw new DukeException();
        }
        return newTask;
    }

    /**
     * Returns command of parsed string
     *
     * @param cmd String to be parsed
     * @return parsed Command
     */
    public static Command parse(String cmd) {
        switch (cmd) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        }

        String[] cmdArr = cmd.split(" ");
        switch(cmdArr[0]) {
        case "mark":
            return new TickCommand(Integer.parseInt(cmdArr[1]) - 1);
        case "unmark":
            return new UntickCommand(Integer.parseInt(cmdArr[1]) - 1);
        case "todo":
            String title = cmd.replaceAll("todo", "").trim();
            return new TodoCommand(title);
        case "deadline":
            return parseDeadline(cmd);
        case "event":
            return parseEvent(cmd);
        case "delete":
            return new DeleteCommand(Integer.parseInt(cmdArr[1]) - 1);
        case "find":
            return new FindCommand(cmd.replaceAll("find", "").trim());
        case "snooze":
            return new SnoozeCommand(Integer.parseInt(cmdArr[1]) - 1);
        case "unsnooze":
            return new UnsnoozeCommand(Integer.parseInt(cmdArr[1]) - 1);
        }

        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static DeadlineCommand parseDeadline(String cmd) {
            String[] cmdArr = cmd.split("/by");
            String title = cmdArr[0].replaceAll("deadline", "").trim();
            LocalDate by = parseDate(cmdArr[1].substring(1));
            return new DeadlineCommand(title, by);
    }

    private static EventCommand parseEvent(String cmd) {
            String[] cmdArr = cmd.split("/from");
            String title = cmdArr[0].replaceAll("event", "").trim();
            String[] times = cmdArr[1].split("/to");
            LocalDate from = parseDate(times[0].trim());
            LocalDate to = parseDate(times[1].trim());
            return new EventCommand(title, from, to);
    }

    private static LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, savedDateFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Sorry! That is the incorrect date format. Please use dd/MM/yyyy");
        }
    }
}
