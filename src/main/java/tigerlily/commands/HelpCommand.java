package tigerlily.commands;

import tigerlily.util.Storage;
import tigerlily.util.Ui;

import tigerlily.exceptions.DukeExceptions;
import tigerlily.tasks.TaskList;

public class HelpCommand implements Command {
    private String input;

    public HelpCommand(String input) {
        this.input = input;
    }

    /**
     * Provides a list of possible instructions which users can use in Tigerlily.
     *
     * @param taskList the TaskList to be searched
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used to update the save file
     * @return the Tasks which match the given request
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        return ui.showHelp();
    }
}