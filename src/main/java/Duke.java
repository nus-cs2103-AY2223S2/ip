import java.util.Scanner;

public class Duke {

    private static final String LINE = "------------------------------------------------------------";

    private static void processCommand(String cmd) {
        System.out.println("\nDuke:\n" + cmd);
        System.out.println("\n" + LINE + "\n");
    }

    private static void start() {

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("You:\n");
            String cmd = sc.nextLine().toLowerCase();

            if (cmd.equals("bye")) {
                System.out.println("\nDuke:\nBye. Have a nice day!\n");
                System.out.println(LINE);
                break;

            } else {
                processCommand(cmd);
            }

        }

        sc.close();

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\nHello from\n" + logo);

        System.out.println("How can I help you?\n");
        System.out.println(LINE + "\n");

        start();

    }
}
