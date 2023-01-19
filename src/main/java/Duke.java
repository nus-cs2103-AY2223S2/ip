import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
        String command = scanner.next();
        String content = scanner.nextLine();
        Task task;
        while (!command.equals("bye")) {
            if (command.equals("list")) {
                System.out.println(line);
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
                System.out.println(line);
            } else if (command.equals("todo")) {
                task = Todo.create(content.substring(1));
                list.add(task);
                System.out.println(line);
                System.out.println("Ok boss. Added task:\n" + task.toString());
                System.out.println("Now you have " + list.size() + " in the list.");
                System.out.println(line);
            } else if (command.equals("deadline")) {
                task = Deadline.create(content.substring(1));
                list.add(task);
                System.out.println(line);
                System.out.println("Ok boss. Added task:\n" + task.toString());
                System.out.println("Now you have " + list.size() + " in the list.");
                System.out.println(line);
            } else if (command.equals("event")) {
                task = Event.create(content.substring(1));
                list.add(task);
                System.out.println(line);
                System.out.println("Ok boss. Added task:\n" + task.toString());
                System.out.println("Now you have " + list.size() + " in the list.");
                System.out.println(line);
            } else if (command.equals("mark")) {
                task = list.get(Integer.parseInt(content.substring(1)) - 1);
                task.markDone();
                System.out.println(line);
                System.out.println("Ok boss! Marked this task as done: \n" + task.toString());
                System.out.println(line);
            } else if (command.equals("unmark")) {
                task = list.get(Integer.parseInt(content.substring(1)) - 1);
                task.unmarkDone();
                System.out.println(line);
                System.out.println("Ok boss! Marked this task as not done yet: \n" + task.toString());
                System.out.println(line);
            }

            command = scanner.next();
            content = scanner.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}