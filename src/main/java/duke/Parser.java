package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.CommandTypes;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EditCommand;
import duke.command.ErrorCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkOrUnmarkCommand;
import duke.command.ToDoCommand;


/**
 * Responsible for parsing user input into relevant data. This data is used to construct a relevant command object,
 * where the data will be used in command execution.
 */
public class Parser {
    private static ArrayList<String> fields = new ArrayList<>(Arrays.asList("/desc", "/by", "/from", "/to"));
    /**
     * Parses the given user input (commandString) and executes the correct action based on that. Returns
     * a boolean to indicate if the application should close due to the user's input.
     *
     * @param commandString string that contains the user's input command
     * @param taskList a list that contains all tasks
     * @return true if the application should exit. Otherwise, returns false.
     */
    public static Command parse(String commandString, TaskList taskList) {
        Scanner commandStream = new Scanner(commandString);
        String command = commandStream.next().toLowerCase(Locale.ROOT);

        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return handleList(taskList);
        case "mark":
            return handleTaskIdxCommands(commandStream, taskList, CommandTypes.MARK);
        case "unmark":
            return handleTaskIdxCommands(commandStream, taskList, CommandTypes.UNMARK);
        case "deadline":
            return handleDeadline(commandStream, taskList);
        case "event":
            return handleEvent(commandStream, taskList);
        case "todo":
            return handleToDo(commandStream, taskList);
        case "delete":
            return handleTaskIdxCommands(commandStream, taskList, CommandTypes.DELETE);
        case "find":
            return handleFind(commandStream, taskList);
        case "edit":
            return handleTaskIdxCommands(commandStream, taskList, CommandTypes.EDIT);
        default:
            return new ErrorCommand(MessageGenerator.genUnknownCommandMsg());
        }
    }


    private static Command handleList(TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        return new ListCommand(taskList);
    }


    private static Command handleTaskIdxCommands(Scanner commandStream, TaskList taskList, CommandTypes type) {
        assert taskList != null;
        if (!commandStream.hasNext()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg("task number"));
        }

        try {
            int taskIdx = Integer.parseInt(commandStream.next()) - 1;

            if (!taskList.hasTask(taskIdx)) {
                return new ErrorCommand(MessageGenerator.genTaskDoesNotExistMsg(String.valueOf(taskIdx + 1)));
            }

            switch (type) {
            case DELETE:
                return new DeleteCommand(taskIdx, taskList);
            case UNMARK:
                return new MarkOrUnmarkCommand(taskIdx, taskList, false);
            case MARK:
                return new MarkOrUnmarkCommand(taskIdx, taskList, true);
            case EDIT:
                return handleEdit(taskIdx, commandStream, taskList);
            default:
                assert false;
            }

        } catch (NumberFormatException e) {
            return new ErrorCommand(MessageGenerator.genNotANumberMsg());
        }

        assert false;
        return new ErrorCommand("Something went wrong");
    }



    private static Command handleDeadline(Scanner commandStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        String taskDesc = "";
        String byString = "";

        boolean foundBy = false;
        while (commandStream.hasNext()) {
            String nextWord = commandStream.next();
            if (nextWord.equals("/by")) {
                foundBy = true;
                continue;
            }

            if (foundBy) {
                byString += nextWord + " ";
            } else {
                taskDesc += nextWord + " ";
            }
        }

        if (taskDesc.isEmpty()) {
            return new ErrorCommand(MessageGenerator.genMissingTaskDescMsg("deadline"));
        }

        if (!foundBy || byString.isEmpty()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg("/by"));
        }

        byString = byString.trim();
        taskDesc = taskDesc.trim();

        return DeadlineCommand.create(taskDesc, byString, taskList);
    }


    private static Command handleEvent(Scanner commandStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        String taskDesc = "";
        String fromString = "";
        String toString = "";

        boolean foundFrom = false;
        boolean foundTo = false;

        while (commandStream.hasNext()) {
            String nextWord = commandStream.next();

            if (nextWord.equals("/from")) {
                foundFrom = true;
                continue;
            } else if (nextWord.equals("/to")) {
                foundTo = true;
                continue;
            }

            if (foundTo) {
                toString += nextWord + " ";
            } else if (foundFrom) {
                fromString += nextWord + " ";
            } else {
                taskDesc += nextWord + " ";
            }
        }

        if (taskDesc.isEmpty()) {
            return new ErrorCommand(MessageGenerator.genMissingTaskDescMsg("event"));
        }

        if (!foundFrom || !foundTo || fromString.isEmpty() || toString.isEmpty()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg("/from or /to"));
        }

        fromString = fromString.trim();
        toString = toString.trim();
        taskDesc = taskDesc.trim();

        return EventCommand.create(taskDesc, fromString, toString, taskList);
    }

    private static Command handleToDo(Scanner commandStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        String taskDesc = "";

        while (commandStream.hasNext()) {
            taskDesc += commandStream.nextLine();
        }

        if (taskDesc.isEmpty()) {
            return new ErrorCommand("missing task desc");
        }

        taskDesc = taskDesc.trim();
        assert taskDesc != null : "taskDesc should not be null";
        return new ToDoCommand(taskDesc, taskList);
    }

    private static Command handleFind(Scanner commandStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        if (!commandStream.hasNext()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg("keyword"));
        }

        String keyword = commandStream.next();
        return new FindCommand(keyword, taskList);
    }

    private static Command handleEdit(Integer taskIdx, Scanner commandStream, TaskList taskList) {
        assert taskIdx != null;
        assert taskList != null;

        if (!commandStream.hasNext()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg(""));
        }

        String field = commandStream.next();

        if (!fields.contains(field)) {
            return new ErrorCommand(MessageGenerator.genInvalidFieldMsg(field));
        }

        String newValue = "";
        while (commandStream.hasNext()) {
            newValue += commandStream.next() + " ";
        }

        newValue = newValue.trim();

        return new EditCommand(taskIdx, field, newValue, taskList);
    }
}
