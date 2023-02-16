package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

/**
 * Manages bye command
 */
public class Exit implements Command {
    public static final String HELP_MSG = "Saves all tasks and exits app.\nFormat: bye";
    private static final String FORMAT = "bye";

    /**
     * @return Parser that can parse the bye command.
     * @see Parser
     */
    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("bye"))
                .<Command>map(s -> new Exit())
                .overrideMsg(FORMAT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage<TaskList> storage) {
        String reply = storage.save(taskList).match(
                ok -> "Tasks saved successfully.\nBye.",
                err -> "Failed to save tasks.\nBye.");
        ui.showReply(reply);
    }
}
