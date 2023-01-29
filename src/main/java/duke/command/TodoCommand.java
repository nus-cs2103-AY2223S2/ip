package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;


/**
 * Class of TodoCommand that add task to the list.
 */
public class TodoCommand extends Command {
    private String activity;

    public TodoCommand(String cmd) {
        System.out.println(cmd);
        String c = cmd.split(" ")[0];
        this.activity = cmd.substring(c.length() + 1);
    }

    public boolean execute(Storage tl, Ui ui, Storage storage) {
        Task t = new Todo(this.activity);
        tl.addTask(t);
        System.out.println("Got it. I've added this duke.task:\n" + t
                + "\n Now you have " + tl.getSize() + " tasks in the list.");
        return true;
    }

}
