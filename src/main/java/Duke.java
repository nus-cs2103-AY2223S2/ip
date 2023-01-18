import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String line = "____________________________________________________________";
        System.out.println(line + "\nHello! I'm Duke\nWhat can I do for you?\n" + line);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(line);
                Task task;
                for (int i = 0; i < list.size(); i++) {
                    task = list.get(i);
                    System.out.println((i + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
                }

                System.out.println(line);
            } else if (input.length() < 6) {
                // add to list
                list.add(new Task(input));
                System.out.println(line + "\nadded: " + input + "\n" + line);
            } else if (input.substring(0, 4).equals("mark")) {
                // mark the task as done
                Task task = list.get(Integer.parseInt(input.substring(5)) - 1);
                task.markDone();
                System.out.println(line);
                System.out.println("Ok boss! Marked this task as done: \n   [" +
                        task.getStatusIcon() + "] " + task.getDescription());
                System.out.println(line);
            } else if (input.substring(0, 6).equals("unmark")){
                Task task = list.get(Integer.parseInt(input.substring(7)) - 1);
                task.unmarkDone();
                System.out.println(line);
                System.out.println("Ok boss! Marked this task as not done yet: \n   [" +
                        task.getStatusIcon() + "] " + task.getDescription());
                System.out.println(line);
            } else {
                // add to list
                list.add(new Task(input));
                System.out.println(line + "\nadded: " + input + "\n" + line);
            }

            input = scanner.nextLine();
        }
        System.out.println(line + "\nBye. Hope to see you again soon!\n" + line);
    }
}
