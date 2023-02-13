package command;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Ui;
import task.Event;
import task.Task;
import task.TaskList;

/**
 * Command to add event task.
 */
public class CommandEvent extends Command {

    private final TaskList taskList;
    private final String taskDetails;

    /**
     * Constructor for CommandEvent.
     *
     * @param taskList List of all tasks.
     * @param taskDetails Details of event type task to be added.
     */
    public CommandEvent(TaskList taskList, String taskDetails) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
    }

    @Override
    public String execute() throws DukeException {
        Task taskAdded = this.addIntoList(this.taskDetails);
        return this.getConfirmationMessageOf(taskAdded);
    }

    private Task addIntoList(String taskDetails) throws DukeException {
        this.checkIfBlank(taskDetails);

        try {
            String[] s = taskDetails.split("/from");
            String[] ss = s[1].split("/to");

            String taskName = s[0].trim();
            String taskStartDate = ss[0].trim();
            String taskEndDate = ss[1].trim();

            Task newTask = new Event(taskName, taskStartDate, taskEndDate);
            this.taskList.addTask(newTask);
            return newTask;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Ui.eventTaskFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException(Ui.supportedDateFormat);
        }
    }

    private void checkIfBlank(String taskDetails) throws DukeException {
        if (taskDetails.isBlank()) {
            throw new DukeException(Ui.emptyDetailsForEventMessage);
        }
    }

    private String getConfirmationMessageOf(Task taskAdded) {
        return Ui.getAddTaskConfirmationWithAttitudeOf(taskAdded);
    }


}
