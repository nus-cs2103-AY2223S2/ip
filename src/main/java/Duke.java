import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String bye = "bye";
    private final static String goodbyeMessage = "Bye. Hope to see you again soon!";
    private final static String list = "list";
    private final static String mark = "mark";
    private final static String unmark = "unmark";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void indentedPrintln(String message) {
        String indentedMessage = "     " + message;
        System.out.println(indentedMessage);
    }

    private static void list() {
        indentedPrintln("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            indentedPrintln(i + ".[" + tasks.get(i - 1).getStatusIcon() + "] "
                    + tasks.get(i - 1));
        }
    }

    private static void mark(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        String message = "Nice! I've marked this task as done:";
        indentedPrintln(message);
        indentedPrintln("  [" + task.getStatusIcon() + "] "
                + task);
    }

    private static void unmark(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        String message = "OK, I've marked this task as not done yet:";
        indentedPrintln(message);
        indentedPrintln("  [" + task.getStatusIcon() + "] "
                + task);
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals(bye)) {
            if (str.equals(list)) {
                list();
            } else if (str.substring(0, 4).equals(mark)) {
                mark(Character.getNumericValue(str.charAt(5)));
            } else if (str.substring(0, 6).equals(unmark)) {
                unmark(Character.getNumericValue(str.charAt(7)));
            } else {
                tasks.add(new Task(str));
                indentedPrintln("added: " + str);
            }
            System.out.println();
            str = sc.nextLine();
        }
        indentedPrintln(goodbyeMessage);
    }
}
