package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.exceptions.DukeException;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.addTask(this.task);
        setOutput("Okay~ I've added the task for you~",
                  this.task.toString(),
                  "Now you have " + tasks.getSize() + " tasks!");

        try {
            storage.saveTasklistToFile(tasks);
        } catch (DukeException e) {
            setOutput(getOutput(), e.getMessage());
        }
    }
}
