import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        final String Indentation = " ";
        System.out.println(Indentation + "____________________________________________________________");
        System.out.println(Indentation + "Hello! I'm Duke");
        System.out.println(Indentation + "What can I do for you?");
        System.out.println(Indentation + "____________________________________________________________");

        Scanner str = new Scanner(System.in);
        String command;

        do {
            command = str.nextLine();
            if (!command.equals("bye")) {
                System.out.println(Indentation + "____________________________________________________________");
                System.out.println(Indentation + command);
                System.out.println(Indentation + "____________________________________________________________");
            }

        } while (!command.equals("bye"));

        System.out.println(Indentation + "____________________________________________________________");
        System.out.println(Indentation + "Bye. Hope to see you again soon!");
        System.out.println(Indentation + "____________________________________________________________");
    }
}
