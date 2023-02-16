package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < tasks.getSize(); i++) {
            strings.add((i + 1) + ": " + tasks.get(i));
        }
        this.setOutput(strings.toArray(new String[0]));
    }
}
