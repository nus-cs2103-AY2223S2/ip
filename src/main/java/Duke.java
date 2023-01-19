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
        String[] rawSplit;
        String[] arguments = {};
        String command;
        ArrayList<Task> list = new ArrayList<>();

        // level 3 functionality:
        while (true) {
            // scan for user input
            rawInput = scanner.nextLine();
            rawSplit = rawInput.split(" ", 2);
            command = rawSplit[0];
            if (rawSplit.length > 1) {
                arguments = rawSplit[1].split("\\/[a-zA-Z]+");
            }

            // parse commands with no arguments
            if (command.equals("bye")) {
                // case: "bye"
                Duke.prettyPrint("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list")) {
                // case: "list"
                StringBuilder sb = new StringBuilder(
                    "Here are the tasks in your list:\n"
                );
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
            }
            // parse commands with arguments
            else if (command.equals("mark")) {
                // case: "mark'
                int index = Integer.parseInt(arguments[0]) - 1;
                Task currentTask = list.get(index);
                currentTask.markAsDone();
                String s = String.format(
                    "Nice! I've marked this task as done:\n %s",
                    currentTask.toString()
                );
                Duke.prettyPrint(s);
            } else if (command.equals("unmark")) {
                // case: "unmark'
                int index = Integer.parseInt(arguments[0]) - 1;
                Task currentTask = list.get(index);
                currentTask.unmarkAsDone();
                String s = String.format(
                    "Ok, I've marked this task as not done yet:\n %s",
                    currentTask.toString()
                );
                Duke.prettyPrint(s);
            } else if (command.equals("todo")) {
                // case: "todo"
                Task currentTask = new ToDo(arguments[0]);
                list.add(currentTask);
                String s = String.format(
                    "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                    currentTask.toString(),
                    list.size()
                );
                Duke.prettyPrint(s);
            } else {
                Duke.prettyPrint(
                    "Sorry, I didn't understand your command. Maybe try again?"
                );
            }
        }
    }
}
