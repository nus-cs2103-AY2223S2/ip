package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.Tasklist;

/**

 AddCommand class implements the {@link Command} interface and is used to add a new task to the {@link Tasklist}.
 It takes a {@link Task} object as input in its constructor.
 */
public class AddCommand implements Command {
    private Task newTask;

    /**
     Constructs an AddCommand object and initializes it with the new task to be added to the task list.
     @param newTask the task to be added to the task list
     */
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     Adds the new task to the task list.
     @param ui the user interface object
     @param list the task list object
     @param storage the storage object
     */
    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {
        list.add(this.newTask);
    }

    /**
     Returns false to indicate that this command does not result in an exit from the application.
     @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
