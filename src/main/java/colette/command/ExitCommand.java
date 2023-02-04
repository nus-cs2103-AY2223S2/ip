package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.gui.GuiText;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;

/** Class that represents a user command to exit the chatbot */
public class ExitCommand extends Command {

    /**
     * Constructs an ExitCommand object
     * to exit the chatbot.
     */
    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList task, GuiText guiText, Storage storage) {
        MainWindow.changeSpriteExpression(SpriteEmotion.NEUTRAL);
        return guiText.showGoodbye();
    }

}
