package duke;
import java.util.Scanner;
import duke.task.Task;

public class Ui {
    private Scanner scanner;

    public Ui () {
        this.scanner = new Scanner(System.in);
    }


    public String readCommand() {
        System.out.println("Input Command:");
        return scanner.nextLine();
    }

    public void showTasks(TaskList tasks) {
        this.showSepLine();
        System.out.println(tasks);
        this.showSepLine();
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        this.showSepLine();
        System.out.println("Hello! I'm Duke, what can I do for you?");
        this.showSepLine();

        this.showHelp();
    }

    public void showHelp() {
        this.showSepLine();
        System.out.println("Commands:");
        System.out.println("Adding tasks: todo, event (requires date), deadline (requires date)");
        System.out.println("Marking completion: mark (index), unmark (index)");
        System.out.println("Removing tasks: remove (index)");
        System.out.println("List all tasks: list");
        System.out.println("Exit Duke: exit");
        this.showSepLine();
    }

    public void showGoodbye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public void showSepLine() {
        System.out.println("-----------------------------------------");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showLoadingError(String message) {
        showSepLine();
        System.out.println(message);
        showSepLine();
    }

    public void showAddTask(Task task, TaskList tasks) {
        System.out.println("Added: " + task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void showDeleteTask(Task task, TaskList tasks) {
        System.out.println("Deleted: " + task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list.");
    }

    public void showMarkTask(Task task) {
        System.out.println("I've marked this task as done: " + task);
    }

    public void showUnmarkTask(Task task) {
        System.out.println("I've unmarked this task as done: " + task);
    }

    public void showFoundTasks(TaskList keywordTasks) {
        this.showSepLine();
        System.out.println("Here are the matching tasks in your list:");
        System.out.println(keywordTasks);
        this.showSepLine();
    }
}
