package command;

import interfaces.Command;
import interfaces.Model;
import interfaces.View;
import model.Task;
import model.TaskModel;
import model.ToDo;
import view.TaskView;

import java.util.ArrayList;
import java.util.HashSet;

public class CommandFactory {
    private final TaskModel taskModel;
    private final TaskView taskView;

    public CommandFactory(TaskModel taskModel, TaskView taskView) {
        this.taskModel = taskModel;
        this.taskView = taskView;
    }

    public Command createCommand(String[] tokens) {
        switch (tokens[0]) {
            case "greet":
                return new GreetingCommand(taskView);
            case "bye":
                return new ByeCommand(taskView);
            case "list":
                return new ListTasksCommand(taskModel, taskView);
            case "mark":
                int markIndex = Integer.parseInt(tokens[1]); // handle parseInt error
                return new MarkDoneCommand(taskView, taskModel, markIndex);
            case "unmark":
                int unmarkIndex = Integer.parseInt(tokens[1]); // handle parseInt error
                return new MarkUndoneCommand(taskView, taskModel, unmarkIndex);
            default:
                Task newTask = new ToDo(String.join(" ", tokens));
                return new AddToListCommand(taskView, taskModel, newTask);
        }
    }
}
