import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("<コ:彡");
        System.out.println("Hello! I'm Duke, your favourite pink octopus.");
        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        String input = sc.nextLine();

        while (!input.equals("bye")) {
            System.out.println("(\\ (\\\n" +
                    "(„• ֊ •„) ♡\n" +
                    "━O━O━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            if (input.startsWith("mark")) {
                int num = Integer.parseInt(input.split(" ")[1]);
                if (num > tasks.size()) {
                    System.out.println("Huh? You don't have that many things in your list! :(");
                } else {
                    Task t = tasks.get(num - 1);
                    if (t.isDone()) {
                        System.out.println("Huh? You've already done this task!");
                    } else {
                        t.mark();
                        System.out.println("Okie! I've marked this task as done:");
                        System.out.println(t);
                    }
                }
            } else if (input.startsWith("unmark")) {
                int num = Integer.parseInt(input.split(" ")[1]);
                if (num > tasks.size()) {
                    System.out.println("Huh? You don't have that many things in your list! :(");
                } else {
                    Task t = tasks.get(num - 1);
                    if (!t.isDone()) {
                        System.out.println("Huh? You haven't even done this task!");
                    } else {
                        t.unmark();
                        System.out.println("Okie! I've marked this task as not done yet:");
                        System.out.println(t);
                    }
                }
            } else if (input.equals("list")) {
                System.out.println("Here are all the things on your list!");
                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    System.out.println(String.format("%s.%s", i + 1, t));
                }
            } else { // task
                Task t;
                if (input.startsWith("todo")) {
                    t = new ToDo(input.substring(5));
                } else if (input.startsWith("deadline")) {
                    t = new Deadline(input.substring(9));
                } else {
                    t = new Event(input.substring(6));
                }
                tasks.add(t);
                System.out.println("Alright! I've added this task:");
                System.out.println(t);
                if (tasks.size() == 1) { // grammar
                    System.out.println("Now you have 1 task on your list.");
                } else {
                    System.out.println(String.format("Now you have %s tasks on your list.", tasks.size()));
                }
            }
            input = sc.nextLine();
        }

        System.out.println("Bye bye :( Hope to see you again soon!");
        sc.close();

    }
}