package duke.util;

import duke.util.Command.CommandType;

public class Parser {


    public static Command parse(String input) {
        if (input.equals("bye")) {
            return new Command(CommandType.EXIT);
        } else if (input.equals("list")) {
            return new Command(CommandType.LIST);
        } else if (input.matches("mark \\d+")) {
            String index = input.replace("mark ", "");
            return new Command(CommandType.MARK, index);
        } else if (input.matches("unmark \\d+")) {
            String index = input.replace("unmark ", "");
            return new Command(CommandType.UNMARK, index);
        } else if (input.matches("delete \\d+")) {
            String index = input.replace("delete ", "");
            return new Command(CommandType.DELETE_TASK, index);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return new Command(CommandType.ADD_TASK, input);
        } else {
            return new Command(CommandType.INVALID_COMMAND);
        }
    }

}
