package duke.util;

import duke.Duke;
import duke.command.*;
import duke.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser that parses user input into commands.
 */
public class Parser {

    private static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    private String readCommand(String[] userCommand) {
        return userCommand[0];
    }

    private int queryInteger(String[] userCommand) throws DukeException {
        if (isInteger(userCommand)) {
            return Integer.parseInt(userCommand[1]);
        } else {
            throw new DukeException("error Invalid formatting for commands");
        }
    }

    private static boolean isInteger(String[] userCommand) throws DukeException {
        if (userCommand.length != 2) {
            throw new DukeException("error Invalid formatting for commands");
        }
        return isInteger(userCommand[1],10);
    }

    private static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(s.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

    /*
     * Parses the user input into a command.
     * 
     * @param userCommand User input.
     * @return Command to be executed.
     * @throws DukeException If there is an error in parsing the user input.
     */
    public Command parse(String[] userCommand) throws DukeException {
        String name;
        switch(this.readCommand(userCommand)) {

            case "todo":
                if (userCommand.length == 1) {
                    throw new DukeException("error no arguments");
                }
                return new TodoCommand(userCommand[1]);

            case "deadline":
                if (userCommand.length == 1) {
                    throw new DukeException("error no arguments");
                }
                String[] splitBy = userCommand[1].split(" /by ", 2);
                if (splitBy.length != 2) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                name = splitBy[0];
                String by = splitBy[1];
                LocalDateTime byTime = LocalDateTime.parse(by, DATETIME_FORMAT);
                return new DeadlineCommand(name, byTime);

            case "event":
                if (userCommand.length == 1) {
                    throw new DukeException("error no arguments");
                }
                String[] splitFrom = userCommand[1].split(" /from ", 2);
                if (splitFrom.length != 2) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                String[] splitTo = splitFrom[1].split(" /to ", 2);
                if (splitTo.length != 2) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                name = splitFrom[0];
                String from = splitTo[0];
                String to = splitTo[1];
                LocalDateTime fromTime = LocalDateTime.parse(from, DATETIME_FORMAT);
                LocalDateTime toTime = LocalDateTime.parse(to, DATETIME_FORMAT);
                return new EventCommand(name, fromTime, toTime);

            case "mark":
                return new MarkCommand(this.queryInteger(userCommand), true);

            case "unmark":
                return new MarkCommand(this.queryInteger(userCommand), false);

            case "list":
                if (userCommand.length > 1) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                return new ListCommand();

            case "bye":
                if (userCommand.length > 1) {
                    throw new DukeException("error Invalid formatting for commands");
                }
                return new ExitCommand();

            case "delete":
                return new DeleteCommand(this.queryInteger(userCommand));
        }
        throw new DukeException("Cannot recognise command!");
    }
}
