package duke;
import java.util.ArrayList;

public class Parser {
    private static final Ui ui = new Ui();
    public static void parse(String cmd, TaskList list) throws DukeException {
        Command command = new Command(cmd, list, ui);
        try {
            if (cmd.equals("list")) {
                command.listCommand(list);
            } else if (cmd.startsWith("mark")) {
                command.markCommand();
            } else if (cmd.startsWith("unmark")) {
                command.unmarkCommand();
            } else if (cmd.startsWith("todo")) {
                command.toDoCommand();
            } else if (cmd.startsWith("deadline")) {
                command.deadlineCommand();
            } else if (cmd.startsWith("event")) {
                command.eventCommand();
            } else if (cmd.startsWith("delete")) {
                command.deleteCommand();
            } else if (cmd.startsWith("find")) {
                command.findCommand();
            } else {
                throw new UnknownCommandException();
            }
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
