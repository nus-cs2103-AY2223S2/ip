package duke.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.commands.Commands;
import duke.exceptions.DukeException;
import duke.exceptions.IllegalCommandException;
import duke.tasks.TaskType;

public class Parser {

    public static boolean handleCommands(String rawCommand, TaskList allTasks) throws DukeException {
        int commandIndex = rawCommand.indexOf(' ');
        String command = rawCommand;
        String arguments = "";
        if (commandIndex != -1) {
            // There is a space character in the command
            command = rawCommand.substring(0, commandIndex);
            arguments = rawCommand.substring(commandIndex + 1).trim();
        }

        switch (command) {
        case "bye":
            Ui.printGoodbye();
            return false;
        case "list":
            Ui.printList(allTasks);
            break;
        case "mark":
            try {
                int markIndex = Integer.parseInt(arguments) - 1;
                allTasks.changeTaskCompletionStatus(markIndex, true);
            } catch (Throwable e) {
                throw new IllegalCommandException(Commands.MARK);
            }
            break;
        case "unmark":
            try {
                int unmarkIndex = Integer.parseInt(arguments) - 1;
                allTasks.changeTaskCompletionStatus(unmarkIndex, false);
            } catch (Throwable e) {
                throw new IllegalCommandException(Commands.UNMARK);
            }
            break;
        case "todo":
            if (arguments.trim().equals("")) {
                throw new IllegalCommandException(Commands.TODO);
            }
            allTasks.addToList(arguments, TaskType.TODO, null, null, false, true);
            break;
        case "deadline":
            try {
                int slashIndex = arguments.indexOf('/');
                String dateByString = arguments.substring(slashIndex + 4);
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateBy = LocalDateTime.parse(dateByString, dateFormat);
                allTasks.addToList(arguments.substring(0, slashIndex - 1), TaskType.DEADLINE,
                        null, dateBy, false, true);
            } catch (Throwable e) {
                throw new IllegalCommandException(Commands.DEADLINE);
            }
            break;
        case "event":
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
                allTasks.addToList(arguments.substring(0, firstSlashIndex - 1), TaskType.EVENT,
                        start, end, false, true);
            } catch (Throwable e) {
                throw new IllegalCommandException(Commands.EVENT);
            }
            break;
        case "delete":
            try {
                int deleteIndex = Integer.parseInt(arguments) - 1;
                allTasks.deleteTask(deleteIndex);
            } catch (Throwable e) {
                throw new IllegalCommandException(Commands.DELETE);
            }
            break;
        case "find":
            try {
                allTasks.find(arguments);
            } catch (Throwable e) {
                throw new IllegalCommandException(Commands.FIND);
            }
        default:
            throw new IllegalCommandException(Commands.UNRECOGNIZED);
        }
        return true;
    }

}
