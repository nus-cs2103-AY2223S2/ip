package jeno.command;

import jeno.storage.Note;
import jeno.storage.TaskList;

/**
 * Class for ListCommand.
 */
public class ListCommand extends Command {

    /**
     * Constructor for ListCommand.
     * @param userInput
     */
    public ListCommand(String userInput) {
        super(userInput);
    }

    /**
     * List all tasks in a TaskList.
     * @param tasks TaskList.
     * @return List of all tasks in String format.
     */
    public String listTasks(TaskList tasks) {
        String returnMessage = "";
        int taskNumber = 1;
        for (int i = 0; i < tasks.getSize(); i++) {
            String foundTask = taskNumber + ". " + tasks.getTask(i).toString() + "\n";
            returnMessage += foundTask;
            taskNumber++;
        }
        return returnMessage;
    }

    /**
     * Executes user input and lists all tasks in current TaskList.
     * @param tasks Current TaskList.
     * @param notes Current Note.
     * @return List of all tasks in String format.
     */
    @Override
    public String execute(TaskList tasks, Note notes) {
        if (tasks.getSize() == 0) {
            return "You currently do not have any active tasks.";
        } else {
            return "Here is the list of active tasks:\n"
                    + listTasks(tasks);
        }
    }
}
