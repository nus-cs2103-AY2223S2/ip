package uwuke.controller;

/**
 * Enum thats lists all possible commands
 */
public enum Command {
    TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, FIND, UNKNOWN, BYE;

    public static Command matchCommand(String input) {
        if (input.equals("list")) {
            return Command.LIST;
        } else if (input.matches("^mark\\s\\d+$")) {
            return Command.MARK;
        } else if (input.matches("^unmark\\s\\d+$")) {
            return Command.UNMARK;
        } else if (input.matches("^deadline\\s.+/by\\s.+$")) {
            return Command.DEADLINE;
        } else if (input.matches("^event\\s.+/from\\s.+/to\\s.+$")) {
            return Command.EVENT;
        } else if (input.matches("^todo\\s.+$")) {
            return Command.TODO;
        } else if (input.matches("^delete\\s\\d+$")) {
            return Command.DELETE;
        } else if (input.matches("find\\s.+")) {
            return Command.FIND;
        } else if (input.equals("bye")) {
            return Command.BYE;
        } else {
            return Command.UNKNOWN;
        }
    }
}
