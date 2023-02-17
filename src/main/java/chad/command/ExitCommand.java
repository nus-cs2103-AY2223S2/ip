package chad.command;

import chad.storage.LocalStorage;
import chad.storage.TaskList;
import chad.task.Task;

/**
 * Exit command when user quits the program. New features to be added.
 */
public class ExitCommand extends Command {

    private LocalStorage localStorage;
    private TaskList tasks;

    public ExitCommand(LocalStorage localStorage, TaskList tasks) {
        this.localStorage = localStorage;
        this.tasks = tasks;
    }

    /**
     * Execute the <code>Exit</code> task.
     *
     * @param tasks the list to store new task.
     * @return Response to quit the application.
     */
    @Override
    public String execute(TaskList tasks) {
        localStorage.saveFile(tasks);
        return "Bye";
    }
}
