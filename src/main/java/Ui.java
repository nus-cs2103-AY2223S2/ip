import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printUi(String message){
        System.out.println("\t" + message);
    }

    public void showStartUp() {
        String logo = "  /\\_/\\\n"
                + " /\u25DE   \u25DF\\\n"
                + "( \u25d5   \u25d5 )\n"
                + " \\     /\n"
                + "  \\   /\n"
                + "   \\ /\n"
                + "    \u25CF\n";
        System.out.println(logo + "BorzAI\n");
    }

    public void showWelcome() {
        printUi("When all I do is for you, Kermie \u2665\n\tWhat can I do for you?\n");
    }

    public void showLoadingError() {
        printUi("Error: Unable to load tasks from file.");
    }

    public void showLine() {
        printUi("__________________________________________________________");
    }

    public void showAdded(Task t) {
        printUi("Got it. I've added this task:\n\t  " + t);
    }

    public void showListSize(TaskList tasks) {
        if (tasks.getSize() == 1) {
            printUi("Now you have " + tasks.getSize() + " task in the list.");
        } else {
            printUi("Now you have " + tasks.getSize() + " tasks in the list.");
        }
    }

    public void showList(TaskList tasks) {
        printUi("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.getTask(i);
            int index = i + 1;
            printUi(index + "." + t);
        }
    }

    public void showMarked(Task t) {
        printUi("Nice! I've marked this task as done:\n\t  " + t);
    }

    public void showUnmarked(Task t) {
        printUi("OK, I've marked this task as not done yet:\n\t  " + t);
    }

    public void showDeleted(Task t) {
        printUi("Noted. I've removed this task:\n\t  " + t);
    }

    public void showError(String errorMessage) {
        printUi("\u2639 OOPS!!! " + errorMessage);
    }

    public void showExit() {
        printUi("Woof (\u256F\u11BA\u2570\u0E51)");
    }

    public void closeScanner() {
        scanner.close();
    }
}