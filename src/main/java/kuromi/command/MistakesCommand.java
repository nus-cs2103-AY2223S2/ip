package kuromi.command;

import kuromi.storage.Storage;
import kuromi.task.TaskList;
import kuromi.view.Ui;

/**
 * Lists My Melody's mistakes.
 */
public class MistakesCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.show(getReply());
    }

    private String getReply() {
        String msg = "Kuromi Note\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "My Melody's mistakes:\n";
        msg += "1. Ate the last pickled onion from my lunch tray -_-\n";
        msg += "2. Ripped my lovely notebook :(\n";
        msg += "3. Cuter than me! :((((\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\n";
        msg += "Source: https://hellokitty.fandom.com/wiki/Kuromi#With_Friends_and_Family";
        return msg;
    }
}
