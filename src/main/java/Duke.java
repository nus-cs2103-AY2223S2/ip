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
        System.out.println(greeting);

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

                String[] inputArr = input.split(" ", 2);
                String taskType = inputArr[0];

                if (taskType.equals("todo")) {
                    ToDo todoTask = new ToDo(inputArr[1]);
                    taskStorage.add(todoTask);
                    System.out.println("Got it. I've added this ToDo task:");
                    System.out.println(todoTask.toString());
                    System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");

                } else if (taskType.equals("deadline")) {
                    String[] newInputArr = inputArr[1].split(" /by ", 2);
                    Deadline deadlineTask = new Deadline(newInputArr[0], newInputArr[1]);
                    taskStorage.add(deadlineTask);
                    System.out.println("Got it. I've added this Deadline task:");
                    System.out.println(deadlineTask.toString());
                    System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");

                } else if (taskType.equals("event")) {
                    String[] newInputArr = inputArr[1].split(" /from ", 2);
                    String[] newerInputArr = newInputArr[1].split(" /to ", 2);
                    Event eventTask = new Event(newInputArr[0], newerInputArr[0], newerInputArr[1]);
                    taskStorage.add(eventTask);
                    System.out.println("Got it. I've added this Event task:");
                    System.out.println(eventTask.toString());
                    System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");

                } else {
                }

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
