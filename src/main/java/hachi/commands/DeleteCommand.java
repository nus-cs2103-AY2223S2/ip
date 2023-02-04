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
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * DeleteCommand constructor.
     *
     * @param input The user's input string.
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            if (input.length() < 7) {
                throw new HachiExceptions(separator + "\n" + "\n" + "Ohno! I don't know which task to delete :(");
            }
            int index_de = Integer.parseInt(input.substring(7));
            Task task = toDoList.get(index_de - 1);
            toDoList.remove(index_de - 1);
            storage.saveTaskList(toDoList);
            return separator + "\n" + "\n" + "   okie dokie. I've removed this task:\n" + task +
                    "   Now you have " + toDoList.size() + " tasks in the list.";

        } catch (HachiExceptions e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e1) {
            return separator + "\n" + "\n" + "There is no task to be deleted..";
        }
    }
}