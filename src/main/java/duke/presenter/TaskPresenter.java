package duke.presenter;

import duke.command.CommandFactory;
import duke.command.exceptions.CommandExecutionError;
import duke.exceptions.CommandException;
import duke.interfaces.*;
import duke.presenter.exceptions.ParserError;

/**
 * The presenter in Duke's architecture, mediates between the view and model.
 * Handles user input, executes the appropriate command on the model and updates the view.
 * @author jayanth
 */
public class TaskPresenter implements Presenter {
    private final InputParser parser;
    private final CommandFactory commandFactory;
    /**
     * Creates the presenter for the application.
     * @param taskModel Model that stores the list of tasks.
     * @param taskView The current view.
     * @param exitEventListener CommandEventListener to listen for the exit command from the user.
     */
    public TaskPresenter(Model taskModel, View taskView, CommandEventListener exitEventListener) {
        this.commandFactory = new CommandFactory(taskModel, taskView, exitEventListener);
        this.parser = new InputParser(commandFactory);
    }

    public void greetUser() throws CommandException {
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
