package duke;


import duke.command.Command;
import duke.command.CreateDeadline;
import duke.command.CreateEvent;
import duke.command.CreateTodo;
import duke.command.Delete;
import duke.command.Exit;
import duke.command.List;
import duke.command.Mark;
import duke.command.Unmark;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] split = fullCommand.split(" ", 2);

        if (split[0].equals("bye") && split.length == 1) {
            return new Exit();
        } else if (split[0].equals("list") && split.length == 1) {
            return new List();
        } else if (split[0].equals("mark")) {
            if (split.length == 1) {
                throw new DukeException("Please specify the task you want to mark.");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Mark(i);
            } catch (NumberFormatException e) {
                throw new DukeException("Please specify a valid task number.");
            }
        } else if (split[0].equals("unmark")) {
            if (split.length == 1) {
                throw new DukeException("Please specify the task you want to unmark.");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Unmark(i);
            } catch (NumberFormatException e) {
                throw new DukeException("Please specify a valid task number.");
            }
        } else if (split[0].equals("delete")) {
            if (split.length == 1) {
                throw new DukeException("Please specify the task you want to delete.");
            }
            try {
                Integer i = Integer.parseInt(split[1]);
                return new Delete(i);
            } catch (NumberFormatException e) {
                throw new DukeException("Please specify a valid task number.");
            }
        } else if (split[0].equals("todo")) {
            if (split.length == 1) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return new CreateTodo(split[1]);
        } else if (split[0].equals("deadline")) {
            if (split.length == 1) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            String[] tokens = split[1].split(" /by ", 2);
            if (tokens.length == 1) {
                throw new DukeException("Please provide a deadline for this task.");
            }
            return new CreateDeadline(tokens[0], parseDate(tokens[1]));
        } else if (split[0].equals("event")) {
            if (split.length == 1) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            String[] tokens = split[1].split(" /from ", 2);
            if (tokens.length == 1) {
                throw new DukeException("Please provide a start time for this event.");
            }
            String[] tokens2 = tokens[1].split(" /to ", 2);
            if (tokens2.length == 1) {
                throw new DukeException("Please provide an end time for this event.");
            }
            return new CreateEvent(tokens[0], parseDate(tokens2[0]), parseDate(tokens2[1]));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static LocalDate parseDate(String date) throws DukeException {
        try {
            return LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a valid date in the format yyyy-mm-dd.");
        }
    }
}
