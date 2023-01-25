import java.util.Scanner;

public class Ui {
    static final String BORDER = "----------------------------------------";
    Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    protected void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "What can I do for you?\n" + BORDER);
    }

    protected void showLine() {
        System.out.println(BORDER);
    }

    protected String readCommand() {
        return sc.nextLine();
    }

    protected void showLoadingError() {
        System.out.println("Some error");
    }
}
