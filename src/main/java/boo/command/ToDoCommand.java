package boo.command;

import boo.storage.Storage;
import boo.task.ToDo;
import boo.tasklist.TaskList;

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
     * Constructs a {@code ToDoCommand}.
     *
     * @param taskName The name of the to-do task to be created.
     * @param tasks The {@code TaskList} of all available tasks.
     * @param storage The {@code Storage} object to allow local saving after adding the to-do task.
     */
    public ToDoCommand(String taskName, TaskList tasks, Storage storage) {
        super();
        this.taskName = taskName;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Creates a to-do task and updates the local data file.
     *
     * @return a string stating the to-do task that has been created.
     */
    @Override
    public String runCommand() {
        //Creates task and saves it
        ToDo newToDoTask = new ToDo(taskName);
        tasks.addTask(newToDoTask);
        storage.saveTasks(tasks);

        //Notifies the user
        StringBuilder sb = new StringBuilder();
        sb.append("Added task to list:\n");
        sb.append(newToDoTask.getStatusOfTaskInString());
        sb.append("\n");

        if (tasks.getSizeOfTaskList() == 1) {
            sb.append("\nCurrently, there is 1 task in your list.");
        } else {
            sb.append("\nCurrently, there are " + tasks.getSizeOfTaskList() + " tasks in your list.");
        }
        return sb.toString();
    }
}

