package hachi.commands;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;


public class ExitCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";

    public ExitCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList toDoList, Ui ui, Storage storage) {
        storage.saveTaskList(toDoList);
        System.out.println(separator + "\n" + "   Ciao ~ see you again soon!");
    }

}
