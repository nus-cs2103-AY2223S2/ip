package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private final String index;
    public MarkCommand(String index) {
        super("mark");
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.size() == 0) {
            throw new DukeException("You do not have any items in your list!");
        }
        try {
            int idx = Integer.parseInt(this.index);
            tasks.get(idx).setDone();
            ui.print(String.format("Nice! I've marked this task as done: \n\t%s",
                    tasks.get(idx)));
            storage.saveList(tasks);
        } catch (NumberFormatException notANumber) {
            throw new DukeException("Please enter a valid number");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
