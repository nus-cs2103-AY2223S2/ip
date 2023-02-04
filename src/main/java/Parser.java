import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parse the command
 *
 */
public class Parser {
    private TodoList todoList;

    private enum Instructions {todo, deadline, event, mark, unmark, delete}

    public Parser(TodoList todoList) {
        this.todoList = todoList;
    }

    public Command parse(String command) throws DukeExceptions {
        String[] split_command = command.split(" ", 2);
        String instruction = split_command[0];

        if (instruction.equals("list")) {
            if (split_command.length == 1) {
                return new ListCommand(todoList);
            } else {
                throw new DukeExceptions("OOPS!!! The description of a list cannot have other parameters");
            }
        }

        //check for valid instructions
        for (Instructions validInstruction : Instructions.values()) {
            if (validInstruction.name().equals(instruction)) {
                if (split_command.length == 1) {
                    throw new DukeExceptions(String.format("OOPS!!! The description of a %s cannot be empty.", instruction));
                } else if (instruction.equals("mark")) {
                    int digit = Integer.parseInt(split_command[1]);
                    return new MarkCommand(todoList, digit);
                } else if (instruction.equals("unmark")) {
                    int digit = Integer.parseInt(split_command[1]);
                    return new UnmarkCommand(todoList, digit);
                } else if (instruction.equals("delete")) {
                    int digit = Integer.parseInt(split_command[1]);
                    return new DeleteCommand(todoList, digit);
                } else {
                    return new AddTaskCommand(todoList, instruction, split_command[1]);
                }
            }
        }
        throw new DukeExceptions("OOPS!!! I'm sorry, but I don't know what that means.");
    }

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

    public static String parseStringDate(LocalDate possibleDeadlineDate) {
        return possibleDeadlineDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

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

    public static String parseStringTime(LocalTime possibleDeadlineTime) {
        return possibleDeadlineTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
    }
}
