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

    public String execute(TaskList tasks, Ui ui, StorageList storage) {
        ArrayList<Task> arrList = tasks.find(message);
        String output = "";
        for (Task t : arrList) {
            output+=t+"\n";
        }

        return "Here are the matching tasks in your list: " + output ;
    }
}