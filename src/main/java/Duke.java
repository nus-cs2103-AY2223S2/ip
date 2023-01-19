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
            System.out.println("\t" + (i + 1) + "." + t.toString());
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void taskMarker(Task input) {
        System.out.println("\t____________________________________________________________");

        if (input.done) {
            System.out.println("\tNice! I've marked this task as done:");

        } else {
            System.out.println("\tNice! I've marked this task as not done:");
        }
        System.out.println("\t" + input.toString());
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

        TaskList taskList = new TaskList();
        Commands commands = new Commands(taskList);

        String userInput = sc.nextLine().strip();
        while (!userInput.equals("bye")) {
            String taskType = userInput.split(" ")[0];
            if (taskType.equals("list")) {
                commands.listTasks();
            } else if (taskType.equals("mark")) {
                int taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                commands.markTask(taskId);
            } else if (taskType.equals("unmark")) {
                int taskId = Integer.parseInt(userInput.split(" ")[1]) - 1;
                commands.unmarkTask(taskId);
            } else if (taskType.equals("todo")) {
                commands.addToDoTask(userInput);
            } else if (taskType.equals("deadline")) {
                commands.addDeadlineTask(userInput);
            } else if (taskType.equals("event")) {
                commands.addEventTask(userInput);
            }
            userInput = sc.nextLine().strip();

        }
        sc.close();
        printer("Bye. Hope to see you again soon!");
    }
}
