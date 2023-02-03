package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import duke.exception.DukeException;
import duke.exception.EmptyTaskDescriptionException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * A parser that deals with user input and transforms them into
 * a form that can be processed.
 */
public class Parser {
    static String processCommand(String command, TaskList tasks) throws DukeException {
        String[] commandArr = command.split(" ");
        switch (commandArr[0]) {
        case "list":
            return tasks.toString();
        case "mark":
            return tasks.markTask(Integer.parseInt(commandArr[1]));
        case "unmark":
            return tasks.unmarkTask(Integer.parseInt(commandArr[1]));
        case "delete":
            return tasks.deleteTask(Integer.parseInt(commandArr[1]));
        case "find":
            return tasks.findTask(commandArr[1]);
        }
        return parseCommand(command, tasks);
    }

    static String parseCommand(String command, TaskList tasks) throws DukeException {
        String[] commandArr = command.split(" ");
        String taskType = commandArr[0];
        String description;
        Task task;
        switch (taskType) {
        case "todo":
            if (commandArr.length == 1) {
                throw new EmptyTaskDescriptionException();
            }
            description = command.substring(5);
            task = new ToDo(description);
            break;
        case "deadline":
            int doneByIndex = command.indexOf("/by");
            description = command.substring(9, doneByIndex - 1);
            String doneByString = command.substring(doneByIndex + 4);
            task = new Deadline(description, parseDateTime(doneByString));
            break;
        case "event":
            int startIndex = command.indexOf("/from");
            int endIndex = command.indexOf("/to");
            description = command.substring(6, startIndex - 1);
            String startString = command.substring(startIndex + 6, endIndex - 1);
            String endString = command.substring(endIndex + 4);
            task = new Event(description, parseDateTime(startString), parseDateTime(endString));
            break;
        default:
            throw new InvalidCommandException();
        }
        tasks.addTask(task);
        return tasks.addTaskText(task);
    }

    static LocalDateTime parseDateTime(String dateTimeString) {
        String[] dateTimeArray = dateTimeString.split(" ");
        LocalDate date = LocalDate.parse(dateTimeArray[0]);
        LocalTime time = LocalTime.parse(dateTimeArray[1]);
        return LocalDateTime.of(date, time);
    }

}
