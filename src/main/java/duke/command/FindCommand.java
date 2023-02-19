package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Storage storage) {
        tasks.sort();
        List<Task> filtered = tasks.findAllTasksWithKeyword(this.keyword);
        if (filtered.size() == 0){
            this.setOutput("Can't find tasks with this keyword yo~");
            return;
        }

        List<String> strings = new ArrayList<>();
        for (int i = 0; i < filtered.size(); i++) {
            strings.add((i + 1) + ": " + filtered.get(i));
        }
        this.setOutput(strings.toArray(new String[0]));
    }
}
