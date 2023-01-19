import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        /* project starts here */
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?\n");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();
            System.out.println(command);
            if (command.equals("bye")) {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
}
