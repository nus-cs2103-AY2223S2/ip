import java.util.*;

public class Duke {
    public static void main(String[] args) {

        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! I'm Somebody\n" + "What can I do for you?");
        Scanner scn = new Scanner(System.in);
        while (true) {

            String input = scn.nextLine();

            if (input.startsWith("mark")) {

                String index = input.split(" ", 2)[1];
                Task task = list.get(Integer.parseInt(index) - 1);
                task.markAsDone();
                System.out.println("\nNice! I've marked this task as done:\n" + task.toString() + "\n");

            } else if (input.startsWith("unmark")) {

                String index = input.split(" ", 2)[1];
                Task task = list.get(Integer.parseInt(index) - 1);
                task.unmark();
                System.out.println("\nOK, I've marked this task as not done yet:\n" + task.toString() + "\n");

            } else if (Objects.equals(input.toLowerCase(), "bye")) {

                System.out.println("\nBye. Hope to see you again soon!\n");
                scn.close();
                return;

            } else if (Objects.equals(input.toLowerCase(), "list")) {

                System.out.println("\nHere are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    Task task = list.get(i - 1);
                    System.out.println(i + "." + task.toString());
                }
                System.out.println("\n");

            } else {

                if (input.contains("todo")) {
                    String description = input.replace("todo", "");
                    Todo todo = new Todo(description);
                    list.add(todo);
                    System.out.println("\nGot it. I've added this task:\n" + todo.toString() + "\nNow you have "
                            + list.size() + " tasks in the list.\n");
                } else if (input.contains("deadline")) {
                    String command = input.replace("deadline", "");
                    String description = command.split("/by")[0];
                    String date = command.split("/by")[1];
                    Deadline deadline = new Deadline(description, date);
                    list.add(deadline);
                    System.out.println("\nGot it. I've added this task:\n" + deadline.toString() + "\nNow you have "
                            + list.size() + " tasks in the list.\n");
                } else if (input.contains("event")) {
                    String command = input.replace("event", "");
                    String description = command.split("/from")[0];
                    String remainder = command.split("/from")[1];
                    String from = remainder.split("/to")[0];
                    String to = remainder.split("/to")[1];

                    Event event = new Event(description, from, to);
                    list.add(event);
                    System.out.println("\nGot it. I've added this task:\n" + event.toString() + "\nNow you have "
                            + list.size() + " tasks in the list.\n");
                }
            }

        }
    }
}
