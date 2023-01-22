package duke.command;

import duke.DukeException;
import duke.task.DeadlineTask;
import duke.task.TaskList;
import duke.util.Ui;

import java.time.LocalDate;

public class DeadlineCommand extends Command {

    private final TaskList taskList;
    private final String taskDescription;
    private final LocalDate deadlineBy;
    private final Ui ui;

    /**
     * Constructor for DeadlineCommand.
     * Creates and insert a DeadlineTask.
     * @param taskList TaskList of Duke's tasks.
     * @param taskDescription Description of task.
     * @param deadlineBy Deadline of task.
     * @param ui Ui instance of Duke.
     */
    public DeadlineCommand(TaskList taskList, String taskDescription, LocalDate deadlineBy, Ui ui) {
        this.taskList = taskList;
        this.taskDescription = taskDescription;
        this.deadlineBy = deadlineBy;
        this.ui = ui;
    }

    @Override
    public boolean execute() throws DukeException {
        DeadlineTask task = new DeadlineTask(taskDescription, deadlineBy);
        taskList.addTask(task);
        ui.showTaskCreatedMessage(task);
        return false;
    }
}
