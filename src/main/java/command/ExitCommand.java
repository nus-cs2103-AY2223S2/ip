package command;

import chatbot.Storage;
import chatbot.Ui;
import task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super();
        this.isExit = true;
        this.isSave = false;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.reply("Alright, goodbye to you too!");
    }
}
