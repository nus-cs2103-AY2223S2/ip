package presenter;

import command.CommandFactory;
import interfaces.Command;
import command.CommandFactory.CommandType;
import interfaces.CommandEventListener;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {
    private final CommandEventListener exitEventListener;
    private final CommandFactory commandFactory;
    InputParser(CommandEventListener exitEventListener, CommandFactory commandFactory) {
        this.exitEventListener = exitEventListener;
        this.commandFactory = commandFactory;
    }

    Command parseInput(String input) {
        String deadlineRegex = "^deadline (.*?)(?: /by (.*))$";
        String eventRegex = "^event (.*?)(?: /from (.*?) /to (.*))$";
        String event = "event project meeting /from Mon 2pm /to 4pm";
        Pattern deadlinePattern = Pattern.compile(deadlineRegex);
        Pattern eventPattern = Pattern.compile(eventRegex);
        String[] tokens = input.split(" ");
        Matcher matcher;
        switch (tokens[0]) {
            case "bye":
                exitEventListener.onCommandEvent(input);
                return commandFactory.createCommand(CommandType.BYE);
            case "greet":
                return commandFactory.createCommand(CommandType.GREET);
            case "list":
                return commandFactory.createCommand(CommandType.LIST);
            case "todo":
                return commandFactory.createCommand(CommandType.CREATE_TODO, input.substring(4).strip());
            case "deadline":
                matcher = deadlinePattern.matcher(input);
                // handle matcher not matching
                if (matcher.matches()) {
                    String taskDescription = matcher.group(1);
                    String deadline = matcher.group(2);
                    return commandFactory.createCommand(CommandType.CREATE_DEADLINE, taskDescription, deadline);
                }
                case "event":
                    matcher = eventPattern.matcher(input);
                    if (matcher.matches()) {
                        String eventDescription = matcher.group(1);
                        String startTime = matcher.group(2);
                        String endTime = matcher.group(3);
                        return commandFactory.createCommand(CommandType.CREATE_EVENT, eventDescription, startTime, endTime);
                    }

                case "mark":
                return commandFactory.createCommand(CommandType.MARK_DONE, tokens[1]);
                // handle improper arguments
            case "unmark":
                return commandFactory.createCommand(CommandType.MARK_UNDONE, tokens[1]);
            case "delete":
                return commandFactory.createCommand(CommandType.DELETE_TASK, tokens[1]);
            default:
                return null;
        }
//        String taskRegex = "^(.*?)(?: \\/by (.*)| \\/from (.*?) \\/to (.*))?$";
    }
}
