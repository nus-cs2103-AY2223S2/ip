package duke.command;

import duke.DukeException;
import duke.task.EventTask;
import duke.task.TaskList;
import duke.util.Ui;

import java.time.LocalDate;

public class EventCommand extends Command {

    private final TaskList taskList;
    private final String taskDescription;
    private final LocalDate eventFrom;
    private final LocalDate eventBy;
    private final Ui ui;

    /**
     * Constructor for DeadlineCommand.
     * Creates and insert a EventTask.
     * @param taskList TaskList of Duke's tasks.
     * @param taskDescription Description of task.
     * @param eventFrom Start date of task.
     * @param eventBy End date of task.
     * @param ui Ui instance of Duke.
     */
    public EventCommand(TaskList taskList, String taskDescription, LocalDate eventFrom, LocalDate eventBy, Ui ui) {
        this.taskList = taskList;
        this.taskDescription = taskDescription;
        this.eventFrom = eventFrom;
        this.eventBy = eventBy;
        this.ui = ui;
    }

    @Override
    public boolean execute() throws DukeException {
        EventTask task = new EventTask(taskDescription, eventFrom, eventBy);
        taskList.addTask(task);
        ui.showTaskCreatedMessage(task);
        return false;
    }
}
