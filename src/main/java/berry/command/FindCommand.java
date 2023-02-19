package berry.command;

import berry.storage.Storage;
import berry.task.TaskList;
import berry.ui.Ui;

/**
 * Finds a task with the keywords given.
 */
public class FindCommand extends Command {
    private static String[] listOfKeywords;

    /**
     * Instantiates a new {@code FindCommand} object with the given keywords
     *
     * @param listOfKeywords keywords to search the tasks with.
     */
    public FindCommand(String[] listOfKeywords) {
        assert listOfKeywords != null : "listOfKeywords should not be null";
        this.listOfKeywords = listOfKeywords;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "Tasks should not be null";
        assert ui != null : "Ui should not be null";
        assert storage != null : "Storage should not be null";

        String output = ui.showFind();
        String listOfTasksWithKeyword = tasks.findTaskIndexWithKeyword(listOfKeywords);
        return output + "\t" + listOfTasksWithKeyword;

    }
}
