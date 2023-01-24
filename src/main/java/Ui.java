import java.util.Scanner;

public class Ui {
    private final String line = "_______________________________________________________________________";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showLoadingError() {}

    public Scanner getScanner() {
        return this.scanner;
    }

    public void showWelcome() {
        this.showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        this.showLine();
    }

    public void showLine() {
        System.out.println(this.line);
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    public void showDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    public void showMark(boolean isDone, Task taskToMark) {
        if(isDone) {
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(taskToMark);
    }

    public void showTaskOutput(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + size + " tasks in the list.");
    }
}
