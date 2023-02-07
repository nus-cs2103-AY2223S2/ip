package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = tasks.findKeywordMatches(keyword);
        Ui.showResponse(response);
        this.responseFromDukeAfterExecution = response;
    }

}
