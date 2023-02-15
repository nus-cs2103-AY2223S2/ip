package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import duke.commands.Commands;
import duke.exceptions.DukeException;
import duke.exceptions.IllegalCommandException;
import duke.tasks.TaskType;

/**
 * Acts as the parser to listen to and act upon user input in the command line interface.
 */
public class Parser {

    /**
     * Parses the raw command entered by the user and acts upon it. Returns a boolean representing if
     * the application should continue prompting the user for input.
     *
     * @param rawCommand a string representing the command entered by the user.
     * @param allTasks the TaskList containing all the tasks logged in by the user.
     * @return A boolean representing if the application should continue prompting the user for input.
     * @throws DukeException if the user input is not recognized.
     */
    public static String handleCommands(String rawCommand, TaskList allTasks) throws DukeException {
        int commandIndex = rawCommand.indexOf(' ');
        String command = rawCommand;
        String arguments = "";
        if (commandIndex != -1) {
            // There is a space character in the command
            command = rawCommand.substring(0, commandIndex);
            arguments = rawCommand.substring(commandIndex + 1).trim();
        }

        String result = "";
        switch (command) {
        case "b":
        case "bye":
            return result;
        case "ls":
        case "list":
            result = ReplyString.getAllTasksString(allTasks);
            break;
        case "m":
        case "mark":
            result = processMarkCommand(arguments, allTasks);
            break;
        case "um":
        case "unmark":
            result = processUnmarkCommand(arguments, allTasks);
            break;
        case "t":
        case "todo":
            result = processTodoCommand(arguments, allTasks);
            break;
        case "d":
        case "deadline":
            result = processDeadlineCommand(arguments, allTasks);
            break;
        case "e":
        case "event":
            result = processEventCommand(arguments, allTasks);
            break;
        case "del":
        case "delete":
            result = processDeleteCommand(arguments, allTasks);
            break;
        case "f":
        case "find":
            result = processFindCommand(arguments, allTasks);
            break;
        default:
            throw new IllegalCommandException(Commands.UNRECOGNIZED);
        }
        assert !Objects.equals(result, "")
                : "Result from a valid command should not be an empty string";
        return result;
    }

    private static String processMarkCommand(String arguments, TaskList allTasks)
            throws IllegalCommandException {
        String result;
        try {
            int markIndex = Integer.parseInt(arguments) - 1;
            result = allTasks.changeTaskCompletionStatus(markIndex, true);
        } catch (Throwable e) {
            throw new IllegalCommandException(Commands.MARK);
        }
        return result;
    }

    private static String processUnmarkCommand(String arguments, TaskList allTasks)
            throws IllegalCommandException {
        String result;
        try {
            int unmarkIndex = Integer.parseInt(arguments) - 1;
            result = allTasks.changeTaskCompletionStatus(unmarkIndex, false);
        } catch (Throwable e) {
            throw new IllegalCommandException(Commands.UNMARK);
        }
        return result;
    }

    private static String processTodoCommand(String arguments, TaskList allTasks)
            throws IllegalCommandException {
        if (arguments.trim().equals("")) {
            throw new IllegalCommandException(Commands.TODO);
        }
        String result = allTasks.addToList(arguments, TaskType.TODO, null,
                null, false, true);
        return result;
    }

    private static String processDeadlineCommand(String arguments, TaskList allTasks)
            throws IllegalCommandException {
        String result;
        try {
            int slashIndex = arguments.indexOf('/');
            String dateByString = arguments.substring(slashIndex + 4);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateBy = LocalDateTime.parse(dateByString, dateFormat);
            result = allTasks.addToList(arguments.substring(0, slashIndex - 1), TaskType.DEADLINE,
                    null, dateBy, false, true);
        } catch (Throwable e) {
            throw new IllegalCommandException(Commands.DEADLINE);
        }
        return result;
    }

    private static String processEventCommand(String arguments, TaskList allTasks)
            throws IllegalCommandException {
        String result;
        try {
            int firstSlashIndex = arguments.indexOf('/');
            String startAndEnd = arguments.substring(firstSlashIndex + 6);
            int secondSlashIndex = startAndEnd.indexOf('/');
            String startString = startAndEnd.substring(0, secondSlashIndex - 1);
            String endString = startAndEnd.substring(secondSlashIndex + 4);
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime start = LocalDateTime.parse(startString, dateFormat);
            LocalDateTime end = LocalDateTime.parse(endString, dateFormat);
            // TODO: Check if start date is after end date
            result = allTasks.addToList(arguments.substring(0, firstSlashIndex - 1), TaskType.EVENT,
                    start, end, false, true);
        } catch (Throwable e) {
            throw new IllegalCommandException(Commands.EVENT);
        }
        return result;
    }

    private static String processDeleteCommand(String arguments, TaskList allTasks)
            throws IllegalCommandException {
        String result;
        try {
            int deleteIndex = Integer.parseInt(arguments) - 1;
            result = allTasks.deleteTask(deleteIndex);
        } catch (Throwable e) {
            throw new IllegalCommandException(Commands.DELETE);
        }
        return result;
    }

    private static String processFindCommand(String arguments, TaskList allTasks)
            throws IllegalCommandException {
        String result;
        try {
            result = allTasks.find(arguments);
        } catch (Throwable e) {
            throw new IllegalCommandException(Commands.FIND);
        }
        return result;
    }

}
