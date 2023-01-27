package command;

import shigure.Ui;
import storage.Storage;
import task.TaskList;

public class ExceptionPrint implements Command {
    private Exception ex;

    public ExceptionPrint(Exception ex) {
        this.ex = ex;
    }

    @Override
    public void run(TaskList tasks, Ui ui, Storage storage) {
        ui.print("?!?!? " + ex.getMessage());
    }
}
