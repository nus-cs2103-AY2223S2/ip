import java.util.Scanner;
public class Duke {
    protected static String divider = "     ____________________________________________________________";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printWelcomeMessage();

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(formatMessage("Bye. Hope to see you again soon!"));
                break;
            }
            System.out.println(formatMessage(command));
        }
    }

    public static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(divider);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(divider);
    }

    public static String formatMessage(String message) {
        return divider + "\n     " + message + "\n" + divider;
    }
}
