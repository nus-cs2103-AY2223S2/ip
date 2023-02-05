package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.gui.Ui;

/**
 * DeleteCommand - If user enters the delete command.
 */
public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    /**
     *  Deletes the given task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {

        String message = "Noted. I've removed this task: \n" +
                tasks.get(index) + "\n" +
                ui.showNumberOfListings(tasks.size() - 1);

        tasks.remove(index);
        return message;
    }
}
