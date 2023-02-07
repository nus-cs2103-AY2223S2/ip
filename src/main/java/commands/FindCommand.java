package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        super(CommandType.FIND);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        TaskList results = list.search(keyword);
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n\t ");
        sb.append(results.toString());
        ui.printOutput(sb.toString());
    }
}
