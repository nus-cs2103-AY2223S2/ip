import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(command);
                System.out.println("What else can I do for you?");
                command = sc.nextLine();
            } else if (command.equals("blah")) {
                System.out.println(command);
                System.out.println("What else can I do for you?");
                command = sc.nextLine();
            } else {
                System.out.println("I can't seem to perform this task. Please enter a valid command!");
                command = sc.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");



    }
}
