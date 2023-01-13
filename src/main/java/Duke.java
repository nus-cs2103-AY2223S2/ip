import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greeting = "Hello! I'm Duke\n"
                    + "What can I do for you?\n";
        String separator = "____________________________________________________________\n";
        String goodbye = "Bye. Hope to see you again soon!\n";
        String endCommand = "bye";
        System.out.println(greeting + separator);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.next();
            if (command.equals(endCommand)) {
                sc.close();
                System.out.println(goodbye + separator);
                break;
            }
            System.out.println(command + "\n" + separator);
        }
    }
}
