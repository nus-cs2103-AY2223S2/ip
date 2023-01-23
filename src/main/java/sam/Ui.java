package sam;

import java.util.Scanner;

public class Ui {
    private static final String LOGO =
              "\t  ██████╗ █████╗ ███╗   ███╗\n"
            + "\t ██╔════╝██╔══██╗████╗ ████║\n"
            + "\t ╚█████╗ ███████║██╔████╔██║\n"
            + "\t  ╚═══██╗██╔══██║██║╚██╔╝██║\n"
            + "\t ██████╔╝██║  ██║██║ ╚═╝ ██║\n"
            + "\t ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝";
    private static final String USER =
              "  ███████\n"
            + " ████▀██▀█\n"
            + " ████▄██▄█\n"
            + "  ▀▀▀▀▀▀▀";
    private static final String SAM =
              "\t\t\t\t        ▄\n"
            + "\t\t\t\t ▒▒██▒▒▓▓▀\n"
            + "\t\t\t\t▒▒▀██▀▒▒▓▓\n"
            + "\t\t\t\t █▄██▄███▓▓\n"
            + "\t\t\t\t  ▀▀▀▀▀▀ ▓";

    // A private variable that is used to read user input.
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showLogo() {
        System.out.println(LOGO);
    }

    public String acceptInput() {
        System.out.println();
        System.out.println(USER);
        System.out.print("> ");
        return scanner.nextLine().strip();
    }

    public void talk(String... messages) {
        System.out.println(SAM);
        System.out.println("┌───────────────────────────────────────────┐");
        for (String message : messages) {
            System.out.println("  " + message);
        }
        System.out.println("└───────────────────────────────────────────┘");
    }
}
