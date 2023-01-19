import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void prettyPrint(String text) {
        System.out.println(
            "____________________________________________________________"
        );
        System.out.println(text);
        System.out.println(
            "____________________________________________________________\n"
        );
    }

    public static void main(String[] args) {
        String logo =
            " ____        _        \n" +
            "|  _ \\ _   _| | _____ \n" +
            "| | | | | | | |/ / _ \\\n" +
            "| |_| | |_| |   <  __/\n" +
            "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke.prettyPrint("Hello! I'm Duke\nWhat can I do for you?");

        // parse user input
        Scanner scanner = new Scanner(System.in);
        String rawInput;
        String[] arguments;
        String command;
        ArrayList<Task> list = new ArrayList<>();

        // level 3 functionality:
        while (true) {
            // scan for user input
            rawInput = scanner.nextLine();
            arguments = rawInput.split(" ");
            command = arguments[0];

            // parse cases
            if (command.equals("bye")) {
                // case: "bye"
                // exit program
                Duke.prettyPrint("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                // case: "list"
                // construct string to be printed
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    Task currentTask = list.get(i);
                    String s = String.format(
                        "%d: %s\n",
                        i + 1,
                        currentTask.toString()
                    );
                    sb.append(s);
                }
                // pprint string
                Duke.prettyPrint(sb.toString());
            } else if (command.equals("mark")) {
                // case: "mark'
                int index = Integer.parseInt(arguments[1]) - 1;
                Task currentTask = list.get(index);
                currentTask.markAsDone();
                String s = String.format(
                    "Nice! I've marked this task as done:\n %s",
                    currentTask.toString()
                );
                Duke.prettyPrint(s);
            } else if (command.equals("unmark")) {
                // case: "unmark'
                int index = Integer.parseInt(arguments[1]) - 1;
                Task currentTask = list.get(index);
                currentTask.unmarkAsDone();
                String s = String.format(
                    "Ok, I've marked this task as not done yet:\n %s",
                    currentTask.toString()
                );
                Duke.prettyPrint(s);
            } else {
                // case: everything else
                // add item to list
                Task currentTask = new Task(rawInput);
                list.add(currentTask);
                Duke.prettyPrint("added:" + rawInput);
            }
        }
    }
}
