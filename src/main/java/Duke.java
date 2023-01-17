import java.util.Optional;
import java.util.Scanner;
import java.util.regex.*;

public class Duke {
    private final Pattern MARK_UNMARK = Pattern.compile("(mark|unmark) [-+]?\\d+", Pattern.CASE_INSENSITIVE);

    private final Pattern DELETE = Pattern.compile("(delete) [-+]?\\d+", Pattern.CASE_INSENSITIVE);
    private static final Pattern NUMBERS = Pattern.compile("[-+]?\\d+");

    private static final Pattern TASK = Pattern.compile("^(event|deadline|todo).*", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_TODO = Pattern.compile("^(todo) (.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_EVENT = Pattern.compile("^(event) (.+) /from (.+) /to (.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_DEADLINE = Pattern.compile("^(deadline) (.+) /by (.+)$", Pattern.CASE_INSENSITIVE);

    private static final TaskMap<Task> taskMap = new TaskMap<>();
    private static final Scanner in = new Scanner(System.in);

    public void begin()  {
        System.out.println("Hello! I'm Duke. What can i do for you?");
        System.out.println("These are the available commands:" +
                "\n\t todo [description]" +
                "\n\t event [description] \\from [date] \\to [date]" +
                "\n\t deadline [description] \\by [date]" +
                "\n----------------------------------------");
        while (in.hasNext()) {
            String cmd = in.nextLine().toLowerCase();
            int taskNumber;
            if (cmd.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye!");
                break;
            } else if (cmd.equalsIgnoreCase("list")) {
                System.out.println(taskMap);
            } else if (MARK_UNMARK.matcher(cmd).matches()) {
                Matcher m = NUMBERS.matcher(cmd);
                if (m.find()) {
                    taskNumber = Integer.parseInt(m.group());
                    Optional<Task> task = taskMap.getTask(taskNumber);
                    if (task.isEmpty()) {
                        continue;
                    }
                    if (cmd.startsWith("mark")) {
                        task.get().markTask();
                    } else {
                        task.get().unmarkTask();
                    }
                }
            } else if (DELETE.matcher(cmd).matches()) {
                Matcher m = NUMBERS.matcher(cmd);
                if (m.find()) {
                    taskNumber = Integer.parseInt(m.group());
                    taskMap.deleteTask(taskNumber);
                }
            } else if (TASK.matcher(cmd).matches()) {
                Matcher mTodo = TASK_TODO.matcher(cmd);
                Matcher mEvent = TASK_EVENT.matcher(cmd);
                Matcher mDeadline = TASK_DEADLINE.matcher(cmd);

                try {
                    if (cmd.startsWith(TaskType.TODO.getType())) {
                        Task.processTask(taskMap, mTodo, TaskType.TODO);
                    } else if (cmd.startsWith(TaskType.EVENT.getType())) {
                        Task.processTask(taskMap, mEvent, TaskType.EVENT);
                    } else if (cmd.startsWith(TaskType.DEADLINE.getType())) {
                        Task.processTask(taskMap, mDeadline, TaskType.DEADLINE);
                    } else {
                        System.out.println("☹ OOPS!!! It seems like you entered an unrecognized command.");
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.begin();
    }
}
