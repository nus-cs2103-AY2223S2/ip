import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {

        System.out.println("<コ:彡");
        System.out.println("Hello! I'm Duke, your favourite pink octopus.");
        System.out.println("What can I do for you today?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                Duke.quit();
                sc.close();
                break;
            }
            System.out.println("(\\ (\\\n" +
                    "(„• ֊ •„) ♡\n" +
                    "━O━O━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            if (input.startsWith("mark")) {
                Duke.mark(tasks, Integer.parseInt(input.split(" ")[1]));
            } else if (input.startsWith("unmark")) {
                Duke.unmark(tasks, Integer.parseInt(input.split(" ")[1]));
            } else if (input.equals("list")) {
                Duke.list(tasks);
            } else if (input.startsWith("todo")) {
                Duke.addToDo(tasks, input);
            } else if (input.startsWith("deadline")) {
                Duke.addDeadline(tasks, input);
            } else if (input.startsWith("event")) {
                Duke.addEvent(tasks, input);
            } else { // error
                throw new DukeInvalidCommandException();
            }
        }

        sc.close();

    }

    static void quit() {
        System.out.println("Bye bye :( Hope to see you again soon!");
    }

    static void mark(ArrayList<Task> tasks, int num) {
        if (num > tasks.size()) { // error
            System.out.println("Huh? You don't have that many things in your list! :(");
        } else {
            Task t = tasks.get(num - 1);
            if (t.isDone()) { // error
                System.out.println("Huh? You've already done this task!");
            } else {
                t.mark();
                System.out.println("Okie! I've marked this task as done:");
                System.out.println(t);
            }
        }
    }

    static void unmark(ArrayList<Task> tasks, int num) {
        if (num > tasks.size()) { // error
            System.out.println("Huh? You don't have that many things in your list! :(");
        } else {
            Task t = tasks.get(num - 1);
            if (!t.isDone()) { // error
                System.out.println("Huh? You haven't even done this task!");
            } else {
                t.unmark();
                System.out.println("Okie! I've marked this task as not done yet:");
                System.out.println(t);
            }
        }
    }

    static void list(ArrayList<Task> tasks) {
        System.out.println("Here are all the things on your list!");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            System.out.println(String.format("%s.%s", i + 1, t));
        }
    }

    static void addToDo(ArrayList<Task> tasks, String input) {
        Duke.addTask(tasks, new ToDo(input.substring(5)));
    }

    static void addDeadline(ArrayList<Task> tasks, String input) { // potential error: no /by
        Duke.addTask(tasks, new Deadline(input.substring(9)));
    }

    static void addEvent(ArrayList<Task> tasks, String input) { // potential error: no /from or /to
        Duke.addTask(tasks, new Event(input.substring(6)));
    }

    static void addTask(ArrayList<Task> tasks, Task t) {
        tasks.add(t);
        System.out.println("Alright! I've added this task:");
        System.out.println(t);
        if (tasks.size() == 1) { // grammar
            System.out.println("Now you have 1 task on your list.");
        } else {
            System.out.println(String.format("Now you have %s tasks on your list.", tasks.size()));
        }
    }
}