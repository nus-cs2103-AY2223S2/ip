import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Duke {
    private static int counter;
    private static HashMap<Integer, Task> toDoList;

    public static void main(String[] args)  {
        boolean terminate = false;
        counter = 0;
        toDoList = new HashMap<>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        try {
            while (!terminate) {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String command = br.readLine();
                String[] inputs = command.split(" ");

                if (command.equals("")) {
                    // Do nothing

                // Once detect "bye", print and terminate
                } else if (command.equals("bye")) {
                    terminate = true;
                    System.out.println("Bye. Hope to see you again soon!");

                // If command is "list", prints the toDoList
                } else if (command.equals("list")) {
                    print_list();

                // if the first token is "mark" and second token is within limit
                } else if (inputs[0].equals("mark") && inputs.length == 2) {
                    Integer task_no = Integer.parseInt(inputs[1]);
                    if (task_no <= inputs.length) {
                        mark(task_no);
                    } else {
                        System.out.println("Invalid task number!");
                    }

                // if the first token is "unmark" and second token is within limit
                } else if (inputs[0].equals("unmark") && inputs.length == 2) {
                    Integer task_no = Integer.parseInt(inputs[1]);
                    if (task_no <= inputs.length) {
                        unmark(task_no);
                    } else {
                        System.out.println("Invalid task number!");
                    }

                // Else handles the commands
                } else {
                    toDoList.put(++counter, new Task(command));
                    System.out.print("added: ");
                    System.out.println(command);

                }

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /*
     * print_list iterates through the toDoList and prints
     * out the status of each individual tasks
     */
    public static void print_list() {
        System.out.println("Here are the tasks in your list: ");

        for (Integer key: toDoList.keySet()) {
            Task toDo = toDoList.get(key);

            System.out.println(key + ". " + toDo);

        }
    }

    /*
     * mark takes in an Integer key
     * and marks the corresponding task as completed
     */
    public static void mark(Integer key) {
        toDoList.get(key).markCompleted();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("   " + toDoList.get(key));

    }

    /*
     * mark takes in an Integer key
     * and marks the corresponding task as not completed
     */
    public static void unmark(Integer key) {
        toDoList.get(key).markUncompleted();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("   " + toDoList.get(key));
    }
}
