import java.util.Scanner;

public class Ui {
    private static final String line = "____________________________________________________________";
    private Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public String readCommand() {
        return input.nextLine();
    }

    public static void showLine() {
        System.out.println(line);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Kirby!\n" + "What can I do for you? :)");
        showLine();
    }

    public static void showError(String errorMsg) {
        System.out.println(errorMsg);
    }

    public void showExit() {
        showLine();
        System.out.println("Bye bye! Hope to see you again soon!! :>");
        showLine();
    }

    public void showList(TaskList lst) {
        showLine();
        lst.printList();
        showLine();
    }

    public void showMarkedTask(Task task) {
        showLine();
        System.out.println("Okay! I've marked this task as done:");
        System.out.println(task);
        showLine();
    }

    public void showUnmarkedTask(Task task) {
        showLine();
        System.out.println("Okay! I've marked this task as not done yet:");
        System.out.println(task);
        showLine();
    }

    public void showDeletedTask(Task task, int size) {
        showLine();
        System.out.println("Okay! I've removed this task from the list:");
        System.out.println(task);
        System.out.println(size);
        showLine();
    }

    public void showTodo(Todo todo, int size) {
        showLine();
        System.out.println("Got it! I've added: ");
        System.out.println(" " + todo);
        System.out.println(size);
        showLine();
    }

    public void showDeadline(Deadline deadline, int size) {
        showLine();
        System.out.println("Got it! I've added: ");
        System.out.println(" " + deadline);
        System.out.println(size);
        showLine();
    }

    public void showEvent(Event event, int size) {
        showLine();
        System.out.println("Got it! I've added: ");
        System.out.println(" " + event);
        System.out.println(size);
        showLine();
    }
}
