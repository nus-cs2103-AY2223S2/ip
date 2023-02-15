package spongebob.command;

import spongebob.exception.SpongebobEmptyArgumentException;
import spongebob.storage.Storage;
import spongebob.task.TaskList;
import spongebob.ui.Ui;

/**
 * Finds a task by searching for a keyword entered by the user.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor to create a find command.
     *
     * @param fullCommand user input.
     * @throws SpongebobEmptyArgumentException indicate that a command has been passed an e empty argument.
     */
    public FindCommand(String[] fullCommand) throws SpongebobEmptyArgumentException {
        try {
            keyword = fullCommand[1];
            assert keyword != null : "Find command with empty keyword";
        } catch (IndexOutOfBoundsException e) {
            throw new SpongebobEmptyArgumentException("The description of find command cannot be empty.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList taskList = findDataFromList(tasks, keyword);
        return ui.responseToFindTaskCommand(taskList);
    }


    // Finds all matched tasks from the tasklist given a specific keyword.
    private TaskList findDataFromList(TaskList tasks, String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTaskAt(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(tasks.getTaskAt(i));
            }
        }
        return result;
    }
}
