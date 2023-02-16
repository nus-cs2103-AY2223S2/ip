package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;

public abstract class Command {
     public abstract void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException;

     public boolean checkEnd() {
          return false;
     }
}