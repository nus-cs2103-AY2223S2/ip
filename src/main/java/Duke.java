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
            System.out.println(line);
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list");
                for (int i = 0; i < list.size(); i++) {
                    System.out.println((i + 1) + "." + list.get(i).toString());
                }
            } else if (command.equals("todo")) {
                try {
                    task = Todo.create(content);
                    list.add(task);
                    System.out.println("Ok boss. Added task:\n" + task.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }

            } else if (command.equals("deadline")) {
                try {
                    task = Deadline.create(content);
                    list.add(task);
                    System.out.println("Ok boss. Added task:\n" + task.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Invalid input for deadline.");
                }
            } else if (command.equals("event")) {
                try {
                    task = Event.create(content);
                    list.add(task);
                    System.out.println("Ok boss. Added task:\n" + task.toString());
                    System.out.println("Now you have " + list.size() + " in the list.");
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! Invalid input for event.");
                }
            } else if (command.equals("mark")) {
                if (content.length() < 2) {
                    System.out.println("☹ OOPS!!! Invalid input for mark command.");
                } else {
                    int index = Integer.parseInt(content.substring(1)) - 1;
                    if (index >= list.size() || index < 0) {
                        System.out.println("☹ OOPS!!! No such task in list.");
                    } else {
                        task = list.get(index);
                        task.markDone();
                        System.out.println("Ok boss! Marked this task as done: \n" + task.toString());
                    }
                }
            } else if (command.equals("unmark")) {
                if (content.length() < 2) {
                    System.out.println("☹ OOPS!!! Invalid input for unmark command.");
                } else {
                    int index = Integer.parseInt(content.substring(1)) - 1;
                    if (index >= list.size() || index < 0) {
                        System.out.println("☹ OOPS!!! No such task in list.");
                    } else {
                        task = list.get(Integer.parseInt(content.substring(1)) - 1);
                        task.unmarkDone();
                        System.out.println("Ok boss! Marked this task as not done yet: \n" + task.toString());
                    }
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println(line);
            command = scanner.next();
            content = scanner.nextLine();
        }

        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}