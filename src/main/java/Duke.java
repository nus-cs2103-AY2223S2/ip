import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        System.out.println("Hello, I am Duke. \nWhat can I do for you?");

        String input = scanner.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            try {
                if (input.equalsIgnoreCase("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i));
                    }
                } else if (input.startsWith("mark ")) {
                    String taskName = input.substring(5);
                    int index = Integer.parseInt((taskName)) - 1;
                    Task task = tasks.get(index);
                    task.mark();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(task);
                } else if (input.startsWith("unmark ")) {
                    String taskName = input.substring(7);
                    int index = Integer.parseInt((taskName)) - 1;
                    Task task = tasks.get(index);
                    task.unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(task);
                } else if (input.startsWith("todo")) {
                    if (input.length() < 5) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    String taskName = input.substring(5);
                    Todo todo = new Todo(taskName);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task: \n" + todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("deadline")) {
                    int dash_index = input.indexOf("/");
                    String taskName = input.substring(9, dash_index);
                    String by = input.substring(dash_index + 4);
                    Deadline deadline = new Deadline(taskName, by);
                    tasks.add(deadline);
                    System.out.println("Got it. I've added this task: \n" + deadline);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (input.startsWith("event")) {
                    int first_dash_index = input.indexOf("/");
                    int second_dash_index = input.lastIndexOf("/");
                    String taskName = input.substring(6, first_dash_index);
                    String from = input.substring(first_dash_index + 6, second_dash_index);
                    String to = input.substring(second_dash_index + 4);
                    Event event = new Event(taskName, from, to);
                    tasks.add(event);
                    System.out.println("Got it. I've added this task: \n" + event);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

        scanner.close();
    }
}
