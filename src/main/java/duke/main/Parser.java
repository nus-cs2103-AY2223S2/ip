package duke.main;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

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
     * else throw a Duke exception to inform user invalidity of input.
     * @param fullCommand the line of input from user
     * @return respective command to the input
     * @throws DukeException when input is invalid
     */
    public static Command parseCommand(String fullCommand) throws DukeException {
        String[] splits = fullCommand.split(" ", 2 );
        switch (splits[0]) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand();
        case ("todo"):
            if (splits.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            if (splits[1].isBlank()) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            return new AddCommand(new Todo(splits[1]));
        case ("event"): {
            if (splits.length == 1) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (splits[1].isBlank() ) {
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if (!fullCommand.contains(" /from") || !fullCommand.contains(" /to")) {
                throw new DukeException("☹ OOPS!!! Please use format: event <description> /from <datetime> /to <datetime>");
            }
            try {
                String[] secondsplits = splits[1].split("/from", 2);
                String[] thirdsplits = secondsplits[1].split("/to", 2);
                if (secondsplits[0].isBlank()) {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                }
                return new AddCommand(new Event(secondsplits[0].trim(), LocalDateTime.parse(thirdsplits[0].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm")), LocalDateTime.parse(thirdsplits[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! Invalid date time format. Please use DD/MM/YYYY HHMM format");
            }
        }
        case ("deadline"): {
            if (splits.length == 1) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (splits[1].isBlank()) {
                throw new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if (!fullCommand.contains(" /by")) {
                throw new DukeException("☹ OOPS!!! Please use format: deadline <description> /by <datetime>");
            }
            try {
                String[] secondsplits = splits[1].split("/by");
                if (secondsplits[0].isBlank()) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                return new AddCommand(new Deadline(secondsplits[0].trim(), LocalDateTime.parse(secondsplits[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))));
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! Invalid date time format. Please use <DD/MM/YYYY HHMM> format");
            }
        }
        case ("mark"):
            return new MarkCommand(Integer.parseInt(splits[1]));
        case("unmark"):
            return new UnmarkCommand(Integer.parseInt(splits[1]));
        case ("delete"):
            return new DeleteCommand(Integer.parseInt(splits[1]));
        case("find"):
            if (splits.length == 1) {
                throw new DukeException("☹ OOPS!!! The keyword for the find cannot be empty.");
            }
            if (splits[1].isBlank()) {
                throw new DukeException("☹ OOPS!!! The keyword for the find cannot be empty.");
            }
            return new FindCommand(splits[1]);
            default:
                throw new DukeException("☹ OOPS!!! Unknown command received. Please enter valid command");
        }
    }
}