package duke.commands;

import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exceptions.DukeException;
import duke.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command{
    private String keyword;

    public FindCommand(ArrayList<String> tokens) throws DukeException {
        super(tokens);
        if (tokens.size() == 1) {
            throw new DukeException(
                    "Invalid input received! Please include a keyword to find!\n"
                    + "Find commands are in the form of: find keyword "
            );
        }
        keyword = String.join(" ", tokens.subList(1, tokens.size()));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> out = new ArrayList<>();
        for (int i = 1; i<=tasks.size(); i++) {
            if (tasks.getTask(i).containsString(keyword)) {
                out.add(tasks.getTask(i));
            }
        }
        ui.showFindResults(out);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
