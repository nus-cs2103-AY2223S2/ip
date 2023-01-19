import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    static LinkedList<String> todo = new LinkedList<>();
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    static String line = "---------------------------------";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello from\n" + logo + "  managed by Wesley Teo.\n\nWhat can I do for you?\n" + line);
        String input = sc.nextLine();
        System.out.println(line);
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                for (String task : todo) {
                    System.out.println(todo.indexOf(task) + 1 + ". " + task);
                }
            } else {
                todo.add(input);
                System.out.println("Added: " + input);
            }
            System.out.println(line);
            input = sc.nextLine();
            System.out.println(line);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
