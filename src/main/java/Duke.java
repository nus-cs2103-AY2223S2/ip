import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    /**
     * A level 2 chat bot Duke.
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
        ArrayList<String> todo = new ArrayList<>();
        String input = ai.nextLine();
        while (!input.equals("bye")) {
            if (!input.equals("list")) {
                todo.add(input);
                System.out.println("added: " + input);
            } else {
                for (int i = 0; i < todo.size(); i++) {
                    System.out.println(i + 1 + ". " + todo.get(i));
                }
            }
            input = ai.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
