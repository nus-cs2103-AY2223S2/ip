import java.util.Scanner;

public class Ui {

    private final Scanner sc;

    public Ui() {
        greet();
        sc = new Scanner(System.in);

    }

    public boolean hasUserInput() {
        return sc.hasNextLine();
    }

    public String userInput() {
        return sc.nextLine();
    }

    public void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo + "Hello! I'm Duke\nWhat can I do for you?");
        separator();
    }

    public void exit() {
        separator();
        System.out.println("\tBye. Hope to see you again soon!");
        separator();
        System.exit(0);
    }

    public void showError(DukeException err) {
        separator();
        System.out.println("\t" + err);
        separator();
    }

    public static void separator() {
        System.out.println("---------------------------------------------------------------");
    }
}
