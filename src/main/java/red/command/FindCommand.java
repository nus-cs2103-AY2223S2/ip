package red.command;

import red.exception.RedException;
import red.storage.Storage;
import red.task.TaskList;
import red.ui.UI;

public class FindCommand extends Command{
    private final String descriptor;

    /**
     * The constructor of FindCommand that takes in the descriptor of the task(s) to be found.
     *
     * @param descriptor The description of the task to be found.
     */
    public FindCommand(String descriptor) {
        this.descriptor = descriptor;
    }

    /**
     * Locates the task(s) that correspond to the descriptor provided.
     *
     * @param tasks The TaskList that contains the current list of tasks.
     * @param ui The UI that interprets any user inputs.
     * @param storage The Storage that keeps track of how the list of tasks changes from user input.
     * @throws RedException Throws Exception when the user inputs invalid instruction.
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) throws RedException {
        ui.addCurrentReply(tasks.find(descriptor));
        System.out.println(tasks.find(descriptor));
    }
}
