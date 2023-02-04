package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Todo;


/**
 * Encapsulates a user instruction to add a task to the to-do list.
 */
public class TodoCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * TodoCommand constructor.
     *
     * @param input The user's input string.
     */
    public TodoCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            if (input.length() <= 5) {
                throw new HachiExceptions(separator + "\n" + "\n" + "Ohno! The description cannot be empty.");
            }
            Todo tdTask = new Todo(input.substring(5, input.length()));
            toDoList.add(tdTask);
            storage.saveTaskList(toDoList);
            return separator + "\n" + "\n" +  "   okie dokie. I've added this task:" + "\n" + tdTask +
            "   Now you have " + toDoList.size() + " tasks in the list.";
        } catch (HachiExceptions e) {
            return e.getMessage();
        }
    }
}
