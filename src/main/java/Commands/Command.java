package Commands;

import java.io.IOException;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    public abstract boolean isContinueConvo();
}