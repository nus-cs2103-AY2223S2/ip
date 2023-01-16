import java.util.Scanner;

public class Duke {
    public static final String LOGO = "\n ____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n\n";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Tasks tasks = new Tasks();

        System.out.println("Hello I am" + LOGO + "What Can I do for you?");
        while(true) {
            System.out.print(">>> ");
            String cmd = sc.nextLine();
            if (cmd.matches("^bye$")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (cmd.matches("^list$")) {
                System.out.println("Here are the tasks in your list:");
                System.out.println(tasks);
            } else if (cmd.matches("^mark [0-9]*$")) {
                int index = Integer.parseInt(cmd.substring(5)) - 1;
                tasks.markTask(index);
            } else if (cmd.matches("^unmark [0-9]*$")) {
                int index = Integer.parseInt(cmd.substring(7)) - 1;
                tasks.unmarkTask(index);
            } else if (cmd.matches("^todo .*$")) {
                String taskName = cmd.substring(5);
                tasks.addTask(new Todo(taskName));
            } else if (cmd.matches("^deadline .* /by .*$")) {
                int byStart = cmd.indexOf("/by");
                String taskName = cmd.substring(9, byStart - 1);
                String by = cmd.substring(byStart + 4);
                tasks.addTask(new Deadline(taskName, by));
            } else if (cmd.matches("^event .* /from .* /to .*$")) {
                int byStart = cmd.indexOf("/from");
                int toStart = cmd.indexOf("/to");
                String taskName = cmd.substring(6, byStart - 1);
                String by = cmd.substring(byStart + 6, toStart-1);
                String to = cmd.substring(toStart + 4);
                tasks.addTask(new Event(taskName, by, to));
            } else {
                System.out.println("I don't quite understand what you mean.");
            }
        }
        sc.close();
    }
}
