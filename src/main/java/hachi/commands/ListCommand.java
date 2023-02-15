package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.main.Storage;

/**
 * Encapsulates a user instruction to display the to-do list.
 */
public class ListCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * ListCommand constructor.
     *
     * @param input The user's input string.
     */
    public ListCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {

        return ui.printList(tasks);

    }
}

