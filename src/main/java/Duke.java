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
                System.out.println("Nice! I've marked this task as done:\n" + "[" + task.getStatusIcon() + "] "
                        + task.getDescription());

            } else if (input.startsWith("unmark")) {

                String index = input.split(" ", 2)[1];
                Task task = list.get(Integer.parseInt(index) - 1);
                task.unmark();
                System.out.println("OK, I've marked this task as not done yet:\n" + "[" + task.getStatusIcon() + "] "
                        + task.getDescription());

            } else if (Objects.equals(input.toLowerCase(), "bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                scn.close();
                return;
            } else if (Objects.equals(input.toLowerCase(), "list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    Task task = list.get(i - 1);
                    System.out.println(i + "." + "[" + task.getStatusIcon() + "] " + task.getDescription());
                }
            } else {
                Task task = new Task(input);
                list.add(task);
                System.out.println("added: " + input);
            }

        }
    }
}
