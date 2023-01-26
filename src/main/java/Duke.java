import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected final static String OUTPUT_DATE_PATTERN = "MMM dd yyyy HHmm";
    protected final static String INPUT_DATE_PATTERN = "yyyy-mm-dd HHmm";
    private final static String BYE = "bye";
    private final static String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private final static String LIST = "list";
    private final static String MARK = "mark";
    private final static String UNMARK = "unmark";
    private final static String DELETE = "delete";
    private static ArrayList<Task> tasks = new ArrayList<>();

    private static void indentedPrintln(String message) {
        String indentedMessage = "     " + message;
        System.out.println(indentedMessage);
    }

    private static void list() {
        indentedPrintln("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            indentedPrintln(i + "." + tasks.get(i - 1));
        }
    }

    private static void mark(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        String message = "Nice! I've marked this task as done:";
        indentedPrintln(message);
        indentedPrintln("  " + task);
    }

    private static void unmark(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        String message = "OK, I've marked this task as not done yet:";
        indentedPrintln(message);
        indentedPrintln("  " + task);
    }

    private static boolean isValidTask(String command) {
        if (command.length() >= 4 && command.substring(0, 4).equals("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return true;
        } else if (command.length() >= 8 && command.substring(0, 8).equals("deadline")) {
            if (command.length() <= 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            return true;
        } else if (command.length() >= 5 && command.substring(0, 5).equals("event")) {
            if (command.length() <= 6) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            return true;
        }
        return false;
    }

    private static void addTask(String command) {
        int len = command.length();
        String description;
        Task newTask;
        if (command.substring(0, 4).equals("todo")) {
            description = command.substring(5);
            newTask = new Todo(description);
        } else if (command.substring(0, 8).equals("deadline")) {
            int indexOfBy = -1;
            for (int i = 0; i < len; i++) {
                if (command.charAt(i) == '/') {
                    indexOfBy = i;
                }
            }
            description = command.substring(9, indexOfBy - 1);
            String by = command.substring(indexOfBy + 4);
            newTask = new Deadline(description, parseDateTime(by));
        } else {
            int indexOfStart = -1, indexOfEnd = -1;
            for (int i = 0; i < len; i++) {
                if (command.charAt(i) == '/') {
                    if (indexOfStart == -1) {
                        indexOfStart = i;
                    } else {
                        indexOfEnd = i;
                    }
                }
            }
            description = command.substring(6, indexOfStart - 1);
            String start = command.substring(indexOfStart + 6, indexOfEnd - 1);
            String end = command.substring(indexOfEnd + 4);
            newTask = new Event(description, parseDateTime(start), parseDateTime(end));
        }
        tasks.add(newTask);
        indentedPrintln("Got it. I've added this task:");
        indentedPrintln("  " + newTask);
        indentedPrintln("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        indentedPrintln("Noted. I've removed this task:");
        indentedPrintln("  " + task);
        indentedPrintln("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static LocalDateTime parseDateTime(String s) {
        return LocalDateTime.parse(s, DateTimeFormatter.ofPattern(INPUT_DATE_PATTERN));
    }

    protected static String getDateTimeOutput(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_PATTERN));
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
        while (!str.equals(BYE)) {
            try {
                if (str.equals(LIST)) {
                    list();
                } else if (str.length() >= 4 && str.substring(0, 4).equals(MARK)) {
                    mark(Character.getNumericValue(str.charAt(5)));
                } else if (str.length() >= 6 && str.substring(0, 6).equals(UNMARK)) {
                    unmark(Character.getNumericValue(str.charAt(7)));
                } else if (isValidTask(str)) {
                    addTask(str);
                } else if (str.length() >= 6 && str.substring(0, 6).equals(DELETE)) {
                    deleteTask(Character.getNumericValue(str.charAt(7)));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println("     " + e);
            } finally {
                System.out.println();
                str = sc.nextLine();
            }
        }
        indentedPrintln(GOODBYE_MESSAGE);
    }
}
