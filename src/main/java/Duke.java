import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void printer(String[] input) {
        System.out.println("\t____________________________________________________________");
        for (String i : input) {
            System.out.println("\t" + i);
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void printer(String input) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + input);
        System.out.println("\t____________________________________________________________");
    }

    public static void taskPrinter(ArrayList<Task> input) {
        System.out.println("\t____________________________________________________________");
        for (int i = 0; i < input.size(); i++) {
            Task t = input.get(i);
            System.out.println("\t" + (i + 1) + ".[" + (t.done ? "X" : " ") + "] " + t.task);
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void taskMarker(Task input) {
        System.out.println("\t____________________________________________________________");
        if (input.done) {
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t[X] " + input.task);
        } else {
            System.out.println("\tNice! I've marked this task as not done:");
            System.out.println("\t[ ] " + input.task);
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String[] strings = { "Hello I'm Duke", "What can I do for you?" };
        printer(strings);

        ArrayList<Task> taskList = new ArrayList<>();

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                taskPrinter(taskList);
            } else if (userInput.split(" ")[0].equals("mark")) {
                int taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task task = taskList.get(taskId);
                task.markAsDone();
                taskMarker(task);
                taskList.set(taskId, task);

            } else if (userInput.split(" ")[0].equals("unmark")) {
                int taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task task = taskList.get(taskId);
                task.markAsNotDone();
                taskMarker(task);
                taskList.set(taskId, task);
            } else {
                printer("added: " + userInput);
                Task task = new Task(userInput);
                taskList.add(task);
            }
            userInput = sc.nextLine();

        }
        sc.close();
        printer("Bye. Hope to see you again soon!");
    }
}
