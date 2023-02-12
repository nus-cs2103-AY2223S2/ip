package duke;

/**
 * Encapsulates a command to create an Event object.
 */
public class EventCommand extends Command {
    private Ui ui;

    private TaskList tasks;

    private String[] arguments;

    /**
     * Constructor for creating a EventCommand object.
     *
     * @param ui The user interface.
     * @param tasks The current list of tasks.
     * @param arguments Arguments received upon going through the parser.
     * @throws DukeException if user does not enter a description for the Event object.
     */
    public EventCommand(Ui ui, TaskList tasks, String[] arguments) throws DukeException {
        if (arguments.length < 2) {
            throw new DukeException("The description of a event cannot be empty!");
        }
        this.ui = ui;
        this.tasks = tasks;
        this.arguments = arguments;
    }

    @Override
    public String execute() {
        try {
            Task chosen = Parser.parseEvent(arguments);
            tasks.addTask(chosen);
            return ui.addedTaskMessage(chosen, tasks);
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }
}
