import java.util.Scanner;
public class Duke {
    /**
     * A level 1 chat bot Duke.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner ai = new Scanner(System.in);
        String greetings = "Hello! I'm Duke" + "\nWhat can I do for you?";
        System.out.println(greetings);
        String input = ai.nextLine();
        while (!input.equals("bye")) {
            System.out.println(input);
            input = ai.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
