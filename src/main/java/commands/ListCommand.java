package commands;
import main.TaskList;
import main.Ui;
import main.Storage;

public class ListCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿︵‿︵︵‿︵‿୨♡୧‿";

    public ListCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList toDoList, Ui ui, Storage storage) {
        if (toDoList.size() > 0) {
            System.out.println(separator + "\n" + "   Here are the tasks in your list:");
            for (int i = 0; i < toDoList.size(); i++) {
                int num = i + 1;
                System.out.print("    " + num + ". " + toDoList.get(i) + "\n");
            }
        } else {
            System.out.print(separator + "\n" + "   You don't have any tasks at the moment" + "\n");
        }
    }
}
