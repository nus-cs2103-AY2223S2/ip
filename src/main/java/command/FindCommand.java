package command;

import java.util.ArrayList;

import task.Task;
import task.TaskManager;

import util.DukeException;

public class FindCommand extends Command {
    private final TaskManager taskManager;
    private final String word;
    public FindCommand(TaskManager taskManager, String input) {
        this.taskManager = taskManager;
        this.word = input;
    }

    @Override
    public void executeCommand() throws DukeException {
        try {
            System.out.println("Here are matching tasks in your list:\n");
            ArrayList<Task> arr = taskManager.getTaskArr();
            for (Task task : arr) {
                if (task.getDescription().contains(this.word)) {
                    System.out.println(task);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Your list does not contain this task!");
        }
    }
}
