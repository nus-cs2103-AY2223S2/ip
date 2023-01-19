import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String INDENT_LINE = "____________________________________________________________";

        System.out.println(INDENT_LINE);
        System.out.println("Hello! I'm Vincent");
        System.out.println("What can I do for you?");
        System.out.println(INDENT_LINE);

        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            String task = sc.nextLine();
            if (task.equals("bye")) {
                System.out.println(INDENT_LINE);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(INDENT_LINE);
                break;
            } else if (task.equals("list")) {
                System.out.println(INDENT_LINE);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i+1) + "." + taskList.get(i));
                }
                System.out.println(INDENT_LINE);
            } else if (task.startsWith("mark")) {
                int index = Integer.parseInt(task.substring(5)) - 1;
                taskList.get(index).markAsDone();
                System.out.println(INDENT_LINE);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println((index+1) + "." + taskList.get(index));
                System.out.println(INDENT_LINE);
            } else if (task.startsWith("unmark")) {
                int index = Integer.parseInt(task.substring(7)) - 1;
                taskList.get(index).markAsUndone();
                System.out.println(INDENT_LINE);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println((index+1) + "." + taskList.get(index));
                System.out.println(INDENT_LINE);
            } else {
                if (task.startsWith("todo")) {
                    taskList.add(new Todo(task.substring(5)));
                } else if (task.startsWith("deadline")) {
                    String[] arr = task.split("/");
                    String description = arr[0].substring(9);
                    String by = arr[1].substring(3);
                    taskList.add(new Deadline(description, by));
                } else if (task.startsWith("event")) {
                    String[] arr = task.split("/");
                    String description = arr[0].substring(6);
                    String from = arr[1].substring(5);
                    String to = arr[2].substring(3);
                    taskList.add(new Event(description, from, to));
                }
                int len = taskList.size();
                System.out.println(INDENT_LINE);
                System.out.println("Got it. I've added this task:");
                System.out.println(taskList.get(len - 1));
                System.out.println("Now you have " + len + " tasks in the list.");
                System.out.println(INDENT_LINE);
            }
        }
    }
}
