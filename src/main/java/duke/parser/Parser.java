package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.DukeInvalidArgumentException;
import duke.exception.DukeInvalidCommandException;

import duke.storage.Storage;
import duke.task.TaskList;


/**
 * Represents a parser that reads in user commands/ input and processes them.
 */
public class Parser {
    /**
     * Returns a LocalDateTime object representing the date and time specified.
     *
     * @param dateTime The string containing the date and time.
     * @return A LocalDateTime object.
     * @throws DukeException If the given date and time are in an invalid format
     * according to the specified patter.
     */
    public static LocalDateTime parseDateTime(String dateTime) throws DukeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        try {
            return LocalDateTime.parse(dateTime, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidArgumentException("Please enter a valid datetime: dd-MM-yyyy HH:mm");
        }
    }

    /**
     * Processes the given user command and performs actions on the TaskList.
     *
     * @param userCommand The user command.
     * @param storage The storage object to store the updated TaskList.
     * @param tasks The TaskList to perform actions on.
     * @throws DukeException If the user's command is invalid or an error occurs
     * while processing the task list
     */
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
