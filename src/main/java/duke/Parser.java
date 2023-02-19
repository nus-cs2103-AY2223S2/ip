package duke;

import java.time.format.DateTimeParseException;

/**
 * Parser class to parse in input.
 */
public class Parser {
    private static final Ui ui = new Ui();

    /**
     * parse method to take in input and execute the required command.
     * @param cmd the input command by user.
     * @param list the TaskList that is to be accessed.
     * @return a String that is created from executing the command.
     * @throws DukeException if command is unknown.
     */
    public static String parse(String cmd, TaskList list) throws DukeException, DateTimeParseException {
        Command command = new Command(cmd, list, ui);
        try {
            if (cmd.equals("list")) {
                return command.listCommand(list);
            } else if (cmd.startsWith("mark")) {
                return command.markCommand();
            } else if (cmd.startsWith("unmark")) {
                return command.unmarkCommand();
            } else if (cmd.startsWith("todo") || cmd.startsWith("t ")) {
                return command.toDoCommand();
            } else if (cmd.startsWith("deadline") || cmd.startsWith("d ")) {
                try {
                    return command.deadlineCommand();
                } catch (DateTimeParseException e) {
                    throw new DukeException("change date format");
                }
            } else if (cmd.startsWith("event") || cmd.startsWith("e ")) {
                return command.eventCommand();
            } else if (cmd.startsWith("delete")) {
                return command.deleteCommand();
            } else if (cmd.startsWith("find")) {
                return command.findCommand();
            } else if (cmd.equals("bye")) {
                return command.byeCommand();
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
