package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * The type Find command.
 */
public class FindCommand extends Command {

    private String searchWord;

    /**
     * Instantiates a new Find command.
     *
     * @param searchWord the search word
     */
    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (searchWord.isEmpty()) {
            throw new DukeException("What's your search word??");
        }

        //returns if search result if successful or failure message
        return tasks.find(searchWord);
    }
}
