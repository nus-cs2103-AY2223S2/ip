package presenter;


import command.CommandFactory;
import interfaces.Presenter;
import interfaces.CommandEventListener;
import interfaces.Command;
import model.TaskModel;
import view.TaskView;


public class TaskPresenter implements Presenter {
    private final InputParser parser;

    public TaskPresenter(TaskModel taskModel, TaskView taskView, CommandEventListener exitEventListener) {
        CommandFactory commandFactory = new CommandFactory(taskModel, taskView);
        this.parser = new InputParser(exitEventListener, commandFactory);
        Command greetCommand = commandFactory.createCommand(CommandFactory.CommandType.GREET);
        greetCommand.execute();
    }

    @Override
    public void handleInput(String input) {
        Command userCommand = parser.parseInput(input);
        if (userCommand != null) {
            userCommand.execute();
        }
    }
}
