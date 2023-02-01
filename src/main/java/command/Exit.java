package command;

import io.Storage;
import io.Ui;
import task.TaskList;
import util.Parser;

public class Exit implements Command {

    private static final String FORMAT = "bye";

    @Override
    public boolean isExit() {
        return true;
    }

    public static Parser<Command> parser() {
        return Parser.skipSpace()
                .ignoreThen(Parser.strParserIgnoreCase("bye"))
                .<Command>map(s -> new Exit())
                .overrideMsg(FORMAT);
    }

    @Override
    public void execute(TaskList taskList, Storage<TaskList> storage) {
        String reply = storage.save(taskList).match(
                ok -> "Tasks saved successfully.\nBye.",
                err -> "Failed to save tasks.\nBye.");
        Ui.showReply(reply);
    }
}
