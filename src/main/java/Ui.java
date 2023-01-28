import java.util.Scanner;

public class Ui {

    private static final String LINE = "------------------------------------------------------------";

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public void greet() {
        System.out.println("\nHello from\n" + Ui.LOGO);

        System.out.println("How can I help you?\n");
        System.out.println(Ui.LINE + "\n");

    }

    public String getUserCommand(Scanner commandScanner) {

        System.out.print("You:\n");
        String command = commandScanner.nextLine();

        System.out.println("\nDuke:");

        return command;

    }

    public void endCommand() {
        System.out.println("\n" + Ui.LINE + "\n");
    }


}
