package fea;

import fea.commands.Command;
import fea.exceptions.FeaException;
import fea.parser.Parser;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;

/**
 * Main FEA class that drives the program.
 */
public class Fea {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor method to initialise FEA.
     * @param filePath The path to the data file containing the tasks.
     */
    public Fea(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            ui.logLoading(filePath);
            tasks = new TaskList(storage.loadTasks());
            ui.logSuccessfulLoad(tasks);
        } catch (FeaException e) {
            ui.logLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Gets the response based on user input command.
     *
     * @param inputCommand The user input command.
     * @return String The response message.
     */
    public String getResponse(Command inputCommand) {
        try {
            return inputCommand.execute(tasks, ui, storage);
        } catch (FeaException e) {
            return e.getMessage();
        }
    }

    /**
     * Parses the user input to the Command class.
     *
     * @param input The user input.
     * @return Command The corresponding command according to the user input.
     */
    public Command parseInput(String input) {
        return Parser.parse(input);
    }

    /**
     * Gets the task list.
     * @return TaskList The task list.
     */
    public TaskList getTaskList() {
        return tasks;
    }
}
