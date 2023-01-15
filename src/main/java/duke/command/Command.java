package duke.command;

import duke.database.DukeRepo;
import duke.exception.DukeException;
import duke.ui.Ui;

public abstract class Command {
    
    public abstract void execute(DukeRepo db, Ui ui) throws DukeException;

    public abstract boolean isExit();
}
