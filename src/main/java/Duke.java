import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        */

        String greeting = "What's up! XyDuke here!\nHow can I be of assistance?";
        System.out.println(greeting + "\n");

        String[] commands = {"bye", "list"};
        ArrayList<Task> taskStorage = new ArrayList<>();

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                printTasks(taskStorage);

            } else if (input.contains("unmark")) {
                String[] inputArr = input.split(" ");
                int toUnmark = Integer.parseInt(inputArr[1]);
                Task unmarkTask = taskStorage.get(toUnmark-1);
                unmarkTask.markUndone();
                System.out.println("OK, I've marked this task as undone:\n" + unmarkTask);

            } else if (input.contains("mark")) {
                String[] inputArr = input.split(" ");
                int toMark = Integer.parseInt(inputArr[1]);
                Task markTask = taskStorage.get(toMark-1);
                markTask.markDone();
                System.out.println("Nice! I've marked this task as done:\n" + markTask);

            } else {
                taskStorage.add(new Task(input));
                String output = String.format("Task added: %s", input);
                System.out.println(output);
            }

            input = sc.nextLine();
        }

        printGoodbye();
        sc.close();
    }

    public static void printGoodbye() {
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(goodbye);
    }

    public static void printTasks(ArrayList<Task> taskStorage) {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : taskStorage) {
            String output = String.format("%d.%s", count++, task.toString());
            System.out.println(output);
        }
    }

    public static boolean isCommand(String input, String[] commands) {
        for (String s : commands) {
            if (s.equals(input)) {
                return true;
            }
        }
        return false;
    }
}
