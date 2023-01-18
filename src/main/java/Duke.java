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
        while (true) {
            String input = sc.nextLine();
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                case "list":
                    System.out.println(tl.toString());
                    break;
                default:
                    if (input.matches("mark \\d+")) {
                        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;
                        if (idx < 0 || idx >= tl.numberOfTasks()) {
                            System.out.println("Task number is invalid!");
                            continue;
                        }
                        tl.markTask(idx);
                        System.out.println("Nice! I've marked this task as done:\n" + tl.getTask(idx));
                    } else if (input.matches("unmark \\d+")) {
                        Integer idx = Integer.valueOf(input.split(" ")[1]) - 1;
                        if (idx < 0 || idx >= tl.numberOfTasks()) {
                            System.out.println("Task number is invalid!");
                            continue;
                        }
                        tl.unmarkTask(idx);
                        System.out.println("OK, I've marked this task as not done yet:\n" + tl.getTask(idx));
                    } else {
                        tl.addTask(input);
                        System.out.println("added: " + input);
                    }
            }
        }
    }
}