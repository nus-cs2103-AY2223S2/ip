package utils;

import controllers.*;
import enums.CommandType;
import exceptions.DukeException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Pattern VALID_COMMAND = Pattern.compile("(?<cmd>\\S+)(?<arguments>.*)?");
    private static final Command invalidCommand =  new Command(CommandType.INVALID) {
        @Override
        public void execute() throws DukeException {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    };

    private static Command parse(String cmd, String arguments) {
        switch(cmd) {
            case "bye":      return new GoodbyeCommand();
            case "list":     return new ListCommand();
            case "mark":     return new MarkCommand(arguments);
            case "date":     return new DateCommand(arguments);
            case "unmark":   return new UnmarkCommand(arguments);
            case "delete":   return new DeleteCommand(arguments);
            case "todo":     return new TodoCommand(arguments);
            case "deadline": return new DeadlineCommand(arguments);
            case "event":    return new EventCommand(arguments);
            default:         return invalidCommand;
        }
    }

    public static Command parse(String input) {
        String uInput = input.toLowerCase();
        Matcher matcher = VALID_COMMAND.matcher(uInput);
        if (matcher.find()) {
            String cmdType = matcher.group("cmd");
            return parse(cmdType, uInput);
        } else {
            return invalidCommand;
        }
    }
}
