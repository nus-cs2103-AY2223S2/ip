package CatBot.Commands;

import CatBot.Storage.Storage;
import CatBot.TaskList.TaskList;
import CatBot.Ui.Ui;

public class EchoCommand extends Command{
    private final String message;

    public EchoCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.setNextOutput(message);
    }
}
