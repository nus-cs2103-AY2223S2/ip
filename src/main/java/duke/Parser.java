package duke;

public class Parser {
    private static final Ui ui = new Ui();
    public static String parse(String cmd, TaskList list) throws DukeException {
        Command command = new Command(cmd, list, ui);
        try {
            if (cmd.equals("list")) {
                return command.listCommand(list);
            } else if (cmd.startsWith("mark")) {
                return command.markCommand();
            } else if (cmd.startsWith("unmark")) {
                return command.unmarkCommand();
            } else if (cmd.startsWith("todo")) {
                return command.toDoCommand();
            } else if (cmd.startsWith("deadline")) {
                return command.deadlineCommand();
            } else if (cmd.startsWith("event")) {
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
