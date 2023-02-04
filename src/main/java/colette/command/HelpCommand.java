package colette.command;

import colette.Storage;
import colette.TaskList;
import colette.gui.GuiText;
import colette.gui.HelpWindow;
import colette.gui.MainWindow;
import colette.gui.SpriteEmotion;

public class HelpCommand extends Command {
    
    public HelpCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList tasks, GuiText guiText, Storage storage) {
        boolean isHelpWindowOpen = HelpWindow.isOpen();
        SpriteEmotion spriteEmotion = !isHelpWindowOpen ? SpriteEmotion.HAPPY : SpriteEmotion.SURPRISED;
        MainWindow.changeSpriteExpression(spriteEmotion);
        HelpWindow.launchHelpWindow();
        return GuiText.showHelp(isHelpWindowOpen);
    }

}
