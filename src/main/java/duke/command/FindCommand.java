package duke.command;

import duke.storage.StorageList;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String message;

    public FindCommand(String fullCommand) {
        String[] checker = fullCommand.split("find ");
        this.message = checker[1];
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        System.out.println("Here are the matching tasks in your list:");
        ArrayList<Task> arrList = tasks.find(message);
        for (Task t : arrList) {
            System.out.println(t);
        }

        return true;
    }
}