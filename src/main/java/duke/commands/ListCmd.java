package duke.commands;

import duke.tasks.TaskList;

/**
 * Command class for 'list' command keyword.
 * Output to UI all tasks currently in list
 * <p>
 * Command format: "list"
 */
public class ListCmd extends Command {

    /**
     * Constructor method.
     * 
     * @param taskList Task list to output to UI
     * @param lineInput Command line input that the user entered
     */
    public ListCmd(TaskList taskList, String lineInput) {
        super(taskList, lineInput);
    }

    /** Executes UI reply. */
    public String execute() {
        return String.format("Here are the tasks in your list:\n%s", taskList.toString());
    }
}
