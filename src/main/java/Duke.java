import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<String> todo = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void echo(String command) {
        if (command.equals("bye"))  {
            exit();
        } else if (command.equals("list")) {
            int counter = 1;
            for (String element: todo) {
                System.out.println(counter + ". " + element);
                counter++;
            }
        } else {
            todo.add(command);
            System.out.println("added: " + command);
        }
    }
    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        sc.close();
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        try {
            while (sc.hasNext()) {
                echo(sc.nextLine());
            }
        } catch (IllegalStateException e) {
            System.exit(0);
        }
    }
}
