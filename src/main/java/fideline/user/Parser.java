package fideline.user;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

import fideline.exception.EmptyParamException;
import fideline.exception.FidelineException;
import fideline.exception.InvalidArgumentException;
import fideline.exception.UnknownCommandException;
import fideline.exception.WrongFormatException;
import fideline.execution.Command;
import fideline.execution.CreateDeadlineCommand;
import fideline.execution.CreateEventCommand;
import fideline.execution.CreateTodoCommand;
import fideline.execution.DeleteCommand;
import fideline.execution.ExitCommand;
import fideline.execution.FindCommand;
import fideline.execution.ListCommand;
import fideline.execution.MarkCommand;
import fideline.execution.UnmarkCommand;

public class Parser {


    private static final String[] possibleCommandsArr = {
        "bye", "list", "todo", "deadline", "event", "mark", "unmark", "delete", "find"
    };


    public static Command getCommand(String userInput) throws FidelineException {
        ArrayList<String> possibleCommandsList = new ArrayList<>();
        possibleCommandsList.addAll(Arrays.asList(possibleCommandsArr));
        // First checks for simple commands (one word)
        if (!userInput.contains(" ")) {
            if (userInput.equals("bye")) {
                return new ExitCommand();
            } else if (userInput.equals("list")) {
                return new ListCommand();
            } else if (possibleCommandsList.contains(userInput)) {
                // error for empty args in command
                throw new EmptyParamException();
            } else {
                // error for unknown command
                throw new UnknownCommandException();
            }
            // below handles commands with arguments
        } else if (userInput.startsWith("todo ")) {
            return new CreateTodoCommand(userInput.substring(5, userInput.length()));
        } else if (userInput.startsWith("deadline ")) {
            String argString = userInput.substring(9, userInput.length());
            if (argString.contains(" /by ")) {
                String[] inputArguments = argString.split(" /by ", 2);
                String description = inputArguments[0];
                String deadline = inputArguments[1];
                if (inputArguments[1].matches("\\d++/\\d++/\\d++")) {
                    String[] timeComponents = inputArguments[1].split("/");
                    if (timeComponents[0].length() > 2
                            || timeComponents[1].length() > 2
                            || timeComponents[2].length() != 4) {
                        throw new WrongFormatException("date should follow the format:\n"
                                + "  dd/mm/yyyy");
                    }
                    if (timeComponents[0].length() == 1) {
                        timeComponents[0] = "0" + timeComponents[0];
                    }
                    if (timeComponents[1].length() == 1) {
                        timeComponents[1] = "0" + timeComponents[1];
                    }
                    String dateTimeString = String.format("%s-%s-%s",
                            timeComponents[2], timeComponents[1], timeComponents[0]);
                    try {
                        LocalDate ld = LocalDate.parse(dateTimeString);
                        deadline = dateTimeString;
                    } catch (DateTimeParseException e) {
                        throw new InvalidArgumentException("date");
                    }
                }
                return new CreateDeadlineCommand(description, deadline);
            } else {
                throw new WrongFormatException("command should follow the format:\n"
                        + "  deadline *description* /by *timing*");
            }
        } else if (userInput.startsWith("event ")) {
            String argString = userInput.substring(6, userInput.length());
            if (argString.contains(" /from ") && argString.contains(" /to ")) {
                String[] tempArr = argString.split(" /from ", 2);
                String[] tempArr2 = tempArr[1].split(" /to ");
                return new CreateEventCommand(tempArr[0], tempArr2[0], tempArr2[1]);
            } else {
                throw new WrongFormatException("command should follow the format:\n"
                        + "  event *description* /from *start time* /to *end time*");
            }
        } else if (userInput.startsWith("mark ")) {
            try {
                int taskNum = Integer.parseInt(userInput.substring(5, userInput.length()));
                return new MarkCommand(taskNum);
            } catch (NumberFormatException nfe) {
                throw new InvalidArgumentException("integer (e.g: mark 2)");
            }
        } else if (userInput.startsWith("unmark ")) {
            try {
                int taskNum = Integer.parseInt(userInput.substring(7, userInput.length()));
                return new UnmarkCommand(taskNum);
            } catch (NumberFormatException nfe) {
                throw new InvalidArgumentException("integer (e.g: unmark 2)");
            }
        } else if (userInput.startsWith("delete ")) {
            try {
                int taskNum = Integer.parseInt(userInput.substring(7, userInput.length()));
                return new DeleteCommand(taskNum);
            } catch (NumberFormatException nfe) {
                throw new InvalidArgumentException("integer! (e.g: delete 2");
            }
        } else if (userInput.startsWith("find ")) {
            return new FindCommand(userInput.substring(5, userInput.length()));
        }
        throw new UnknownCommandException();
    }

}
