package duke;
import java.util.Scanner;

public class Ui {
    private static final String WELCOME_MESSAGE = "Hello Boss.\n" + "How may i help you?\n";
    private static final String LINE = "__________________________________________\n";

    private final Scanner SCANNER;

    public Ui() {
        this.SCANNER = new Scanner(System.in);
    }

    public String readCommand() {
        return SCANNER.nextLine();
    }

    public String showWelcome() {
        return (WELCOME_MESSAGE + LINE);
    }

    public String showAdd(Task task) {
        return "Got it. I've added this task:\n" + task.toString() + "\n";
    }

    public String showDelete(Task task) {
        return "Noted. I've removed this task:\n" + task.toString() + "\n";
    }

    public String showFind(TaskList tasks) {
        return "Here are the matching tasks in your list:\n" + tasks + "\n";
    }

    public String showTaskSize(int size) {
        return "Now you have " + size + " tasks in the list. + \n";
    }

    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n" + task.toString() + "\n";
    }

    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n"
        + task.toString() + "\n";
    }

    public String showTaskList(TaskList list) {
        String result = "";
        if (list.isEmpty()) {
            result = "No tasks added.\n";
        } else {
            for (int i = 0; i < list.size(); i++) {
                result += (i + 1) + ":" + list.get(i) + "\n";
            }
        }
        return result;
    }

    public String showError() {
        return "Error has occurred.";
    }

    public String showBye() {
        return "Bye! Have a good day!";
    }
}
