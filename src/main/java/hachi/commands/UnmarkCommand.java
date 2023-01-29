package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Task;

/**
 * Encapsulates a user instruction to mark a task as not done in the to-do list.
 */
public class UnmarkCommand extends Command {
    private String input;

    /**
     * UnmarkCommand constructor.
     *
     * @param input The user's input string.
     */
    public UnmarkCommand(String input) {
        this.input = input;
    }

    public boolean execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            if (input.length() <= 7 || !Character.isDigit(input.charAt(7))) {
                throw new HachiExceptions("☹ Ohno! I don't know which task to mark/unmark :(");
            }
            int index = Integer.parseInt(input.substring(7));
            Task task = toDoList.get(index - 1);
            task.unmark();
            storage.saveTaskList(toDoList);
            System.out.println("   okie dokie, I've marked this task as not done yet: " + "\n" + task);
        } catch (HachiExceptions e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e1) {
            System.out.println(" There is no task to be unmarked");
        }
        return false;
    }
}