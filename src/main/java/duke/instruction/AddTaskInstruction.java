package duke.instruction;

import duke.exception.GeneralDukeException;
import duke.task.GeneralDukeTask;
import duke.task.TaskList;

public abstract class AddTaskInstruction extends GeneralDukeInstruction {
    private final GeneralDukeTask task;

    public AddTaskInstruction(GeneralDukeTask task) {
        this.task = task;
    }

    @Override
    public void run(TaskList list) throws GeneralDukeException {
        list.addTask(task);
        String message = "Got it. I've added this task:\n " + task
                + "\nNow you have " + list.remainingTasks() + " tasks in the list.";
        format.displayWithBar(message);
    }
}
