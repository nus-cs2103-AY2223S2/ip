package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Task;

/**
 * Encapsulates a user instruction to delete a saved task from the list.
 */
public class DeleteCommand extends Command {
    private String input;

    /**
     * DeleteCommand constructor.
     *
     * @param input The user's input string.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.length() < 7) {
                throw new HachiExceptions(ui.invalidIndexMessage());
            }
            int index_de = Integer.parseInt(input.substring(7));
            Task task = tasks.get(index_de - 1);
            tasks.remove(index_de - 1);
            return ui.showDeleted(tasks, task);
        } catch (HachiExceptions e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e1) {
            return ui.noTasksMessage();
        }
    }
}