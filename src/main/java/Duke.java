import java.util.Scanner;
public class Duke {

    public static void list() {
        System.out.println("\t----------------------------");
        System.out.println("\tlist");
        System.out.println("\t----------------------------\n");
    }

    public static void blah() {
        System.out.println("\t----------------------------");
        System.out.println("\tblah");
        System.out.println("\t----------------------------\n");
    }

    public static void bye() {
        System.out.println("\t----------------------------");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t----------------------------\n");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        while (true) {
            if (!input.equals("bye")) {
                System.out.println("\t----------------------------");
                System.out.println("\t" + input);
                System.out.println("\t----------------------------");
                input = scanner.nextLine();
            } else {
                Duke.bye();
                break;
            }

        }


    }
}
