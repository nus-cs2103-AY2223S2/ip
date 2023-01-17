import java.util.*;

import Tasks.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------------------------");

        ArrayList<Task> list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String in = sc.nextLine();
            System.out.println("----------------------------------------");
            String[] split = in.split(" ", 2);

            if (in.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (in.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    Task curr = list.get(i);
                    System.out.println((i + 1) + "." + curr);
                }
            } else if (split[0].equals("mark")) {
                Integer i = Integer.parseInt(split[1]);
                Task task = list.get(i-1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (split[0].equals("unmark")) {
                Integer i = Integer.parseInt(split[1]);
                Task task = list.get(i-1);
                task.markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (split[0].equals("todo")) {
                list.add(new Todo(split[1]));
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(list.size()-1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (split[0].equals("deadline")) {
                String[] tokens = split[1].split(" /by ", 2);
                list.add(new Deadline(tokens[0], tokens[1]));
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(list.size()-1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (split[0].equals("event")) {
                String[] tokens = split[1].split(" /from ", 2);
                String[] tokens2 = tokens[1].split(" /to ", 2);
                list.add(new Event(tokens[0], tokens2[0], tokens2[1] ));
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(list.size()-1));
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else {
                System.out.println("Sorry, I don't understand that command.");
            }
            System.out.println("----------------------------------------");
        }
        sc.close();
    }
}
