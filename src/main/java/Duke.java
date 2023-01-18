import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static void validateInput(String[] tokens, int expectedMin) {

    }
    public static void main(String[] args) {

        // Initialise variables
        List<Task> userTasks =  new ArrayList<Task>();
        Scanner scanner = new Scanner(System.in);
        String command = "";
        String delimiter = "\t---";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);

        while (true) {
            command = scanner.nextLine();
            System.out.println(delimiter);
            if (command.equals("bye")) {
                System.out.println("\tBye. Hope to see you again soon!");
                System.out.println(delimiter);
                break;
            }

            String[] tokens = command.split(" ");
            if (command.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                int i = 1;
                for (Task s: userTasks) {
                    System.out.printf("\t%d.%s\n", i, s);
                    i++;
                }
            } else if (tokens[0].equals("mark")) {
                Task t = userTasks.get(Integer.parseInt(tokens[1]) - 1);
                t.setIsDone(true);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t" + t);
            } else if (tokens[0].equals("unmark")){
                Task t = userTasks.get(Integer.parseInt(tokens[1]) - 1);
                t.setIsDone(false);
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t" + t);
            } else {
                System.out.printf("\tadded: %s\n", command);
                userTasks.add(new Task(command));
            }
            System.out.println(delimiter);
        }
    }
}
