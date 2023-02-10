package duke;
import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Hello Boss.\n" + "How may i help you?\n";
    private static final String LINE = "__________________________________________\n";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE + LINE);
    }

    public void showAdd(Task task) {
        System.out.println("Got it. I've added this task:\n" + task.toString());
    }

    public void showDelete(Task task) {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
    }

    public void showTaskSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    public void showMarked(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task.toString() + "\n");
    }

    public void showUnmarked(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n");
    }

    public void showTaskList(TaskList list) {
        if (list.isEmpty()) {
            System.out.println("No tasks added.\n");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 + ":" + list.get(i) + "\n");
            }
        }
    }

    public void showError() {
        System.out.println("Error has occurred.");
    }

    public void showBye() {
        System.out.println("Bye! Have a good day!");
    }
}
