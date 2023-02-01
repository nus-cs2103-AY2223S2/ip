package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.GuiText;

public class FindCommand extends Command {

    private String[] keywords;

    public FindCommand(String ... keywords) {
        super(false);
        this.keywords = keywords;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) {
        return guiText.showFind(tasks.findTasksByKeyword(this.keywords));
    }

}
