package catbot.parser;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Objects;

import catbot.CatBotException;
import catbot.commands.AddDeadlineCommand;
import catbot.commands.AddEventCommand;
import catbot.commands.AddRecurringCommand;
import catbot.commands.AddTodoCommand;
import catbot.commands.Command;
import catbot.commands.DeleteCommand;
import catbot.commands.EchoCommand;
import catbot.commands.FindCommand;
import catbot.commands.ListCommand;
import catbot.commands.MarkCommand;
import catbot.commands.SaveCommand;

/**
 * Handles parsing user input.
 */
public class Parser {

    /**
     * Parses an input string to figure out the command.
     * @param command is the user input for a command.
     * @return a {@code Command} object for the user command.
     * @throws CatBotException if the user input is malformed.
     */
    public static Command parse(String command) throws CatBotException {
        assert !Objects.equals(command, "");
        String[] commandComponents = command.split(" ", 2);
        String[] temp;
        switch (commandComponents[0].toLowerCase(Locale.ROOT)) {
        case "todo":
            try {
                return new AddTodoCommand(commandComponents[1].strip());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "deadline":
            try {
                temp = commandComponents[1].split("/by", 2);
                LocalDateTime by = LocalDateTime.parse(temp[1].strip());
                return new AddDeadlineCommand(temp[0].strip(), by);
            } catch (DateTimeParseException e) {
                throw new CatBotException("Dates should be in the format yyyy-MM-ddTHH:mm");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "event":
            try {
                temp = commandComponents[1].split("/from|/to", 3);
                LocalDateTime from = LocalDateTime.parse(temp[1].strip());
                LocalDateTime to = LocalDateTime.parse(temp[2].strip());
                return new AddEventCommand(temp[0].strip(), from, to);
            } catch (DateTimeParseException e) {
                throw new CatBotException("Dates should be in the format yyyy-MM-ddTHH:mm");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "recurring":
            try {
                temp = commandComponents[1].split("/at|/every", 3);
                LocalDateTime at = LocalDateTime.parse(temp[1].strip());
                Duration every = Duration.parse(temp[2].strip());
                if (every.isNegative() || every.isZero()) {
                    throw new CatBotException("I can't travel back in time ... yet!");
                }
                return new AddRecurringCommand(temp[0].strip(), at, every);
            } catch (DateTimeParseException e) {
                if (e.toString().contains("Duration")) {
                    throw new CatBotException("Durations should be in the format P#DT#H#M#S");
                }
                throw new CatBotException("Dates should be in the format yyyy-MM-ddTHH:mm");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "list":
            return new ListCommand();

        case "mark":
            try {
                int index = Integer.parseInt(commandComponents[1].strip());
                return new MarkCommand(index - 1, true);
            } catch (NumberFormatException e) {
                throw new CatBotException(e + " isn't a number!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "unmark":
            try {
                int index = Integer.parseInt(commandComponents[1].strip());
                return new MarkCommand(index - 1, false);
            } catch (NumberFormatException e) {
                throw new CatBotException(e + " isn't a number!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "delete":
            try {
                int index = Integer.parseInt(commandComponents[1].strip());
                return new DeleteCommand(index - 1);
            } catch (NumberFormatException e) {
                throw new CatBotException(e + " isn't a number!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "find":
            return new FindCommand(commandComponents[1].strip());

        case "echo":
            try {
                return new EchoCommand(commandComponents[1].strip());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "bye":
            return new SaveCommand();

        default:
            throw new CatBotException("I don't know what you mean.");
        }
    }
}
