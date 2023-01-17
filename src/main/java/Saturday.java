import java.util.ArrayList;

public class Saturday {
    private TaskList<Task> taskList = new TaskList<>();

    public Saturday() {
        Utils.divider();
        System.out.println("\t Hello! I'm Saturday\n\t What can I do for you?");
        Utils.divider();
        System.out.println("");
    }

    public void output(String text) {
        Utils.divider();
        System.out.println("\t" + text);
        Utils.divider();
        System.out.println("");
    }

    // Input method to process userInputs
    public void input(String text) {
        if (text.equals("list")) {
            displayList();
        } else {
            add(text);
        }
    }

    public void add(String text) {
        Task task = new Task(text);
        taskList.addTask(task);
        output("added: " + text);
    }

    public void displayList() {
        output(taskList.toString());
    }

    public void exit() {
        output("Bye. Hope to see you again soon!");
    }

}
