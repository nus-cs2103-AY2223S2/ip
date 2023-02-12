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

public class TaskPresenter implements Presenter {
    private final InputParser parser;

    public TaskPresenter(TaskModel taskModel, TaskView taskView, CommandEventListener exitEventListener)
            throws CommandException {
        CommandFactory commandFactory = new CommandFactory(taskModel, taskView);
        this.parser = new InputParser(exitEventListener, commandFactory);
        Command greetCommand = commandFactory.createCommand(CommandFactory.CommandType.GREET);
        greetCommand.execute();
    }

    @Override
    public void handleInput(String input) throws ParserError, CommandExecutionError {
        Command userCommand = parser.parseInput(input);
        userCommand.execute();
    }
}
