package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.GuiText;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList task, GuiText guiText, Storage storage) {
        return guiText.showGoodbye();
    }

}
