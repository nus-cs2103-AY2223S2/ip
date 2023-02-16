package jeno.parser;

import jeno.command.*;
import jeno.exception.JenoException;
import jeno.task.Deadline;
import jeno.task.Event;
import jeno.task.Task;
import jeno.task.ToDo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static Command getCommandType(String userInput) throws JenoException {
        assert userInput != null: "User input cannot be blank";
        String command = userInput.split(" ")[0];
        switch (command) {
        case "help":
            return new HelpCommand(userInput);
        case "bye":
            return new ExitCommand(userInput);
        case "list":
            return new ListCommand(userInput);
        case "delete":
            return new DeleteCommand(userInput);
        case "clear":
            return new ClearCommand(userInput);
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
        case "note":
            return new NoteCommand(userInput);
        case "opennotes":
            return new OpenNoteCommand(userInput);
        case "clearnotes":
            return new ClearNotesCommand(userInput);
        default:
            throw new JenoException("Oops! Please enter a valid command.\n" +
                    "To view list of features, type in 'help' in the command box.");
        }
    }
}
