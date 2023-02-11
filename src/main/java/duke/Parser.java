package duke;

import duke.command.*;
import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Responsible for parsing user input into relevant data. This data is used to construct a relevant command object,
 * where the data will be used in command execution.
 */
public class Parser {

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
            return handleMark(commandStream, taskList);
        case "unmark":
            return handleUnmark(commandStream, taskList);
        case "deadline":
            return handleDeadline(commandStream, taskList);
        case "event":
            return handleEvent(commandStream, taskList);
        case "todo":
            return handleToDo(commandStream, taskList);
        case "delete":
            return handleDelete(commandStream, taskList);
        case "find":
            return handleFind(commandStream, taskList);
        default:
            return new ErrorCommand(MessageGenerator.genUnknownCommandMsg());
        }
    }


    private static Command handleList(TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        return new ListCommand(taskList);
    }

    private static Command handleMark(Scanner commandStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        if (!commandStream.hasNext()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg("task number"));
        }

        try {
            int taskIdx = Integer.parseInt(commandStream.next()) - 1;

            if (!taskList.hasTask(taskIdx)) {
                return new ErrorCommand(MessageGenerator.genTaskDoesNotExistMsg(String.valueOf(taskIdx + 1)));
            }

            boolean isMark = true;
            return new MarkOrUnmarkCommand(taskIdx, taskList, isMark);

        } catch (NumberFormatException e) {
            return new ErrorCommand(MessageGenerator.genNotANumberMsg());
        }

    }

    private static Command handleUnmark(Scanner commandStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        if (!commandStream.hasNext()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg("task number"));
        }

        try {
            int taskIdx = Integer.parseInt(commandStream.next()) - 1;

            if (!taskList.hasTask(taskIdx)) {
                return new ErrorCommand(MessageGenerator.genTaskDoesNotExistMsg(String.valueOf(taskIdx + 1)));
            }

            boolean isMark = false;
            return new MarkOrUnmarkCommand(taskIdx, taskList, isMark);

        } catch (NumberFormatException e) {
            return new ErrorCommand(MessageGenerator.genNotANumberMsg());
        }

    }


    private static Command handleDelete(Scanner commandStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        if (!commandStream.hasNext()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg("task number"));
        }

        try {
            int taskIdx = Integer.parseInt(commandStream.next()) - 1;

            if (!taskList.hasTask(taskIdx)) {
                return new ErrorCommand(MessageGenerator.genTaskDoesNotExistMsg(String.valueOf(taskIdx + 1)));
            }

            return new DeleteCommand(taskIdx, taskList);

        } catch (NumberFormatException e) {
            return new ErrorCommand(MessageGenerator.genNotANumberMsg());
        }
    }

    private static Command handleDeadline(Scanner stringStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        String taskDesc = "";
        String byString = "";

        boolean foundBy = false;
        while (stringStream.hasNext()) {
            String temp = stringStream.next();
            if (temp.equalsIgnoreCase("/by")) {
                foundBy = true;
                continue;
            }

            if (foundBy) {
                byString += temp + " ";
            } else {
                taskDesc += temp + " ";
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

        try {
            LocalDateTime by = DateTimeParser.parse(byString);
            String[] parts = byString.split(" ");
            boolean hasTime = parts.length == 2;

            assert taskDesc != null : "taskDesc should not be null";
            assert by != null : "/by field should not be null";

            return new DeadlineCommand(taskDesc, by, hasTime, taskList);

        } catch (DateTimeParseException e) {
            return new ErrorCommand(MessageGenerator.genDateTimeParseErrorMsg());
        }

    }

    private static Command handleEvent(Scanner stringStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        String taskDesc = "";
        String fromString = "";
        String toString = "";

        boolean foundFrom = false;
        boolean foundTo = false;

        while (stringStream.hasNext()) {
            String temp = stringStream.next();

            if (temp.equalsIgnoreCase("/from")) {
                foundFrom = true;
                continue;
            } else if (temp.equalsIgnoreCase("/to")) {
                foundTo = true;
                continue;
            }

            if (foundTo) {
                toString += temp + " ";
            } else if (foundFrom) {
                fromString += temp + " ";
            } else {
                taskDesc += temp + " ";
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

        try {
            LocalDateTime from = DateTimeParser.parse(fromString);
            LocalDateTime to = DateTimeParser.parse(toString);

            String[] fromParts = fromString.split(" ");
            boolean fromHasTime = fromParts.length == 2;

            String[] toParts = toString.split(" ");
            boolean toHasTime = toParts.length == 2;

            assert taskDesc != null : "taskDesc should not be null";
            assert from != null : "/from field should not be null.";
            assert to != null : "/to field should not be null.";

            return new EventCommand(taskDesc, from, fromHasTime, to, toHasTime, taskList);

        } catch (DateTimeParseException e) {
            return new ErrorCommand(MessageGenerator.genDateTimeParseErrorMsg());
        }
    }

    private static Command handleToDo(Scanner stringStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        String taskDesc = "";

        while (stringStream.hasNext()) {
            String temp = stringStream.nextLine();
            taskDesc += temp;
        }

        if (taskDesc.isEmpty()) {
            return new ErrorCommand("missing task desc");
        }

        taskDesc = taskDesc.trim();
        assert taskDesc != null : "taskDesc should not be null";
        return new ToDoCommand(taskDesc, taskList);
    }

    private static Command handleFind(Scanner stringStream, TaskList taskList) {
        assert taskList != null : "Task list should not be null";
        if (!stringStream.hasNext()) {
            return new ErrorCommand(MessageGenerator.genMissingFieldMsg("keyword"));
        }

        String keyword = stringStream.next();
        return new FindCommand(keyword, taskList);
    }
}
