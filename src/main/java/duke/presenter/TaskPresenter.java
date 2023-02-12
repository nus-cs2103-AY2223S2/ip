package duke.presenter;

import duke.command.CommandFactory;
import duke.command.exceptions.CommandExecutionError;
import duke.exceptions.CommandException;
import duke.interfaces.Command;
import duke.interfaces.CommandEventListener;
import duke.interfaces.Presenter;
import duke.model.TaskModel;
import duke.presenter.exceptions.ParserError;
import duke.view.TaskView;

/**
 * The presenter in Duke's architecture, mediates between the view and model.
 * Handles user input, executes the appropriate command on the model and updates the view.
 * @author jayanth
 */
public class TaskPresenter implements Presenter {
    private final InputParser parser;

    /**
     * Creates the presenter for the application.
     * @param taskModel Model that stores the list of tasks.
     * @param taskView The current view.
     * @param exitEventListener CommandEventListener to listen for the exit command from the user.
     * @throws CommandException If an error occurs while executing the greet command.
     */
    public TaskPresenter(TaskModel taskModel, TaskView taskView, CommandEventListener exitEventListener)
            throws CommandException {
        CommandFactory commandFactory = new CommandFactory(taskModel, taskView);
        this.parser = new InputParser(exitEventListener, commandFactory);
        Command greetCommand = commandFactory.createCommand(CommandFactory.CommandType.GREET);
        greetCommand.execute();
    }

    /**
     * Handle input from the view.
     * @param input the input to handle
     * @throws ParserError If there is an error while parsing the user input.
     * @throws CommandExecutionError If there is an error while executing the command.
     */
    @Override
    public void handleInput(String input) throws ParserError, CommandExecutionError {
        Command userCommand = parser.parseInput(input);
        userCommand.execute();
    }
}
