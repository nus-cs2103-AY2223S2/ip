package duke.command;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.utils.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(DukeRepo db, Ui ui) {
        db.close();
        ui.println(Message.BYE);
    }

    @Override
    public boolean isExit() {
        return true;
    }
    
}
