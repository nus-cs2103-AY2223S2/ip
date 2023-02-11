package duke.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.UserInterface;

public class Find extends Command {
    private List<String> keywords;

    public Find(String keywords) {
        this.keywords = Arrays.asList(keywords.split(" "));

        for (int i = 0; i < this.keywords.size(); i++) {
            this.keywords.set(i, this.keywords.get(i).toLowerCase());
        }
    }

    @Override
    public void execute(TaskList list, UserInterface ui, Storage storage) throws Exception {
        ArrayList<Task> found = new ArrayList<>();
        for (Task t : list) {
            if (keywords.stream().anyMatch(k -> t.description().toLowerCase().contains(k))) {
                found.add(t);
            }
        }
        TaskList foundList = new TaskList(found);
        ui.showTasks(foundList);

    }

}
