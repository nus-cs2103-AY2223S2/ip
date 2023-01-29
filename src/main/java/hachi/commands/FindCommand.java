package hachi.commands;

import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Task;

/**
 * Encapsulates a user instruction to find a task from the to-do list.
 */
public class FindCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";

    /**
     * FindCommand constructor.
     *
     * @param input The user's input string.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    public boolean execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            String keyword = this.input.substring(5, input.length());
            int n = 0;
            System.out.println("   Here are the matching tasks in your list:");
            for (int i = 0; i < toDoList.size(); i++) {
                Task t = toDoList.get(i);
                if (t.toString().contains(keyword)) {
                    n++;
                    System.out.println("   " + n + ". " + t + "\n");
                }
            }
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }
}

