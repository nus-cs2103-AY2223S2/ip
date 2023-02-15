package duke.Command;

import duke.Storage;
import duke.TaskList;

public class SearchCommand extends Command {

    private String searchString;

    public SearchCommand(String commandParams) {
        this.searchString = commandParams;
    }

    @Override
    public String executeCommand(Storage storage, TaskList tasks) {
        String result = tasks.searchTaskWithResult(searchString);
        return result;
    }
}
