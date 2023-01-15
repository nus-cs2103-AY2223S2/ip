import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    final static String lines = "\t____________________________________________________________\n";
    final static String greet = lines +
            "\tHello! I'm Duke\n" +
            "\tWhat can I do for you?\n" +
            lines;
    final static String bye = lines +
            "\tBye. Hope to see you again soon!\n" +
            lines;
    static List<Task> taskList;

    public static void printList() {
        System.out.print(lines);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.print(lines);
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println(lines + "\tadded: " + task +"\n" + lines);
    }

    public static void main(String[] args) {
        taskList = new ArrayList<>();
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);

        String commands = sc.nextLine();
        while (!commands.equals("bye")) {
            if (commands.equals("list")) {
                printList();
            } else if (commands.contains("mark")) {
                String[] s = commands.split(" ");
                Task t = taskList.get(Integer.parseInt(s[1]) - 1);
                if (s[0].equals("mark")) {
                    t.markDone();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + t);
                } else if (s[0].equals("unmark")) {
                    t.markNotDone();
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.println("\t" + t);
                }
            } else {
                Task t = new Task(commands);
                addTask(t);
            }
            commands = sc.nextLine();
        }
        System.out.println(bye);
    }
}
