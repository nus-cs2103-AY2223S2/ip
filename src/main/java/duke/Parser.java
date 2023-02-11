package duke;

/**
 * deals with understanding the instructions given to Duke
 */
public class Parser {
    /**
     * Interprets the user input and executes the corresponding actions
     * 
     * @param command input given by the user
     * @param tasks   the tasklist to be changed according to the user input
     * @throws DukeException invalid input
     */
    public static String parse(String command, TaskList tasks) throws DukeException {
        Ui ui = new Ui();
        if (command.equals("bye")) {
            return ui.exit();
        } else if (command.equals("list")) {
            return ui.listCommandMessage() + tasks.toString();
        } else if (command.startsWith("mark")) {
            return tasks.markTask(command);
        } else if (command.startsWith("unmark")) {
            return tasks.unmarkTask(command);
        } else if (command.startsWith("todo")) {
            return tasks.addTodo(command);
        } else if (command.startsWith("deadline")) {
            return tasks.addDeadline(command);
        } else if (command.startsWith("event")) {
            return tasks.addEvent(command);
        } else if (command.startsWith("delete")) {
            return tasks.deleteTask(command);
        } else if (command.startsWith("find")) {
            return tasks.findTask(command);
        } else {
            throw new UnknownCommandException();
        }
    }
}
