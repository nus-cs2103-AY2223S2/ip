package hachi.commands;

import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;

/**
 * Encapsulates a user instruction to exit the program.
 */
public class ExitCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";

    /**
     * ExitCommand constructor.
     *
     * @param input The user's input string.
     */
    public ExitCommand(String input) {
        this.input = input;
    }

    public boolean execute(TaskList toDoList, Ui ui, Storage storage) {
        storage.saveTaskList(toDoList);
        System.out.println(separator + "\n" + "   Ciao ~ see you again soon!");
        return false;
    }

}
