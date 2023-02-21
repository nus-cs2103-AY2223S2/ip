package commands;

import baymax.Storage;
import baymax.TaskList;
import baymax.Ui;

import tasks.Task;

import java.util.ArrayList;

public class FindCommand implements Command {

    private String input;

    public FindCommand(String input) {
        this.input = input;
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            if (task.getDescription().contains(input.split(" ", 2)[1])) {
                foundTasks.add(task);
            }
        }
        TaskList newList = new TaskList(foundTasks);
        return ui.printList(newList);
    }
}
