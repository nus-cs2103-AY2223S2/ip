package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.GuiText;

public class ListCommand extends Command {

    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) {
        return guiText.showList(tasks);
    }

}
