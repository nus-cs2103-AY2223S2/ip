package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

public class Exit implements Command {
    public Exit() {

    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.print("Otsumiki!~ I'll see you later!");
    }
}
