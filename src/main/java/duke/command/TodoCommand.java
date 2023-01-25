package duke.command;

import duke.storage.StorageList;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

public class TodoCommand extends Command {
    private String message;

    public TodoCommand(String fullCommand) {
        String[] checker = fullCommand.split("todo ");
        System.out.println(checker[0]);
        this.message = checker[0];
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
