package duke.commands;

import java.util.ArrayList;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.Task;

public class SearchCommand extends Command {
    public static final String COMMAND = "search";
    private String[] tokens;

    public SearchCommand(String[] tokens) {
        this.tokens = tokens;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String searchString = tokens[1];
        assert searchString != "" : "str is supposed to be non-empty";
        ArrayList<Task> results = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(searchString)) {
                results.add(task);
                indices.add(index);
            }
            index++;
        }
        ui.addToResponseMessage("Here are the matching tasks in your list:");
        for (int i = 0; i < results.size(); i++) {
            ui.addToResponseMessage(indices.get(i) + ". " + results.get(i).toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
