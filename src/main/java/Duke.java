import java.util.*;
import java.util.regex.*;
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

            } else {

                String toFind = "unmark ";
                Pattern word = Pattern.compile(toFind);
                Matcher match = word.matcher(input);
                boolean gotMatch = match.find();

                if (gotMatch && match.start() == 0) {
                    String[] inputArr = input.split(" ");
                    int toUnmark = Integer.parseInt(inputArr[1]);
                    Task unmarkTask = taskStorage.get(toUnmark - 1);
                    unmarkTask.markUndone();
                    System.out.println("OK, I've marked this task as undone:\n" + unmarkTask);
                    input = sc.nextLine();
                    continue;

                }

                toFind = "mark ";
                word = Pattern.compile(toFind);
                match = word.matcher(input);
                gotMatch = match.find();

                if (gotMatch && match.start() == 0) {
                    String[] inputArr = input.split(" ");
                    int toMark = Integer.parseInt(inputArr[1]);
                    Task markTask = taskStorage.get(toMark - 1);
                    markTask.markDone();
                    System.out.println("Nice! I've marked this task as done:\n" + markTask);
                    input = sc.nextLine();
                    continue;

                }

                toFind = "delete ";
                word = Pattern.compile(toFind);
                match = word.matcher(input);
                gotMatch = match.find();

                if (gotMatch && match.start() == 0) {
                    String[] inputArr = input.split(" ");
                    int toDelete = Integer.parseInt(inputArr[1]);
                    Task deleteTask = taskStorage.remove(toDelete - 1);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deleteTask.toString());
                    System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");
                    input = sc.nextLine();
                    continue;
                }


                try {
                    validate(input);
                } catch (DukeException de) {
                    System.out.println(de.getMessage());
                    input = sc.nextLine();
                    continue;
                }

                String[] inputArr = input.split(" ", 2);
                String taskType = inputArr[0];

                switch (taskType) {
                    case "todo":
                        ToDo todoTask = new ToDo(inputArr[1]);
                        taskStorage.add(todoTask);
                        System.out.println("Got it. I've added this ToDo task:");
                        System.out.println(todoTask);
                        System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");

                        break;
                    case "deadline": {
                        String[] newInputArr = inputArr[1].split(" /by ", 2);
                        Deadline deadlineTask = new Deadline(newInputArr[0], newInputArr[1]);
                        taskStorage.add(deadlineTask);
                        System.out.println("Got it. I've added this Deadline task:");
                        System.out.println(deadlineTask);
                        System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");

                        break;
                    }
                    case "event": {
                        String[] newInputArr = inputArr[1].split(" /from ", 2);
                        String[] newerInputArr = newInputArr[1].split(" /to ", 2);
                        Event eventTask = new Event(newInputArr[0], newerInputArr[0], newerInputArr[1]);
                        taskStorage.add(eventTask);
                        System.out.println("Got it. I've added this Event task:");
                        System.out.println(eventTask);
                        System.out.println("Now you have " + taskStorage.size() + " tasks in the list.");

                        break;
                    }
                }
            }

            input = sc.nextLine();
        }

        printGoodbye();
        sc.close();
    }

    public static void validate(String input) throws DukeException {
        input = input.trim();
        if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
            throw new DukeException("OOPS!!! The description of a " + input + " task cannot be empty!");
        } else if (!input.contains("todo") && !input.contains("deadline") && !input.contains("event")) {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means! :(");
        }
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
