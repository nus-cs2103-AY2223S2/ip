package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;

public class FindCommand extends Command {
    private static String[] listOfKeywords;

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