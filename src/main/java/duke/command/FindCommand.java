package duke.command;

import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Task;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String word) {
        this.keyword = word;
    }

    @Override
    public String execute(TaskList list, Ui ui) {
        String output = "";
        output += ui.printFindMessage();
        output += list.find(keyword);
        return output;
    }
}
