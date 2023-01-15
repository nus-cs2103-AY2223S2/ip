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
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.print(lines);
    }

    public static void addTask(Task task) {
        taskList.add(task);
        System.out.println(lines + "\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.\n" + lines);
    }

    public static void main(String[] args) {
        taskList = new ArrayList<>();
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);

        String commands = sc.nextLine();
        while (!commands.equals("bye")) {
            String[] s = commands.split(" ");
            switch (s[0]) {
                case "list":
                    printList();
                    break;
                case "mark":
                    Task t1 = taskList.get(Integer.parseInt(s[1]) - 1);
                    t1.markDone();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + t1);
                    break;
                case "unmark":
                    Task t2 = taskList.get(Integer.parseInt(s[1]) - 1);
                    t2.markNotDone();
                    System.out.println("\tOK, I've marked this task as not done yet:");
                    System.out.println("\t" + t2);
                    break;
                case "todo":
                    Todo todo = new Todo(commands.substring(5));
                    addTask(todo);
                    break;
                case "deadline":
                    String[] deadlineInfo = commands.substring(9).split(" /by ");
                    Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    addTask(deadline);
                    break;
                case "event":
                    String[] eventInfo = commands.substring(6).split(" /from ");
                    String[] eventTime = eventInfo[1].split(" /to ");
                    Event event = new Event(eventInfo[0], eventTime[0], eventTime[1]);
                    addTask(event);
                    break;
                default:
                    System.out.println(lines + "\tSorry, I don't understand that.\n" + lines);
                    break;
            }
            commands = sc.nextLine();
        }
        System.out.println(bye);
    }
}
