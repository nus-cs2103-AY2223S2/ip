package hachi.commands;

import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.main.Storage;

/**
 * Encapsulates a user instruction to display the to-do list.
 */
public class ListCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";

    /**
     * ListCommand constructor.
     *
     * @param input The user's input string.
     */
    public ListCommand(String input) {
        this.input = input;
    }

    public boolean execute(TaskList toDoList, Ui ui, Storage storage) {
        if (toDoList.size() > 0) {
            System.out.println(separator + "\n" + "   Here are the tasks in your list:");
            for (int i = 0; i < toDoList.size(); i++) {
                int num = i + 1;
                System.out.print("    " + num + ". " + toDoList.get(i) + "\n");
            }
        } else {
            System.out.print(separator + "\n" + "   You don't have any tasks at the moment" + "\n");
        }
        return false;
    }
}
