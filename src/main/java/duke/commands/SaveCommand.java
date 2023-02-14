package duke.commands;

import java.io.IOException;

import duke.Duke;
import duke.main.Storage;
import duke.parser.Arguments;

public class SaveCommand extends Command {
    public SaveCommand() {
        super("save");
    }

    @Override
    protected void executeInternal(Arguments args, Duke instance) {
        try {
            Storage.saveToDisk("data.dat", instance.getTaskList());
            output("Saved your tasks to disk!");
        } catch (IOException e) {
            output("Failed to save your data: %s", e.getMessage());
        }
    }
}
