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
            } else if (task.substring(0, 4).equals("mark")) {
                int index = Integer.parseInt(task.substring(5)) - 1;
                taskList.get(index).markAsDone();
                System.out.println(INDENT_LINE);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println((index+1) + "." + taskList.get(index));
                System.out.println(INDENT_LINE);
            } else if (task.substring(0, 6).equals("unmark")) {
                int index = Integer.parseInt(task.substring(7)) - 1;
                taskList.get(index).markAsUndone();
                System.out.println(INDENT_LINE);
                System.out.println("Nice! I've marked this task as not done yet:");
                System.out.println((index+1) + "." + taskList.get(index));
                System.out.println(INDENT_LINE);
            }
            else {
                taskList.add(new Task(task));
                System.out.println(INDENT_LINE);
                System.out.println("added: " + task);
                System.out.println(INDENT_LINE);
            }
        }
    }
}
