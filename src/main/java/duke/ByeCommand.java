package duke;

/**
 * Encapsulates a command to exit the program.
 */
public class ByeCommand extends Command {

    private Ui ui;

    private TaskList tasks;

    private Storage storage;

    /**
     * Constructor for creating a ByeCommand object.
     *
     * @param ui The user interface.
     * @param tasks The current list of tasks.
     * @param storage Object responsible for storing the tasks into txt file.
     * @param arguments Arguments received upon going through the parser.
     * @throws DukeException if user enters bye with extra characters.
     */
    public ByeCommand(Ui ui, TaskList tasks, Storage storage, String[] arguments) throws DukeException {
        if (arguments.length != 1) {
            throw new DukeException("Invalid number of arguments.Only require 1!");
        }
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public String execute() {
        try {
            storage.saveToFile(tasks);
            return ui.showGoodbyeMessage();
        } catch (DukeException e) {
            return ui.showErrorMessage(e);
        }
    }
}
