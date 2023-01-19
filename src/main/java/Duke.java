import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(separator);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        Scanner scanner = new Scanner(System.in);
        String input;
        while(true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            }
            System.out.println(input);
            System.out.println(separator);
        }
    }
}
