package main;

import command.*;

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
                    return new AddDeadlineCommand(str1[0], LocalDate.parse(str1[1].substring(3, 13)));
                } catch (DateTimeException e) {
                    throw new DukeException("Please enter a valid date in the form YYYY-MM-DD");
                }
            } else if (command.startsWith("event")) {
                String[] str2 = command.substring(6).split("/");
                try {
                    return new AddEventCommand(str2[0], LocalDate.parse(str2[1].substring(5, 15)), LocalDate.parse(str2[2].substring(3, 13)));
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
