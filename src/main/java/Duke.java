import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        // Initialise variables
        Scanner scanner = new Scanner(System.in);
        String command = "";
        String delimiter = "---";
        while (true) {
            command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println(delimiter);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(delimiter);
                break;
            }
            System.out.println(delimiter);
            System.out.println(command);
            System.out.println(delimiter);
        }
    }
}
