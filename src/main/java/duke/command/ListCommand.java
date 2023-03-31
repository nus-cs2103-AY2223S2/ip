package duke.command;

import duke.Storage;
import duke.TaskList;

import java.util.ArrayList;
import java.util.List;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        if (tasks.isEmpty()) {
            this.setOutput("Yatta! Nothing to do yo~");
            return;
        }

        List<String> strings = new ArrayList<>();
        tasks.sort();
        for (int i = 0; i < tasks.getSize(); i++) {
            strings.add((i + 1) + ": " + tasks.get(i));
        }
        this.setOutput(strings.toArray(new String[0]));
    }
}
