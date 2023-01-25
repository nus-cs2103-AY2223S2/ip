package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String message;

    public TodoCommand(String fullCommand) {
        String[] checker = fullCommand.split("todo ");
        this.message = checker[1];
    }

    public boolean execute(TaskList tasks, Ui ui, StorageList storage) {
        Todo t = new Todo(message);
        tasks.addToList(t);
        System.out.println("Got it, I've added this task:");
        System.out.println(t);
        tasks.statement();
        return true;
    }
}
