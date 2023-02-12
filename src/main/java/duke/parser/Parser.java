package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
<<<<<<< HEAD:src/main/java/duke/parser/Parser.java
=======
import java.time.format.DateTimeParseException;
>>>>>>> master:src/main/java/duke/command/Parser.java

/**
 * Class for Parser which translates between different task formats
 */
public final class Parser {

    /**
     * Converts string representation of a date time to LocalDateTime object
     * @param dateTime Date Time to be converted
     * @return Date Time as a LocalDateTime object
     */
    public static LocalDateTime dateFormatter(String dateTime) {
        assert dateTime.length() == 16: "Incorrect dateTime format";
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    /**
     * Get keyword from find command
     * @param input User input containing find command
     * @return String representation of keyword
     */
    public static String getFindKeyword(String input) {
        assert input.length() > 5: "Find keyword cannot be empty";
        return input.substring(5);
    }

    /**
     * Translate a task log line to its respective Task object
     * @param taskLog Task log to be translated
     * @return Task which was represented by its task log format
     */
    public static Task translateTaskLogToTask(String taskLog) {
        assert taskLog != null: "Task Log cannot be blank";
        Task taskToReturn = new Task();
        String[] taskLogCommands = taskLog.split(" \\| ");
        String taskType = taskLogCommands[0];
        String taskStatus = taskLogCommands[1];
        String taskName = taskLogCommands[2];
        switch(taskType) {
        case "T":
            taskToReturn = new ToDo(taskName);
            break;
        case "D":
            String deadline = taskLogCommands[3];
            LocalDateTime formattedDeadline = Parser.dateFormatter(deadline);
            taskToReturn = new Deadline(taskName, formattedDeadline);
            break;
        case "E":
            String[] duration = taskLogCommands[3].split(" - ");
            String startTime = duration[0];
            String endTime = duration[1];
            LocalDateTime formattedStartTime = Parser.dateFormatter(startTime);
            LocalDateTime formattedEndTime = Parser.dateFormatter(endTime);
            taskToReturn = new Event(taskName, formattedStartTime, formattedEndTime);
            break;
        default:
        }
        if (taskStatus.equals("1")) {
            taskToReturn.markTask();
        }
        return taskToReturn;
    }

    public static Command getCommandType(String userInput) throws DukeException {
        assert userInput != null: "User input cannot be blank";
        String command = userInput.split(" ")[0];
        switch (command) {
        case "bye":
            return new ExitCommand(userInput);
        case "list":
            return new ListCommand(userInput);
        case "delete":
            return new DeleteCommand(userInput);
        case "mark":
            return new MarkCommand(userInput);
        case "unmark":
            return new UnmarkCommand(userInput);
        case "find":
            return new FindCommand(userInput);
        case "todo":
            return new TodoCommand(userInput);
        case "deadline":
            return new DeadlineCommand(userInput);
        case "event":
            return new EventCommand(userInput);
        default:
            throw new DukeException("Invalid command type");
        }
    }
}
