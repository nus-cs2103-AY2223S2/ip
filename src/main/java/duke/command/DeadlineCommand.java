package duke.command;

import java.time.LocalDate;

import duke.DukeException;
import duke.task.DeadlineTask;
import duke.task.TaskList;
import duke.util.Ui;

/**
 * A command that creates a DeadlineTask
 * @author Junyi
 */
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
    public String execute() throws DukeException {
        DeadlineTask task = new DeadlineTask(taskDescription, deadlineBy);
        taskList.addTask(task);
        System.out.println(taskList.size());

        return ui.showTaskCreatedMessage(task);
    }
}
