package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;

public class Command {
    private String command;

    public Command(String command) {
        this.command = command;
    }

    public boolean isExit() {
        return false;
    }

    public String getCommand() {
        return command;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return;
    }
}
