import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    // pretty prints a given string
    public static void prettyPrint(String text) {
        System.out.println(
            "____________________________________________________________"
        );
        System.out.println(text);
        System.out.println(
            "____________________________________________________________\n"
        );
    }

    // adds a task to the list
    public static void addTaskToList(Task task, ArrayList<Task> list) {
        list.add(task);
        String s = String.format(
            "Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
            task.toString(),
            list.size()
        );
        Duke.prettyPrint(s);
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
            try {
                // scan for user input
                rawInput = scanner.nextLine();
                rawSplit = rawInput.split(" ", 2);
                command = rawSplit[0];
                if (rawSplit.length > 1) {
                    arguments = rawSplit[1].split("\\W\\/[a-zA-Z]+\\W");
                } else {
                    arguments = new String[0];
                }

                // parse commands with no arguments
                if (command.equals("bye")) {
                    Duke.prettyPrint("Bye. Hope to see you again soon!");
                    break;
                } else if (command.equals("list")) {
                    if (list.size() == 0) {
                        Duke.prettyPrint("You have no tasks! Try adding some.");
                    } else {
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
                }
                // parse commands with arguments
                else if (command.equals("mark")) {
                    // possible errors for mark and unmark
                    // 1. invalid # arguments (no mark target given)
                    // 2. mark target doesnt exist (out of range)
                    int index = Integer.parseInt(arguments[0]) - 1;
                    Task currentTask = list.get(index);
                    currentTask.markAsDone();
                    String s = String.format(
                        "Nice! I've marked this task as done:\n %s",
                        currentTask.toString()
                    );
                    Duke.prettyPrint(s);
                } else if (command.equals("unmark")) {
                    int index = Integer.parseInt(arguments[0]) - 1;
                    Task currentTask = list.get(index);
                    currentTask.unmarkAsDone();
                    String s = String.format(
                        "Ok, I've marked this task as not done yet:\n %s",
                        currentTask.toString()
                    );
                    Duke.prettyPrint(s);
                } else if (command.equals("todo")) {
                    // implicitly handles todo commands with empty descriptions
                    // as those will have 0 arguments
                    if (arguments.length != 1) {
                        throw new InvalidArgumentException(
                            command,
                            arguments.length,
                            1
                        );
                    }
                    Task currentTask = new ToDo(arguments[0]);
                    Duke.addTaskToList(currentTask, list);
                } else if (command.equals("deadline")) {
                    // possible errors for deadline & event
                    // 1. arguments may not have the proper format.
                    //    code currently reads any string starting with '/' as
                    //    the start of an argument. not necessarily '/by'
                    //    or '/from' or '/to', as expected
                    if (arguments.length != 2) {
                        throw new InvalidArgumentException(
                            command,
                            arguments.length,
                            2
                        );
                    }
                    Task currentTask = new Deadline(arguments[0], arguments[1]);
                    Duke.addTaskToList(currentTask, list);
                } else if (command.equals("event")) {
                    if (arguments.length != 3) {
                        throw new InvalidArgumentException(
                            command,
                            arguments.length,
                            3
                        );
                    }
                    Task currentTask = new Event(
                        arguments[0],
                        arguments[1],
                        arguments[2]
                    );
                    Duke.addTaskToList(currentTask, list);
                }
                // undefined commands
                else {
                    throw new UnknownCommandException(rawInput);
                }
            } catch (DukeException e) {
                Duke.prettyPrint(e.toString());
            }
        }
    }
}
