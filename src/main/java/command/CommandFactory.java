package command;

import interfaces.Command;
import interfaces.Model;
import interfaces.View;
import model.Task;
import model.ToDo;

import java.util.ArrayList;
import java.util.HashSet;

public class CommandFactory {
    private final Model taskModel;
    private final View taskView;
    private final HashSet<String> commands;
    public CommandFactory(Model taskModel, View taskView) {
        this.taskModel = taskModel;
        this.taskView = taskView;
        this.commands = new HashSet<>();
        commands.add("greet");
        commands.add("bye");
        commands.add("list");
    }

    public Command createCommand(String[] tokens) {
        switch (tokens[0]) {
            case "greet":
                return new GreetingCommand(taskView);
            case "bye":
                return new ByeCommand(taskView);
            case "list":
                return new ListTasksCommand(taskModel, taskView);
            default:
                Task newTask = new ToDo(String.join(" ", tokens));
                return new AddToListCommand(taskView, taskModel, newTask);
        }
    }
}
