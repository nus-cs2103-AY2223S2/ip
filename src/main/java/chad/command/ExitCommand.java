package chad.command;

import chad.storage.LocalStorage;
import chad.storage.TaskList;

/**
 * Exit command when user quits the program. New features to be added.
 */
public class ExitCommand extends Command {

    private LocalStorage localStorage;

    /**
     * Constructor method to create an exit command to quite the program and save the task list into a file.
     * @param localStorage destination to save the task list.
     */
    public ExitCommand(LocalStorage localStorage) {
        this.localStorage = localStorage;
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
