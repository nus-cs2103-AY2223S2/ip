package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (input.length() <= 7) {
                throw new DukeException("    OOPS!!! Delete must be followed by an int.");
            }
            int index = Integer.parseInt(input.substring(7));
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            storage.saveTaskList(tasks);
            System.out.println("    Noted. I've removed this task:");
            System.out.println("      " + task);
            System.out.println("    Now you have " + tasks.size() + " tasks in the list.");
            return true;
        } catch (DukeException de) {
            System.out.println(de.getMessage());
            return false;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    OOPS!!! Insufficient items in the list to be deleted.");
            return false;
        }
    }
}
