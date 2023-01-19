import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public static void printList(ArrayList<Task> stored) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < stored.size(); i++) {
            Task task = stored.get(i);
            System.out.println((i+1) + ". [" + task.getStatusIcon() + "] " + task.getDescription());
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String separator = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(separator);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> myTasks = new ArrayList<>();
        String input;
        while(true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(separator);
                break;
            } else if (input.equals("list")) {
                printList(myTasks);
                System.out.println(separator);

            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.substring(5));
                taskNumber--;
                Task task = myTasks.get(taskNumber);
                task.taskDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [" + task.getStatusIcon() + "] " + task.getDescription());

            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.substring(7));
                taskNumber--;
                Task task = myTasks.get(taskNumber);
                task.taskNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" [" + task.getStatusIcon() + "] " + task.getDescription());
            } else {
                Task task = new Task(input);
                myTasks.add(task);
                System.out.println("added: " + task.getDescription());
                System.out.println(separator);
            }
        }
    }
}

