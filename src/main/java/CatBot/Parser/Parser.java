package CatBot.Parser;

import CatBot.CatBotException;
import CatBot.Commands.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Parser {
    public static Command parse(String command) throws CatBotException {
        String[] cmd = command.split(" ", 2);
        String[] temp;
        switch (cmd[0].toLowerCase(Locale.ROOT)) {
        case "todo":
            try {
                return new AddCommand(cmd[1].strip());
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "deadline":
            try {
                temp = cmd[1].split("/by", 2);
                LocalDateTime by = LocalDateTime.parse(temp[1].strip());
                return new AddCommand(temp[0].strip(), by);
            } catch (DateTimeParseException e) {
                throw new CatBotException("Dates should be in the format yyyy-MM-ddTHH:mm");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "event":
            try {
                temp = cmd[1].split("/from|/to", 3);
                LocalDateTime from = LocalDateTime.parse(temp[1].strip());
                LocalDateTime to = LocalDateTime.parse(temp[2].strip());
                return new AddCommand(temp[0].strip(), from, to);
            } catch (DateTimeParseException e) {
                throw new CatBotException("Dates should be in the format yyyy-MM-ddTHH:mm");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "list":
            return new ListCommand();

        case "mark":
            try {
                int index = Integer.parseInt(cmd[1].strip());
                return new MarkCommand(index - 1, true);
            } catch (NumberFormatException e) {
                throw new CatBotException(e + " isn't a number!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "unmark":
            try {
                int index = Integer.parseInt(cmd[1].strip());
                return new MarkCommand(index - 1, false);
            } catch (NumberFormatException e) {
                throw new CatBotException(e + " isn't a number!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "delete":
            try {
                int index = Integer.parseInt(cmd[1].strip());
                return new DeleteCommand(index - 1);
            } catch (NumberFormatException e) {
                throw new CatBotException(e + " isn't a number!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new CatBotException("That's the wrong format!");
            }

        case "echo":
            try {
                return new EchoCommand(cmd[1].strip());
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
