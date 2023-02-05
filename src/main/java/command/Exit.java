package command;

import io.Storage;
import io.Ui;
import parsing.Parser;
import task.TaskList;

public class Exit implements Command {
    private static final String FORMAT = "bye";

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

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
    public void execute(TaskList taskList, Storage<TaskList> storage) {
        String reply = storage.save(taskList).match(
                ok -> "Tasks saved successfully.\nBye.",
                err -> "Failed to save tasks.\nBye.");
        Ui.showReply(reply);
    }
}
