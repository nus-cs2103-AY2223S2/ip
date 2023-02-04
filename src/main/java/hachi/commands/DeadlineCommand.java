package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.main.Storage;
import hachi.tasks.Deadline;

import java.time.format.DateTimeParseException;

/**
 * Encapsulates a user instruction to add a task with deadline to the list
 */
public class DeadlineCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * DeadlineCommand constructor.
     *
     * @param input The user's input string.
     */
    public DeadlineCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            int index_ddl = input.indexOf("/");
            if (input.length() > 9 && !input.contains("/")) {
                throw new HachiExceptions(separator + "\n" + "\n" + "Ohno! seems like you forgot to put a deadline.");
            }
            if (index_ddl - 1 < 9) {
                throw new HachiExceptions(separator + "\n" + "\n" + "Ohno! The description cannot be empty.");
            }
            Deadline ddlTask = new Deadline(input.substring(9, index_ddl - 1), input.substring(index_ddl + 4, input.length()));
            toDoList.add(ddlTask);
            storage.saveTaskList(toDoList);
            return separator + "\n" + "\n" + " okie dokie. I've added this task:" + "\n" + ddlTask +
                    "   Now you have " + toDoList.size() + " tasks in the list.";
        } catch (HachiExceptions e) {
            return e.getMessage();
        } catch (DateTimeParseException e1) {
            return separator + "\n" + "\n" + "Key in deadline in the format of yyyy-mm-dd";
        }
    }
}

