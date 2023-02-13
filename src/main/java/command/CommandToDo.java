package command;

import duke.DukeException;
import duke.Ui;
import storage.Storage;
import task.Task;
import task.TaskList;
import task.ToDo;

/**
 * Command to add todo task.
 */
public class CommandToDo extends Command {

    private final TaskList taskList;
    private final String taskDetails;
    private final Storage storage;


    /**
     * Constructor for CommandToDo.
     *
     * @param taskList List of all tasks.
     * @param taskDetails Details of todo type task to be added.
     */
    public CommandToDo(TaskList taskList, String taskDetails, Storage storage) {
        this.taskList = taskList;
        this.taskDetails = taskDetails;
        this.storage = storage;
    }
    @Override
    public String execute() throws DukeException {
        Task taskAdded = this.addIntoList(this.taskDetails);
        this.updateFile();
        return this.getConfirmationMessageOf(taskAdded);
    }

    private void updateFile() throws DukeException {
        this.storage.overwriteFile(this.taskList);
    }

    private Task addIntoList(String taskDetails) throws DukeException {
        this.checkIfBlank(taskDetails);

        Task newTask = new ToDo(taskDetails.trim());
        this.taskList.addTask(newTask);
        return newTask;
    }

    private void checkIfBlank(String taskDetails) throws DukeException {
        if (taskDetails.isBlank()) {
            throw new DukeException(Ui.getEmptyDetailsForToDoMessage());
        }
    }

    private String getConfirmationMessageOf(Task taskAdded) {
        return Ui.getAddTaskConfirmationWithAttitudeOf(taskAdded);
    }
}
