package duke.command;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.ui.Ui;

public class ExitCommand extends Command {

    /**
     * Closes the database and say farewell.
     * 
     * @see Command#execute(DukeRepo, Ui)
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {
        db.close();
        ui.println(Message.BYE);
    }

    /**
     * @see Command#isExit()
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
