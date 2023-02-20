package duke;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static Command parse(String Command) throws DukeException {
        if (isExitCommand(Command)) {
            return new ExitCommand();
        } else if (isListCommand(Command)) {
            return new ListCommand();
        } else if (isMarkCommand(Command)) {
            String userInput = Command.replaceFirst("mark", "").trim();
            int taskNum = errorMarkIndex(userInput);
            return new MarkCommand(taskNum - 1);
        } else if (isUnmarkCommand(Command)) {
            String userInput = Command.replaceFirst("unmark", "").trim();
            int taskNum = errorUnmarkIndex(userInput);
            return new UnmarkCommand(taskNum - 1);
        } else if (isAddTodoCommand(Command)) {
            String todo = Command.replaceFirst("todo", "").trim();
            errorTodo(todo);
            return new AddTodoCommand(todo);
        } else if (isAddEventCommand(Command)) {
            String[] event = errorEvent(Command);
            if (isDate(event[1])) {
                Date eventDate = parseDate(event[1]);
                return new AddEventCommand(event[0], eventDate);
            } else {
                return new AddEventCommand(event[0], event[1]);
            }
        } else if (isAddDeadlineCommand(Command)) {
            String[] deadline = errorDeadline(Command);
            if (isDate(deadline[1])) {
                Date deadlineDate = parseDate(deadline[1]);
                return new AddDeadlineCommand(deadline[0], deadlineDate);
            } else {
                return new AddDeadlineCommand(deadline[0], deadline[1]);
            }
        } else if (isDeleteCommand(Command)) {
            String userInput = Command.replaceFirst("delete", "").trim();
            int taskNum = errorDeleteIndex(userInput);
            return new DeleteCommand(taskNum);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static Date parseDate(String date) throws DukeException {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            throw new DukeException("An error occurred while parsing date: " + e);
        }
    }

    private static boolean isDate(String input) {
        String[] splitInput = input.split("/");
        if (splitInput.length != 3 || !isNumeric(splitInput[0]) || !isNumeric(splitInput[1])) {
            return false;
        }

        String[] yearAndTime = splitInput[2].split(" ");
        if (yearAndTime.length != 2 || !isNumeric(yearAndTime[0]) || !isNumeric(yearAndTime[1])) {
            return false;
        }

        return true;
    }

    private static int errorMarkIndex(String userInput) throws DukeException {
        return errorMarkOrUnmarkOrDeleteIndex(userInput);
    }

    private static int errorUnmarkIndex(String userInput) throws DukeException {
        return errorMarkOrUnmarkOrDeleteIndex(userInput);
    }

    private static int errorDeleteIndex(String userInput) throws DukeException {
        return errorMarkOrUnmarkOrDeleteIndex(userInput);
    }

    private static int errorMarkOrUnmarkOrDeleteIndex(String userInput) throws DukeException {
        if (userInput.isEmpty() || isNotNumeric(userInput)) {
            throw new DukeException("☹ OOPS!!! The index to remove cannot be empty or not an integer.");
        }

        return Integer.parseInt(userInput);
    }

    private static boolean isNotNumeric(String input) {
        return !input.matches("-?\\d+(\\.\\d+)?");
    }

    private static void errorTodo(String todo) throws DukeException {
        if (todo.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static String[] errorEvent(String userInput) throws DukeException {
        return errorEventOrDeadline(userInput, "event", "/from");
    }

    private static String[] errorDeadline(String UserInput) throws DukeException {
        return errorEventOrDeadline(UserInput, "deadline", "/by");
    }

    private static String[] errorEventOrDeadline(String UserInput, String textToReplace, String textToSplit) throws DukeException {
        String[] splitInput = UserInput.replaceFirst(textToReplace, "")
                .trim().split(textToSplit);

        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }

        if (splitInput.length != 2 || splitInput[0].isBlank() || splitInput[1].isBlank()) {
            throw new DukeException("☹ OOPS!!! Please make sure the date is not empty");
        }
        return splitInput;
    }

    private static boolean isNumeric(String UserInput) {
        return UserInput.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean isExitCommand(String UserInput) {
        return UserInput.startsWith("bye");
    }

    public static boolean isListCommand(String UserInput) {
        return UserInput.startsWith("list");
    }

    public static boolean isMarkCommand(String UserInput) {
        return UserInput.startsWith("mark");
    }

    public static boolean isUnmarkCommand(String UserInput) {
        return UserInput.startsWith("unmark");
    }

    public static boolean isAddTodoCommand(String UserInput) {
        return UserInput.startsWith("todo");
    }

    public static boolean isAddDeadlineCommand(String UserInput) {
        return UserInput.startsWith("deadline");
    }

    public static boolean isAddEventCommand(String UserInput) {
        return UserInput.startsWith("event");
    }

    public static boolean isDeleteCommand(String UserInput) {
        return UserInput.startsWith("delete");
    }
}
