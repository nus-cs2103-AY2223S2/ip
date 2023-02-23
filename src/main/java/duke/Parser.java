package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ErrorCommand;
import duke.command.FindCommand;
import duke.command.UpdateCommand;

/**
 * Represents a parser which converts CLI into a Command object.
 * Checks for format of CLI, throws exceptions to alert any incorrect input format.
 */
public class Parser {

    protected static String[] simpleCommands = new String[]{"bye", "list", "find", "help", "add"};
    protected static String[] moreCommands = new String[]{"todo", "deadline", "event", "mark", "unmark", "delete"};

    /**
     * Checks if the input command is one of the acceptable commands.
     *
     * @param commandName Input command name.
     * @return ture if input command is valid.
     */
    public static boolean isValidCommand(String commandName) {
        boolean valid = Arrays.asList(simpleCommands).contains(commandName)
                | Arrays.asList(moreCommands).contains(commandName);
        return valid;
    }

    /**
     * Parses user's input into a Command object.
     * Throws exceptions if the input has incorrect format.
     *
     * @param fullCommand User's input.
     * @param ui          Ui to show messages.
     * @return A Command object which contains information corresponds to the user's input.
     * @throws DukeException
     */
    public static Command parse(String fullCommand, Ui ui) throws DukeException {
        int firstWord = fullCommand.length() - 1;
        String commandName;
        try {
            firstWord = fullCommand.indexOf(' ');
            commandName = fullCommand.substring(0, firstWord);
        } catch (StringIndexOutOfBoundsException e) {
            commandName = fullCommand.trim();
        }

        if (!isValidCommand(commandName)) {
            throw new DukeException("Please enter a valid command\n"
                    + "You may enter [help] for more information :D");
        }

        assert !commandName.equals("");

        String command = fullCommand.substring(firstWord + 1);

        if (commandName.equals("todo")) {
            command = fullCommand.substring(4).trim();
        }

        switch (commandName) {
        case "bye":
        case "list":
        case "help":
        case "add": {
            return new Command(commandName);
        }
        case "mark":
        case "unmark":
        case "delete": {
            try {
                int index = Integer.parseInt(command.trim());
                return new UpdateCommand(commandName, index);
            } catch (NumberFormatException e) {
                return new ErrorCommand(ui.showTaskNoError());
            }
        }
        case "find": {
            return new FindCommand("find", command.trim());
        }
        case "todo": {
            String name = command.trim();
            if (name.isEmpty()) {
                return new ErrorCommand(ui.todoFormatAlert());
            } else {
                return new AddCommand("todo", name, null, null, null);
            }
        }
        case "deadline": {
            try {
                int firstSlash = command.indexOf('/');
                String name = command.substring(0, firstSlash);
                String dateInfo = command.substring(firstSlash + 1);
                if (name.isEmpty() || dateInfo.isEmpty()) {
                    throw new DukeException("the name or date information cannot be empty");
                } else {
                    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("ddMMuuuu HHmm");
                    LocalDateTime by = LocalDateTime.parse(dateInfo, dateformatter);
                    return new AddCommand("deadline", name, by, null, null);
                }
            } catch (DateTimeParseException | StringIndexOutOfBoundsException | DukeException e) {
                return new ErrorCommand(ui.deadlineFormatAlert());
            }
        }
        case "event": {
            try {
                int firstSlash = command.indexOf('/');
                int lastSlash = command.lastIndexOf('/');
                String name = command.substring(0, firstSlash);
                String timeFrom = command.substring(firstSlash + 1, lastSlash - 1);
                String timeTo = command.substring(lastSlash + 1);
                if (name.isEmpty() || timeFrom.isEmpty() || timeTo.isEmpty()) {
                    throw new DukeException("the name or time information cannot be empty");
                } else {
                    DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("ddMMuuuu HHmm");
                    LocalDateTime from = LocalDateTime.parse(timeFrom, dateformatter);
                    LocalDateTime to = LocalDateTime.parse(timeTo, dateformatter);
                    return new AddCommand("event", name, null, from, to);
                }
            } catch (DateTimeParseException | StringIndexOutOfBoundsException | DukeException e) {
                return new ErrorCommand(ui.eventFormatAlert());
            }
        }
        default: {
            return new ErrorCommand("May I know what type of task this is?");
        }
        }
    }
}
