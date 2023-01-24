package presenter;


import command.CommandFactory;
import interfaces.*;

import java.util.ArrayList;

public class TaskPresenter implements Presenter {
    private final InputParser parser;

    private final ArrayList<CommandEventListener> listenerList;
    public TaskPresenter(Model taskModel, View taskView) {
        this.listenerList = new ArrayList<>();
        CommandFactory commandFactory = new CommandFactory(taskModel, taskView);
        this.parser = new InputParser(listenerList, commandFactory);
        Command greetCommand = this.parser.parseInput("greet");
        greetCommand.execute();
    }

    @Override
    public void handleInput(String input) {
        Command userCommand = parser.parseInput(input);
        if (userCommand != null) {
            userCommand.execute();
        }
    }

    @Override
    public void registerListener(CommandEventListener listener) {
        this.listenerList.add(listener);
    }
}
