import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________");

        String input = sc.nextLine();
        while(!input.equals("bye")) {
            printMsg(input);
            input = sc.nextLine();
        }

        printMsg("Bye. Hope to see you again soon!");

    }

    private static void printMsg(String msg) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + msg);
        System.out.println("    ____________________________________________________________");
    }
}
