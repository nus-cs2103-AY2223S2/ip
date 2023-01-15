import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String divider = "=============================================";
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void displayList() {
        System.out.println("Here are the tasks in your list currently:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i));
        }
        System.out.println(divider);
    }

    public static Task getTask(String command) {
        Task task = tasks.get(Integer.parseInt(command.split(" ")[1]) - 1);
        return task;
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Oink! I've added the following task:");
        System.out.println("> "+ task.toString());
        printSummary();
        System.out.println(divider);
    }

    public static void markDone(Task task) {
        task.setDone(true);
        System.out.println("Oink! I've marked this task as done: ");
        System.out.println("> " + task.toString());
        System.out.println(divider);
    }

    public static void unmarkDone(Task task) {
        task.setDone(false);
        System.out.println("Oink! I've marked this task as undone: ");
        System.out.println("> " + task.toString());
        System.out.println(divider);
    }

    public static void printSummary() {
        System.out.println("You now have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    public static void printIntroduction() {
        String logo = " ____  ____  ____  ____   __\n"
                + "(  _ \\(  __)(  _ \\(  _ \\ / _\\\n"
                + " ) __/ ) _)  ) __/ ) __//    \\\n"
                + "(__)  (____)(__)  (__)  \\_/\\_/\n";
        String art = "       _\n"
                + "  <`--'\\>______\n"
                + "  /. .  `'     \\\n"
                + " (`')  ,        @\n"
                + "  `-._,        /\n"
                + "     )-)_/--( >  jv\n"
                + "    ''''  ''''\n";
        System.out.println(divider);
        System.out.println("Oink! I'm \n" + logo + art);
        System.out.println("Nice to meet you! How can I assist you today?");
        System.out.println(divider);
    }

    public static void insertTask(String command) {
        String task = command.split(" ")[0];
        int descStartIndex = command.indexOf(" ") + 1;
        if (task.equals("todo")) {
            String desc = command.substring(descStartIndex);
            addTask(new Todo(desc));
        } else {
            int descEndIndex = command.indexOf("/") - 1;
            String desc = command.substring(descStartIndex, descEndIndex);
            if (task.equals("event")) {
                int fromStartIndex = command.indexOf("/") + 6;
                int fromEndIndex = command.indexOf("/to") - 1;
                int toStartIndex = command.indexOf("/to") + 4;
                String from = command.substring(fromStartIndex, fromEndIndex);
                String to = command.substring(toStartIndex);
                addTask(new Event(desc, from, to));
            } else {
                int dueStartIndex = command.indexOf("/") + 4;
                String due = command.substring(dueStartIndex);
                addTask(new Deadline(desc, due));
            }
        }
    }

    public static void main(String[] args) {
        printIntroduction();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println("Oink oink! See you again :)");
                sc.close();
                break;
            } else if (command.equals("list")) {
                displayList();
            } else if (command.startsWith("mark")) {
                markDone(getTask(command));
            } else if (command.startsWith("unmark")) {
                unmarkDone(getTask(command));
            } else {
                insertTask(command);
            }
        }
    }
}

