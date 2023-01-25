package duke.command;

import duke.storage.Storage;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a to-do command that is entered by the user to create a new to-do task.
 */
public class ToDoCommand extends Command {

    /** Task name for the to-do task to be created.*/
    private String taskName;

    /** Task list containing all the tasks. */
    private TaskList tasks;

    /** Storage that allows updating after creating the to-do task. */
    private Storage storage;

    /**
     * Constructs a ToDoCommand
     *
     * @param ui The Ui to allow the command to print messages to the user.
     * @param taskName The name of the to-do task to be created.
     * @param tasks The lists of all available tasks.
     * @param storage The Storage object to allow local saving after adding the to-do task.
     */
    public ToDoCommand(Ui ui, String taskName, TaskList tasks, Storage storage) {
        super(ui);
        this.taskName = taskName;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Creates a to-do task and updates the local data file.
     */
    @Override
    public void runCommand() {
        //Creates task and saves it
        ToDo newToDoTask = new ToDo(taskName);
        tasks.addTask(newToDoTask);
        storage.saveTasks(tasks);

        //Notifies the user
        Ui.printStraightLine();
        ui.printStatement("Added task to list:");
        ui.printStatement(newToDoTask.getStatusOfTaskInString());
        if (tasks.getSizeOfTaskList() == 1) {
            ui.printStatement("\nCurrently, there is 1 task in your list.");
        } else {
            ui.printStatement("\nCurrently, there are " + Integer.toString(tasks.getSizeOfTaskList())
                    + " tasks in your list.");
        }
        Ui.printStraightLine();
    }
}

