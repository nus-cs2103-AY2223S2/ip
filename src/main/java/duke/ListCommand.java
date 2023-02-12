package duke;

/**
 * Encapsulates a command to list all tasks in the TaskList currently.
 */
public class ListCommand extends Command {
    private Ui ui;
    private TaskList tasks;

    /**
     * Constructor for creating a ListCommand object.
     *
     * @param ui The user interface.
     * @param tasks The current list of tasks.
     * @param arguments Arguments received upon going through the parser.
     * @throws DukeException if user list with extra characters.
     */
    public ListCommand(Ui ui, TaskList tasks, String[] arguments) throws DukeException {
        if (arguments.length != 1) {
            throw new DukeException("Invalid number of arguments.Only require 1!");
        }
        this.ui = ui;
        this.tasks = tasks;
    }
    @Override
    public String execute() {
        return ui.showTasksMessage(tasks);
    }
}
