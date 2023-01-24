import java.util.Scanner;

public class Ui {
    private final String line = "____________________________________________________________";
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
}
