package app.command;

import app.chatbot.Storage;
import app.chatbot.Ui;
import app.task.TaskList;

/**
 * Allows for the app to exit. This is the only class with isExit = true.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
        this.isExit = true;
        this.isSave = false;
    }

    /**
     * Prints the exit message. Duke.run() recognises the exit status and closes the app.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.reply("Alright, goodbye to you too!");
    }
}
