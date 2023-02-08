package berry.command;

import berry.task.TaskList;
import berry.ui.Ui;
import berry.storage.Storage;
import berry.exception.BerryException;

public class FindCommand extends Command {
    private static String[] listOfKeywords;

    public FindCommand(String[] listOfKeywords) {
        this.listOfKeywords = listOfKeywords;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String output = ui.showFind();
        String listOfTasksWithKeyword = tasks.findTaskIndexWithKeyword(listOfKeywords);
        return output + listOfTasksWithKeyword;

    }
}