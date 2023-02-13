package app.command;

import app.chatbot.Response;
import app.chatbot.Storage;
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
     * @param storage
     */
    @Override
    public Response execute(TaskList tasks, Storage storage) {
        return new Response("Alright, goodbye to you too!", true);
    }
}
