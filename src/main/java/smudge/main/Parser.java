package smudge.main;

import smudge.command.*;
import smudge.task.Deadline;
import smudge.task.Event;
import smudge.task.Todo;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser class parse input received from user and return respective commands
 */
public class Parser {
    /**
     * Takes in line of input as command from user. process the input
     * and check if valid, if it is then return respective commands,
     * else throw a Smudge exception to inform user invalidity of input.
     *
     * @param fullCommand the line of input from user
     * @return respective command to the input
     * @throws SmudgeException when input is invalid
     */
    public static Command parseCommand(String fullCommand) throws SmudgeException {
        String[] first = fullCommand.split(" ", 2 );

        switch (first[0]) {
        case ("bye"):
            return new ExitCommand();
        //Fallthrough
        case ("list"):
            return new ListCommand();
        //Fallthrough
        case ("todo"):
            if (first.length == 1) {
                throw new SmudgeException("☹ Meow!!! The description of a todo cannot be empty.");
            }
            if (first[1].isBlank()) {
                throw new SmudgeException("☹ Meow!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(first[1]));
        //Fallthrough
        case ("event"):
            if (first.length == 1) {
                throw new SmudgeException("☹ Meow!!! The description of an event cannot be empty.");
            }
            if (first[1].isBlank() ) {
                throw new SmudgeException("☹ Meow!!! The description of an event cannot be empty.");
            }
            if (!fullCommand.contains(" /from") || !fullCommand.contains(" /to")) {
                throw new SmudgeException("☹ Meow!!! Invalid input format. Please use format: event <description> /from <datetime> /to <datetime>");
            }
            try {
                String[] second = first[1].split("/from", 2);
                String[] third = second[1].split("/to", 2);

                if (second[0].isBlank()) {
                    throw new SmudgeException("☹ Meow!!! The description of an event cannot be empty.");
                }
                return new AddCommand(new Event(second[0].trim(),
                        LocalDateTime.parse(third[0].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")),
                        LocalDateTime.parse(third[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
            } catch (DateTimeParseException e) {
                throw new SmudgeException("☹ Meow!!! Invalid date time format. Please use DD/MM/YYYY HHMM format");
            }
            //Fallthrough
        case ("deadline"):
            if (first.length == 1) {
                throw new SmudgeException(" ☹ Meow!!! The description of a deadline cannot be empty.");
            }
            if (first[1].isBlank()) {
                throw new SmudgeException(" ☹ Meow!!! The description of a deadline cannot be empty.");
            }
            if (!fullCommand.contains(" /by")) {
                throw new SmudgeException("☹ Meow!!! Invalid input format. Please use format: deadline <description> /by <datetime>");
            }
            try {
                String[] second = first[1].split("/by");

                if (second[0].isBlank()) {
                    throw new SmudgeException("☹ Meow!!! The description of a deadline cannot be empty.");
                }
                return new AddCommand(new Deadline(second[0].trim(),
                        LocalDateTime.parse(second[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
            } catch (DateTimeParseException e) {
                throw new SmudgeException("☹ Meow!!! Invalid date time format. Please use <DD/MM/YYYY HHMM> format");
            }
            //Fallthrough
        case ("mark"):
            if (first.length == 1) {
                throw new SmudgeException("☹ Meow!!! The task number to be marked as done cannot be empty.");
            }
            if (first[1].isBlank()) {
                throw new SmudgeException("☹ Meow!!! The task number to be marked as done cannot be empty.");
            }
            if (first[1].equals("0")) {
                throw new SmudgeException("☹ Meow!!! The task number to be marked as done cannot be 0.");
            }
            return new MarkCommand(Integer.parseInt(first[1]));
        //Fallthrough
        case("unmark"):
            if (first.length == 1) {
                throw new SmudgeException("☹ Meow!!! The task number to be unmarked cannot be empty.");
            }
            if (first[1].isBlank()) {
                throw new SmudgeException("☹ Meow!!! The task number to be unmarked cannot be empty.");
            }
            if (first[1].equals("0")) {
                throw new SmudgeException("☹ Meow!!! The task number to be unmarked cannot be 0.");
            }
            return new UnmarkCommand(Integer.parseInt(first[1]));
        //Fallthrough
        case ("delete"):
            if (first.length == 1) {
                throw new SmudgeException("☹ Meow!!! The task number to be deleted cannot be empty.");
            }
            if (first[1].isBlank()) {
                throw new SmudgeException("☹ Meow!!! The task number to be deleted cannot be empty.");
            }
            if (first[1].equals("0")) {
                throw new SmudgeException("☹ Meow!!! The task number to be deleted cannot be 0.");
            }
            return new DeleteCommand(Integer.parseInt(first[1]));
        //Fallthrough
        case("find"):
            if (first.length == 1) {
                throw new SmudgeException("☹ Meow!!! The keyword for the find cannot be empty.");
            }
            if (first[1].isBlank()) {
                throw new SmudgeException("☹ Meow!!! The keyword for the find cannot be empty.");
            }
            return new FindCommand(first[1]);
        //Fallthrough
        case("help"):
            return new HelpCommand();
        //Fallthrough
        default:
            throw new SmudgeException("☹ Meow!!! Unknown command received. Please enter valid command. " +
                    "\n type 'help' for User Guide information");
            //Fallthrough
        }
    }
}