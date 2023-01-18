import java.util.*;

public class Duke {
    public static void main(String[] args) {

        // Print intro
        String intro = "Hello! I'm Duke\n What can I do for you?";
        System.out.println(intro);

        // Create Scanner object
        Scanner sc = new Scanner(System.in);
        TaskList tl = new TaskList();

        // Always ready to receive input
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    // Exit Duke
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    // List tasks
                    System.out.println(tl.toString());
                    break;
                default:
                    if (input.matches("mark \\d+")) {
                        // Marking a task as done
                        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

                        // Verify if task number is invalid:
                        if (idx < 0 || idx >= tl.numberOfTasks()) {
                            System.out.println("Task number is invalid!");
                            continue;
                        }
                        tl.markTask(idx);
                        System.out.println("Nice! I've marked this task as done:\n" + tl.getTask(idx));
                    } else if (input.matches("unmark \\d+")) {
                        // Unmarking a task
                        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;

                        // Verify if task number is invalid:
                        if (idx < 0 || idx >= tl.numberOfTasks()) {
                            System.out.println("Task number is invalid!");
                            continue;
                        }
                        tl.unmarkTask(idx);
                        System.out.println("OK, I've marked this task as not done yet:\n" + tl.getTask(idx));
                    } else  {
                        // Add task
                        try {
                            Task t = tl.addTask(input);
                            System.out.println("Got it. I've added this task:\n" + t + "\nNow you have " + tl.numberOfTasks() + (tl.numberOfTasks() > 1 ? " tasks" : " task") + " in the list");
                        } catch (CommandNotFoundException e) {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        } catch (EmptyCommandException e) {
                            System.out.println("☹ OOPS!!! The description of a " + e.command + " cannot be empty.");
                        }
                    }
            }
        }
    }
}