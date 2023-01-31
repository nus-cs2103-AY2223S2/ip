package alfred.command;

import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents a find command object which users can find tasks that contains
 * certain keywords.
 */
public class FindCommand extends Command {

    private String keyWords;

    /**
     * Constructs a find command object that takes in the key-words
     * @param keyWords The key-words that are looking for inside the task list.
     */
    public FindCommand(String keyWords) {
        this.keyWords = keyWords;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.displayCommand(tasks.findTasks(keyWords));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
