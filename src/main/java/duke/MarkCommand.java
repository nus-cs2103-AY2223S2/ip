package duke;

/**
 * Encapsulates a command to mark a task as done.
 */
public class MarkCommand extends Command {

    private Ui ui;
    private TaskList tasks;

    private String[] arguments;

    /**
     * Constructor for creating a MarkCommand object.
     *
     * @param ui The user interface.
     * @param tasks The current list of tasks.
     * @param arguments Arguments received upon going through the parser.
     * @throws DukeException if user did not specify id of task to be marked.
     */
    public MarkCommand(Ui ui, TaskList tasks, String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("Task number missing!");
        }
        this.ui = ui;
        this.tasks = tasks;
        this.arguments = arguments;
    }
    @Override
    public String execute() {
        try {
            int id = Parser.parseTask(arguments);
            Task chosen = tasks.getTask(id);
            chosen.mark();
            return ui.markTaskMessage(chosen);
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }
}
