package duke;

import duke.command.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses the command.
 */
public class Parser {
    private TodoList todoList;

    private enum Instructions {todo, deadline, event, mark, unmark, delete, find}

    public Parser(TodoList todoList) {
        this.todoList = todoList;
    }

    /**
     * Returns a Command object (can be ListCommand, ...) of the input String.
     *
     * @param command String input.
     * @return Command Object corresponding to the input String command.
     * @throws DukeExceptions If input syntax is incorrect.
     */
    public Command parse(String command) throws DukeExceptions {
        String[] split_command = command.split(" ", 2);
        String instruction = split_command[0];
        if (instruction.equals("list")) {
            if (split_command.length == 1) {
                return new ListCommand(todoList);
            } else {
                throw new DukeExceptions("OOPS!!! The description of a list " +
                        "cannot have other parameters");
            }
        }

        //check for valid instructions
        for (Instructions validInstruction : Instructions.values()) {
            if (validInstruction.name().equals(instruction)) {
                if (split_command.length == 1) {
                    throw new DukeExceptions(String.format("OOPS!!! The description of a %s cannot be empty.",
                            instruction));
                } else if (instruction.equals("mark")) {
                    int digit = Integer.parseInt(split_command[1]);
                    return new MarkCommand(todoList, digit);
                } else if (instruction.equals("unmark")) {
                    int digit = Integer.parseInt(split_command[1]);
                    return new UnmarkCommand(todoList, digit);
                } else if (instruction.equals("delete")) {
                    int digit = Integer.parseInt(split_command[1]);
                    return new DeleteCommand(todoList, digit);
                } else if (instruction.equals("find")) {
                    return new FindCommand(todoList, split_command[1]);
                } else {
                    return new AddTaskCommand(todoList, instruction, split_command[1]);
                }
            }
        }
        throw new DukeExceptions("OOPS!!! I'm sorry, but I don't know what that means.");
    }

    /**
     * Returns LocalDate object capture the date of an event.
     *
     * @param possibleDateTime A string array may contain date information.
     * @return LocalDate object capture the date or null if there is no information.
     * @throws DateTimeParseException If there is no information.
     */
    public static LocalDate parseDate(String[] possibleDateTime) throws DateTimeParseException {
        try {
            //parse date
            LocalDate possibleDeadlineDate = LocalDate.parse(possibleDateTime[0]);
            return possibleDeadlineDate;
        } catch (DateTimeParseException ex) {
            //can not parse date
            return null;
        }
    }

    /**
     * Returns String representation of the date of an event.
     *
     * @param possibleDeadlineDate LocalDate object capture the date of an event.
     * @return String representation of the date of an event.
     */
    public static String parseStringDate(LocalDate possibleDeadlineDate) {
        return possibleDeadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns LocalTime object capture the time of an event.
     *
     * @param possibleDateTime A string array may contain time information.
     * @return LocalTime object capture the time or null if there is no information.
     * @throws DateTimeParseException If there is no information.
     */
    public static LocalTime parseTime(String[] possibleDateTime) throws DateTimeParseException {
        try {
            //parse date
            LocalTime possibleDeadlineTime = (possibleDateTime.length == 1)
                    ? LocalTime.parse(possibleDateTime[0])
                    : LocalTime.parse(possibleDateTime[1]);
            return possibleDeadlineTime;
        } catch (DateTimeParseException ex) {
            //can not parse date
            return null;
        }
    }

    /**
     * Returns String representation of the time of an event.
     *
     * @param possibleDeadlineTime LocalTime object capture the time.
     * @return String representation of the time of an event.
     */
    public static String parseStringTime(LocalTime possibleDeadlineTime) {
        return possibleDeadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }
}
