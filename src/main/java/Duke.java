import java.util.Scanner;

public class Duke {

    private static final String LINE = "------------------------------------------------------------";

    private static void start() {

        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();

        while (true) {
            System.out.print("You:\n");
            String command = sc.nextLine().strip().replaceAll("( )+", " ");

            System.out.println("\nDuke:");

            if (command.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Have a nice day!\n");
                System.out.println(LINE);
                break;

            } else {
                try {
                    tm.processCommand(command);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println("\n" + LINE + "\n");
                }

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
