package duke.command;

import duke.command.exceptions.CommandExecutionError;
import duke.interfaces.Command;
import duke.interfaces.Model;
import duke.interfaces.View;
import duke.model.Deadline;
import duke.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortDeadlinesCommand implements Command {
    private final View taskView;
    private final Model taskModel;
    SortDeadlinesCommand(Model taskModel, View taskView) {
        this.taskView = taskView;
        this.taskModel = taskModel;
    }
    @Override
    public void execute() throws CommandExecutionError {
        List<Task> tasks = taskModel.getTasks();
        List<Deadline> deadlines = new ArrayList<>();
        for (Task task: tasks) {
            if (task instanceof Deadline) {
                deadlines.add((Deadline) task);
            }
        }
        Collections.sort(deadlines);
        taskView.setTasks(new ArrayList<>(deadlines), true);
    }
}
