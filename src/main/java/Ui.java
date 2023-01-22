import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo =
            " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Ui.prettyPrint("Hello! I'm Duke\nWhat can I do for you?");
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public static void prettyPrint(String text) {
        System.out.println(
            "____________________________________________________________"
        );
        System.out.println(text);
        System.out.println(
            "____________________________________________________________\n"
        );
    }
}
