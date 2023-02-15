package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Task;

/**
 * Encapsulates a user instruction to mark a task as done in the to-do list.
 */
public class MarkCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * MarkCommand constructor.
     *
     * @param input The user's input string.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.length() <= 5 || !Character.isDigit(input.charAt(5))) {
                throw new HachiExceptions(ui.invalidIndexMessage());
            }
            int index = Integer.parseInt(input.substring(5));
            Task task = tasks.get(index - 1);
            task.mark();
            return separator + "\n" + "\n" + "   good job! I've marked this task as done: " + "\n" + task;
        } catch (HachiExceptions e) {
            return e.getMessage();

        } catch (IndexOutOfBoundsException e1) {
            return ui.invalidIndexMessage();

        }
    }
}
