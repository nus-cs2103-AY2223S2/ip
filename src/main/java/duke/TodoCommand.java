package duke;

/**
 * Encapsulates a command to create a Todo object.
 */
public class TodoCommand extends Command {
    private Ui ui;

    private TaskList tasks;

    private String[] arguments;

    /**
     * Constructor for creating a TodoCommand object.
     *
     * @param ui The user interface.
     * @param tasks The current list of tasks.
     * @param arguments Arguments received upon going through the parser.
     */
    public TodoCommand(Ui ui, TaskList tasks, String[] arguments) {
        this.ui = ui;
        this.tasks = tasks;
        this.arguments = arguments;
    }

    @Override
    public String execute() {
        try {
            Task chosen = Parser.parseTodo(arguments);
            tasks.addTask(chosen);
            return ui.addedTaskMessage(chosen, tasks);
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }
}
