package main;

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

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Class that makes sense of user input.
 */
public class Parser {

    /**
     * Extracts the message of input and calls the corresponding commands from input.
     *
     * @param command Message that user input.
     * @return Corresponding command.
     * @throws DukeException Throws exception when user input invalid command.
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
                if (isInvalidNumeric(command.substring(5))) {
                    throw new DukeException("Please enter a valid number");
                }
                return new MarkDoneCommand(Integer.parseInt(command.substring(5)));
            } else if (command.startsWith("unmark")) {
                if (isInvalidNumeric(command.substring(7))) {
                    throw new DukeException("Please enter a valid number");
                }
                return new MarkNotDoneCommand(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("delete")) {
                if (isInvalidNumeric(command.substring(7))) {
                    throw new DukeException("Please enter a valid number");
                }
                return new DeleteTaskCommand(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("todo")) {
                return new AddTodoCommand(command.substring(5));
            } else if (command.startsWith("deadline")) {
                String[] str = command.substring(9).split("/");
                String description = str[0];
                String dueDate = str[1].substring(3, 13);
                if (isInvalidDate(dueDate)) {
                    throw new DukeException("Please enter a valid date in the form YYYY-MM-DD");
                }
                return new AddDeadlineCommand(description, convertStringToDate(dueDate));
            } else if (command.startsWith("event")) {
                String[] str = command.substring(6).split("/");
                String description = str[0];
                String startDate = str[1].substring(5, 15);
                String endDate = str[2].substring(3, 13);
                if (isInvalidDate(startDate) || isInvalidDate(endDate)) {
                    throw new DukeException("Please enter a valid date in the form YYYY-MM-DD");
                }
                return new AddEventCommand(description, convertStringToDate(startDate), convertStringToDate(endDate));
            } else {
                throw new DukeException("Please input a valid command");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Description cannot be empty");
        }
    }

    private static boolean isInvalidDate(String time) {
        try {
            LocalDate.parse(time);
        } catch (DateTimeException e) {
            return true;
        }
        return false;
    }

    private static boolean isInvalidNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private static LocalDate convertStringToDate(String time) {
        return LocalDate.parse(time);
    }
}
