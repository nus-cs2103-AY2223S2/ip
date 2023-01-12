import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> todo = new ArrayList<>();

    public static Scanner sc = new Scanner(System.in);

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static void echo(String command) {
        if (command.equals("bye"))  {
            exit();
        } else if (command.equals("list")) {
            int counter = 1;
            for (Task element : todo) {
                System.out.println(counter + ". " + "[" + element.getStatusIcon() + "] " + element.description);
                counter++;
            }
        } else if (command.contains("unmark")) {
            int taskNum = Integer.parseInt(command.substring(7));
            Task task = todo.get(taskNum -1);
            task.markAsIncomplete();
        } else if (command.contains("mark")) {
            int taskNum = Integer.parseInt(command.substring(5));
            Task task = todo.get(taskNum - 1);
            task.markAsDone();
        } else {
            todo.add(new Task(command));
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
