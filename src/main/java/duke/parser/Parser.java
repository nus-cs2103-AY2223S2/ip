package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidCommandException;

import duke.storage.Storage;
import duke.task.TaskList;


public class Parser {
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("Please enter a valid datetime: dd-MM-yyyy HH:mm");
        }
    }

    public static void processCommand(String userCommand, Storage storage, TaskList tasks) throws DukeException {

        if (userCommand.isEmpty()) {
            throw new DukeInvalidCommandException("Please enter a command");
        }

        String command = userCommand.split(" ", 2)[0];

        // Single word commands
        if (userCommand.equals("list")) {
            tasks.printTaskList();
            return;
        }

        // Multi-word commands
        switch (command) {
            case "todo":
                tasks.addToDo(userCommand);
                break;
            case "deadline":
                tasks.addDeadline(userCommand);
                break;
            case "event":
                tasks.addEvent(userCommand);
                break;
            case "mark":
                tasks.markTask(userCommand);
                break;
            case "unmark":
                tasks.unmarkTask(userCommand);
                break;
            case "delete":
                tasks.deleteTask(userCommand);
                break;
            case "find":
                tasks.findTask(userCommand);
                break;
            default:
                throw new DukeInvalidCommandException("beep...boop... unrecognized command!");
        }

        storage.storeTaskList(tasks.getTaskList());
    }
}
