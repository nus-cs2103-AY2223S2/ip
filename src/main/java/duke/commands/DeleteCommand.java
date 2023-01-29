package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {

    private int deleteNumber;

    public DeleteCommand(int deleteNumber) {
        this.deleteNumber = deleteNumber;
    }

    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        if (deleteNumber < 1 || deleteNumber > list.size()) {
            throw new DukeException("Sorry, this task number is invalid.");
        }
        Task removed = list.get(this.deleteNumber - 1);
        list.remove(this.deleteNumber - 1);
        store.save(list);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(removed.toString());
        ui.showMessage("Now you have " + list.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
