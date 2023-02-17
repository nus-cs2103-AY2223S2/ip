package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskAssigner;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * AddCommand creates a new task based on the user input.
 * Adds the new task into our existing task list.
 */
public class AddCommand extends Command {

    /**
     * Returns either a Deadline, Event or ToDo task depending on user input.
     */
    protected TaskAssigner taskAssigner;

    /**
     * Creates an AddCommand.
     *
     * @param textCmd user input.
     */
    public AddCommand(String textCmd) {
        super(textCmd);
        this.taskAssigner = new TaskAssigner();
    }

    /**
     * Creates a task based on the user input and stores it.
     *
     * @param taskList task list that stores all tasks.
     * @param ui User interface for duke.
     * @param storage Storage information for task.
     * @return String output to be displayed by ChatBot.
     * @throws DukeException
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        Task addedTask = taskAssigner.assignTask(textCmd);
        taskList.add(addedTask);
        return ui.printAddTask(addedTask, taskList.getNumTasks());
    }
}
