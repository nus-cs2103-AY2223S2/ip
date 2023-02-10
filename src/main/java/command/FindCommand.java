package command;

import java.util.ArrayList;

import task.Task;
import task.TaskManager;

import util.DukeException;

public class FindCommand extends Command {
    //private final TaskManager taskManager;
    private final String word;
    public FindCommand(String input) {
        //this.taskManager = taskManager;
        this.word = input;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String executeCommand(TaskManager taskManager) throws DukeException {
        try {
            System.out.println("Here are matching tasks in your list:\n");
            assert taskManager != null;
            ArrayList<Task> arr = taskManager.getTaskArr();
            for (Task task : arr) {
                if (task.getDescription().contains(this.word)) {
                    System.out.println(task);
                }
            }
            return "";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Your list does not contain this task!");
        }
    }
}
