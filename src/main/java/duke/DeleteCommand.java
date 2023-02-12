package duke;

/**
 * Encapsulates a command to delete a task.
 */
public class DeleteCommand extends Command {
    private Ui ui;
    private TaskList tasks;

    private String[] arguments;

    /**
     * Constructor for creating a DeleteCommand object.
     *
     * @param ui The user interface.
     * @param tasks The current list of tasks.
     * @param arguments Arguments received upon going through the parser.
     * @throws DukeException if user did not specify id of task to be deleted.
     */
    public DeleteCommand(Ui ui, TaskList tasks, String[] arguments) throws DukeException {
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
            tasks.deleteTask(chosen);
            return ui.deleteTaskMessage(chosen, tasks);
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }
}
