package duke.parser;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;
import duke.data.TypeOfTask;
import duke.exception.DukeException;



/**
 * Parser class that handles all conversion and parsing of the user inputs.
 *
 * @author Haiqel Bin Hanaffi
 */
public class Parser implements Serializable {
    /**
     * Default constructor
     */
    public Parser() {

    }

    /**
     * Converts the user input into descriptions to store in the Task instance.
     *
     * @param input Input from user as an array of Strings
     * @param action Type of task
     * @param limiter Delimitter specified. Example: " ", "/by", "/from", "/to"
     * @return the contents of the user inputs
     * @throws DukeException when an error occurs during execution depending on the type of task
     */
    public String convertToUserInput(String[] input, TypeOfTask action, String limiter) throws DukeException {
        switch(action) {
        case find: {
            return convertFindInput(input, limiter);
        }
        case todo: {
            return convertTodoInput(input, limiter);
        }
        case deadline: {
            return convertDeadlineInput(input, limiter);
        }
        case event: {
            return convertEventInput(input, limiter);
        }
        case delete: {
            return convertDeleteInput(input, limiter);
        }
        default:
            throw new DukeException();
        }
    }

    /**
     * Converts user inputs when user uses the Find command
     *
     * @param input Input from user as an array of Strings
     * @param limiter Delimitter specified. Example: " ", "/by", "/from", "/to"
     * @return the contents of the user inputs
     * @throws DukeException when an error occurs during execution depending on the type of task
     */
    public String convertFindInput(String[] input, String limiter) throws DukeException {
        String userInput = String.join(" ", Arrays.copyOfRange(input, 0, input.length));
        boolean isUserInputNull = userInput.equals("") || userInput == null;
        if (isUserInputNull) {
            throw new DukeException(TypeOfTask.find, 0);
        } else {
            return userInput;
        }
    }

    /**
     * Converts user inputs when user uses the Todo command
     *
     * @param input Input from user as an array of Strings
     * @param limiter Delimitter specified. Example: " ", "/by", "/from", "/to"
     * @return the contents of the user inputs
     * @throws DukeException when an error occurs during execution depending on the type of task
     */
    public String convertTodoInput(String[] input, String limiter) throws DukeException {
        String userInput = String.join(" ", Arrays.copyOfRange(input, 0, input.length));
        boolean isUserInputNull = userInput.equals("") || userInput == null;
        if (isUserInputNull) {
            throw new DukeException(TypeOfTask.todo, 0);
        } else {
            return userInput;
        }
    }

    /**
     * Converts user inputs when user uses the Deadline command
     *
     * @param input Input from user as an array of Strings
     * @param limiter Delimitter specified. Example: " ", "/by", "/from", "/to"
     * @return the contents of the user inputs
     * @throws DukeException when an error occurs during execution depending on the type of task
     */
    public String convertDeadlineInput(String[] input, String limiter) throws DukeException {
        String userInput = "";
        if (!limiter.equals("/by")) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/by")) {
                    break;
                } else {
                    userInput += input[i] + " ";
                }
            }
        } else {
            // to get the date and time after "/by"
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/by")) {
                    for (int j = i + 1; j < input.length; j++) {
                        userInput += input[j] + " ";
                    }
                    break;
                }
            }
        }
        boolean isUserInputNull = userInput.equals("") || userInput == null;
        if (isUserInputNull) {
            throw new DukeException(TypeOfTask.deadline, 0);
        } else {
            return userInput;
        }
    }

    /**
     * Converts user inputs when user uses the Event command
     *
     * @param input Input from user as an array of Strings
     * @param limiter Delimitter specified. Example: " ", "/by", "/from", "/to"
     * @return the contents of the user inputs
     * @throws DukeException when an error occurs during execution depending on the type of task
     */
    public String convertEventInput(String[] input, String limiter) throws DukeException {
        String userInput = "";
        if (limiter.equals("/from")) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/from")) {
                    for (int j = i + 1; j < input.length; j++) {
                        if (input[j].equals("/to")) {
                            break;
                        } else {
                            userInput += input[j] + " ";
                        }
                    }
                    break;
                }
            }
        } else if (limiter.equals("/to")) {
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/to")) {
                    for (int j = i + 1; j < input.length; j++) {
                        userInput += input[j] + " ";
                    }
                    break;
                }
            }
        } else {
            // to get the user's input before the "/from" limiter
            for (int i = 0; i < input.length; i++) {
                if (input[i].equals("/from")) {
                    break;
                } else {
                    userInput += input[i] + " ";
                }
            }
        }
        boolean isUserInputNull = userInput.equals("") || userInput == null;
        if (isUserInputNull) {
            throw new DukeException(TypeOfTask.event, 0);
        } else {
            return userInput;
        }
    }

    /**
     * Converts user inputs when user uses the Delete command
     *
     * @param input Input from user as an array of Strings
     * @param limiter Delimitter specified. Example: " ", "/by", "/from", "/to"
     * @return the contents of the user inputs
     * @throws DukeException when an error occurs during execution depending on the type of task
     */
    public String convertDeleteInput(String[] input, String limiter) throws DukeException {
        if (input.length == 0) {
            throw new DukeException(TypeOfTask.delete, 0);
        } else if (input.length > 1) {
            throw new DukeException(TypeOfTask.delete, 1);
        } else {
            return input[0];
        }
    }

    /**
     * Returns the local date in another format
     *
     * @param date Date
     * @return Date
     * @throws DukeException when error occurs during conversion
     */
    public LocalDate covertToLocalDate(String date) throws DukeException {
        String[] dateFormats = {"dd/MM/yyyy", "yyyy-MM-dd", "MM-dd-yyyy", "d/MM/yyyy", "d/M/yyyy"};
        LocalDate localDate;
        for (String dateFormat : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
                localDate = LocalDate.parse(date, formatter);
                return localDate;
            } catch (DateTimeParseException e) {
                // continue trying other formats
                continue;
            }
        }
        throw new DukeException(TypeOfTask.parser, 0);
    }

    /**
     * Returns the local time
     *
     * @param time Time
     * @return local time
     * @throws DukeException when error occurs during execution
     */
    public LocalTime convertToLocalTime(String time) throws DukeException {
        String[] timeFormats = {"h:mm a", "HH:mm", "hh:mm a", "HH:mm:ss", "HHmm", "h:mma", "hh:mma", "h:a"};
        LocalTime localTime;
        for (String timeFormat : timeFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
                localTime = LocalTime.parse(time.toUpperCase(), formatter);
                return localTime;
            } catch (DateTimeParseException e) {
                // continue trying other formats
                continue;
            }
        }
        throw new DukeException(TypeOfTask.parser, 1);
    }

    /**
     * Converts the beginning of the user's input into a TypeOfTask enum type
     *
     * @param command Command given by the user
     * @return Command instance
     * @throws DukeException when error occurs when creating the Command instance
     */
    public Command parse(String command) throws DukeException {
        String[] input = command.split(" ");
        String typeOfTask = input[0];
        String[] contents = Arrays.copyOfRange(input, 1, input.length);
        switch(typeOfTask) {
        case "mark":
            return new MarkCommand(contents);
        case "unmark":
            return new UnmarkCommand(contents);
        case "list":
            return new ListCommand(contents);
        case "bye":
        case "quit":
            return new ByeCommand();
        case "todo":
            return new TodoCommand(contents);
        case "deadline":
            return new DeadlineCommand(contents);
        case "event":
            return new EventCommand(contents);
        case "delete":
            return new DeleteCommand(contents);
        case "find":
            return new FindCommand(contents);
        default: throw new DukeException();
        }
    }
}
