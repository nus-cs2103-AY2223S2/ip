import java.util.Scanner;

public class Saturday {
    private static boolean isActive = true;
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        // Greeting
        Utils.divider();
        Utils.output("Hello! I'm Saturday\n\tWhat can I do for you?");
        Utils.divider();
        Utils.newline();

        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            String input = scanner.nextLine();
            Utils.divider();
            try {
                Command command = Command.getCommand(input);
                command.execute(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            Utils.divider();
            Utils.newline();
        }
    }

    public static void todo(String text) {
        int s = text.indexOf(" ");
        if (s != -1) {
            String description = text.substring(s + 1);
            ToDo task = new ToDo(description);
            taskList.addTask(task);
        }
    }

    public static void deadline(String text) {
        int s = text.indexOf(" ");
        int d = text.indexOf("/by");
        if (s != -1 && d != -1 && d > s) {
            String description = text.substring(s + 1, d - 1);
            String deadline = text.substring(d + 4);
            Deadline task = new Deadline(description, deadline);
            taskList.addTask(task);
        } else {
            throw new IllegalArgumentException("Please specify the deadline");
        }
    }

    public static void event(String text) {
        int s = text.indexOf(" ");
        int f = text.indexOf("/from");
        int t = text.indexOf("/to");
        if (s != -1 && f != -1 && t != -1 && t > f && f > s) {
            String description = text.substring(s + 1, f - 1);
            String from = text.substring(f + 6, t - 1);
            String to = text.substring(t + 4);
            Event task = new Event(description, from, to);
            taskList.addTask(task);
        } else {
            throw new IllegalArgumentException("Please specify the timeframe");
        }
    }

    public static void displayList() {
        Utils.output(taskList.toString());
    }

    public static void mark(int i) {
        taskList.mark(i);
    }

    public static void unMark(int i) {
        taskList.unMark(i);
    }

    public static void exit() {
        isActive = false;
        Utils.output("Bye. Hope to see you again soon!");
    }

}
