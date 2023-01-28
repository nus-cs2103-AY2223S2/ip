package presenter;


import command.CommandFactory;
import command.exceptions.CommandExecutionError;
import exceptions.CommandException;
import interfaces.Presenter;
import interfaces.CommandEventListener;
import interfaces.Command;
import model.TaskModel;
import presenter.exceptions.ParserError;
import view.TaskView;


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
