import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean active = true;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        while (active) {
            String input = s.nextLine();
            printOutput(input);
            if (input.equalsIgnoreCase("bye")) {
                active = false;
            }
        }
    }

    public static void printOutput(String text) {
        System.out.println("\t____________________________________________________________");
        if (text.equalsIgnoreCase("bye")) {
            System.out.println("\t Bye. Hope to see you again soon!");
        } else {
            System.out.println("\t " + text);
        }
        System.out.println("\t____________________________________________________________");
    }
}
