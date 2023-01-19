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
            Task t;
            switch(tokens[0]) {
                case "list":
                    System.out.println("\tHere are the tasks in your list:");
                    int i = 1;
                    for (Task s: userTasks) {
                        System.out.printf("\t%d.%s\n", i, s);
                        i++;
                    }
                    break;
                case "mark":
                    t = userTasks.get(Integer.parseInt(tokens[1]) - 1);
                    t.setIsDone(true);
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + t);
                    break;
                case "unmark":
                    t = userTasks.get(Integer.parseInt(tokens[1]) - 1);
                    t.setIsDone(false);
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.println("\t" + t);
                    break;
                case "todo":
                    tokens = command.split(" ", 2);
                    t = new ToDo(tokens[1]);
                    userTasks.add(t);
                    System.out.printf("\tGot it. I've added this task:\n" +
                            "\t%s\n\tNow you have %d tasks in the list.\n", t, userTasks.size());
                    break;
                case "deadline":
                    tokens = command.split("/");

                    t = new Deadline(tokens[0].split(" ", 2)[1],
                            tokens[1].split(" ", 2)[1]);
                    userTasks.add(t);
                    System.out.printf("\tGot it. I've added this task:\n" +
                            "\t%s\n\tNow you have %d tasks in the list.\n", t, userTasks.size());
                    break;
                case "event":
                    tokens = command.split("/");
                    t = new Event(tokens[0].split(" ", 2)[1],
                            tokens[1].split(" ", 2)[1],
                            tokens[2].split(" ", 2)[1]);
                    userTasks.add(t);
                    System.out.printf("\tGot it. I've added this task:\n" +
                            "\t%s\n\tNow you have %d tasks in the list.\n", t, userTasks.size());
                    break;
                default:
                    System.out.printf("\tadded: %s\n", command);
                    userTasks.add(new Task(command));
            }
//            if (command.equals("list")) {
//
//            } else if (tokens[0].equals("mark")) {
//
//            } else if (tokens[0].equals("unmark")){
//
//            } else {
//
//            }
            System.out.println(delimiter);
        }
    }
}
