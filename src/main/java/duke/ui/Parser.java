package duke.ui;

import duke.command.*;
import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {

    public Parser() {
    }

    public static Command parse(String input) throws DukeException, IllegalArgumentException,
            DateTimeParseException, NumberFormatException {

        if (input.equals("bye")) {
            return new ExitCommand();
        }

        // Split strings into 2, first part is the operation, 2nd part is the description
        String[] command = input.split(" ", 2);
        Operation op = Operation.valueOf(command[0].toUpperCase());

        if (!op.equals(Operation.LIST) && command.length < 2) {
            throw new DukeException("No command description given.");
        }

        switch (op) {
        case MARK:
            return markTaskParser(input);
        case UNMARK:
            return unmarkTaskParser(input);
        case TODO:
        case DEADLINE:
        case EVENT:
            return addTaskParser(input);
        case LIST:
            return new ListTasksCommand();
        case DELETE:
            return deleteTaskParser(input);
        }

        return null; // cannot reach here, as duke.command.Operation.valueOf throws IllegalArgumentException
    }

    public static Command markTaskParser(String input) throws NumberFormatException {
        String[] command = input.split(" ", 2);
        int taskIndex = Integer.parseInt(command[1]); // Throws exception if not valid string as id
        return new MarkTaskCommand(taskIndex);
    }

    public static Command unmarkTaskParser(String input) throws NumberFormatException {
        String[] command = input.split(" ", 2);
        int taskIndex = Integer.parseInt(command[1]); // Throws exception if not valid string as id
        return new UnmarkTaskCommand(taskIndex);
    }

    public static Command addTaskParser(String input) throws DateTimeParseException {
        String[] command = input.split(" ", 2);
        Operation op = Operation.valueOf(command[0].toUpperCase());
        String description = command[1];

        switch (op) {
        case TODO:
            return new AddTodoCommand(description);
        case DEADLINE:
            String[] deadlineString = description.split("/by", 2);
            String deadlineDescription = deadlineString[0];
            LocalDate by = LocalDate.parse(deadlineString[1].trim());

            return new AddDeadlineCommand(deadlineDescription, by);
        case EVENT:
            String[] eventString = description.split("/from", 2);
            String eventDescription = eventString[0];

            // Parse the string to get to and from dates of the event
            String[] fromAndTo = eventString[1].split("/to", 2);
            LocalDate from = LocalDate.parse(fromAndTo[0].trim());
            LocalDate to = LocalDate.parse(fromAndTo[1].trim());

            return new AddEventCommand(eventDescription, from, to);
        }

        return null;
    }

    public static Command deleteTaskParser(String input) throws NumberFormatException {
        String[] command = input.split(" ", 2);
        int taskIndex = Integer.parseInt(command[1]); // Throws exception if not valid string as id
        return new DeleteTaskCommand(taskIndex);
    }




}
