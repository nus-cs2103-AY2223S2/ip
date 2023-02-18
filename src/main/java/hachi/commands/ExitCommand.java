package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;

/**
 * Encapsulates a user instruction to exit the program.
 */
public class ExitCommand extends Command {
    private String input;

    /**
     * ExitCommand constructor.
     *
     * @param input The user's input string.
     */
    public ExitCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            storage.saveTaskList(tasks);
            return ui.showExitMessage();
        } catch (HachiExceptions e) {
            return ui.showSavingError();
        }
    }
}
