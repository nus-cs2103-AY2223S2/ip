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
                return new MarkDoneCommand(Integer.parseInt(command.substring(5)));
            } else if (command.startsWith("unmark")) {
                return new MarkNotDoneCommand(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("delete")) {
                return new DeleteTaskCommand(Integer.parseInt(command.substring(7)));
            } else if (command.startsWith("todo")) {
                return new AddTodoCommand(command.substring(5));
            } else if (command.startsWith("deadline")) {
                String[] str1 = command.substring(9).split("/");
                try {
                    return new AddDeadlineCommand(str1[0],
                            LocalDate.parse(str1[1].substring(3, 13)));
                } catch (DateTimeException e) {
                    throw new DukeException("Please enter a valid date in the form YYYY-MM-DD");
                }
            } else if (command.startsWith("event")) {
                String[] str2 = command.substring(6).split("/");
                try {
                    LocalDate startDate = LocalDate.parse(str2[1].substring(5, 15));
                    LocalDate endDate = LocalDate.parse(str2[2].substring(3, 13));
                    if (endDate.isBefore(startDate)) {
                        throw new DukeException("ending date cannot be before starting date");
                    }
                    return new AddEventCommand(str2[0], startDate, endDate);
                } catch (DateTimeException e) {
                    throw new DukeException("Please enter a valid date in the form YYYY-MM-DD");
                }

            } else {
                throw new DukeException("Please input a valid command");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please input the description");
        }
    }
}
