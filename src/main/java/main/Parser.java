package main;

import java.time.DateTimeException;
import java.time.LocalDate;

import command.AddDeadlineCommand;
import command.AddEventCommand;
import command.AddTodoCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.MarkDoneCommand;
import command.MarkNotDoneCommand;
import command.RecurrenceCommand;

/**
 * Class that makes sense of user input.
 */
public class Parser {

    /**
     * Extracts the message of input and calls the corresponding commands from input.
     *
     * @param command Message that user input.
     * @return Corresponding command.
     * @throws DukeException Throws exception when user input invalid command or command with wrong format.
     */
    public static Command parse(String command) throws DukeException {
        try {
            if (command.equals("bye")) {
                return new ExitCommand();
            } else if (command.equals("list")) {
                return new ListCommand();
            } else if (command.startsWith("find")) {
                return new FindCommand(command.substring(5));
            } else if (command.startsWith("mark")) {
                if (isInvalidNumber(command.substring(5))) {
                    throw new DukeException("Please enter a valid number");
                }
                return new MarkDoneCommand(Integer.parseInt(command.substring(5)));
            } else if (command.startsWith("unmark")) {
                if (isInvalidNumber(command.substring(7))) {
                    throw new DukeException("Please enter a valid number");
                }
                return new MarkNotDoneCommand(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("delete")) {
                if (isInvalidNumber(command.substring(7))) {
                    throw new DukeException("Please enter a valid number");
                }
                return new DeleteTaskCommand(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("recurrence")) {
                String[] str = command.substring(11).split(" ");
                if (isInvalidNumber(str[1])) {
                    throw new DukeException("Please enter a valid number");
                }
                return new RecurrenceCommand(str[0], Integer.parseInt(str[1]));
            } else if (command.startsWith("todo")) {
                return new AddTodoCommand(command.substring(5));
            } else if (command.startsWith("deadline")) {
                String[] str = command.substring(9).split("/");
                String description = str[0];
                if (isInvalidDate(str[1].substring(3, 13))) {
                    throw new DukeException("Please enter a valid date in the form YYYY-MM-DD");
                }
                LocalDate dueDate = convertStringToDate(str[1].substring(3, 13));
                return new AddDeadlineCommand(description, dueDate);
            } else if (command.startsWith("event")) {
                String[] str = command.substring(6).split("/");
                String description = str[0];
                if (isInvalidDate(str[1].substring(5, 15)) || isInvalidDate(str[2].substring(3, 13))) {
                    throw new DukeException("Please enter a valid date in the form YYYY-MM-DD");
                }
                LocalDate startDate = convertStringToDate(str[1].substring(5, 15));
                LocalDate endDate = convertStringToDate(str[2].substring(3, 13));
                if (endDate.isBefore(startDate)) {
                    throw new DukeException("ending date cannot be before starting date");
                }
                return new AddEventCommand(description, startDate, endDate);
            } else {
                throw new DukeException("Please input a valid command");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Description cannot be empty");
        }
    }

    /**
     * Checks if the date input is correct.
     *
     * @param date Date input by user.
     * @return Returns true if the date is invalid.
     */
    private static boolean isInvalidDate(String date) {
        try {
            LocalDate.parse(date);
        } catch (DateTimeException e) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the number that users input can be converted to Integers.
     *
     * @param s Input by users that should be converted to Integers.
     * @return Returns true if the input is invalid.
     */
    private static boolean isInvalidNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    /**
     * Converts the time input by users to a LocalDate.
     *
     * @param time Time input by users that should be converted to LocalDate.
     * @return Returns the corresponding LocalDate from input.
     */
    private static LocalDate convertStringToDate(String time) {
        return LocalDate.parse(time);
    }
}
