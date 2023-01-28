package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.GuiText;
import duke.gui.MainWindow;
import duke.gui.SpriteEmotion;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) {
        MainWindow.changeSpriteExpression(SpriteEmotion.HAPPY);
        return guiText.showFind(tasks.findTasksByKeyword(this.keyword));
    }

}
