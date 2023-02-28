package duke.commands;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Find is a subclass of Command that takes in a keyword and returns a string of all the tasks that
 * contain that keyword
 */
public class Find extends Command {
    protected String keyword;
    public Find(String input) {
        this.keyword = input;
    }
    /**
     * The function takes in a tasklist, a ui and a storage, and returns a string of all the tasks that
     * contain the keyword
     * @param tasks the list of tasks
     * @param ui a reference to the UI object
     * @param storage This is the storage object that you will use to access the file system.
     * @return The matching tasks in the task list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        StringBuilder matchingTasks = new StringBuilder("Here are the matching tasks in your list:\n");
        int counter = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task currenttask = tasks.getTask(i);
            String taskdescription = currenttask.getDescription();
            if (taskdescription.contains(keyword)) {
                counter++;
                matchingTasks.append(counter).append(". ").append(taskdescription).append("\n");
            }
        }
        try {
            assert counter > 0 : ui.printNoTasksError();
        } catch (AssertionError a) {
            return a.getMessage();
        }
        return matchingTasks.toString();
    }
}
