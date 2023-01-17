import java.util.Optional;
import java.util.Scanner;
import java.util.regex.*;

public class Duke {
    private static final Pattern MARK_UNMARK = Pattern.compile("(mark|unmark) [-+]?\\d+", Pattern.CASE_INSENSITIVE);
    private static final Pattern NUMBERS = Pattern.compile("[-+]?\\d+");

    private static final Pattern TASK = Pattern.compile("^(event|deadline|todo) .+", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_TODO = Pattern.compile("^(todo) (.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_EVENT = Pattern.compile("^(event) (.+) /from (.+) /to (.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_DEADLINE = Pattern.compile("^(deadline) (.+) /by (.+)$", Pattern.CASE_INSENSITIVE);

    private static final TaskMap<Task> taskMap = new TaskMap<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello! I'm Duke. What can i do for you?");
        while (in.hasNext()) {
            String s = in.nextLine();
            String cmd = s.toLowerCase();
            int taskNumber;
            if (cmd.equalsIgnoreCase("bye")) {
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
                    if (cmd.toLowerCase().startsWith("mark")) {
                        task.get().markTask();
                    } else {
                        task.get().unmarkTask();
                    }
                }
            } else if (TASK.matcher(cmd).matches()) {
                Matcher mTodo = TASK_TODO.matcher(cmd);
                Matcher mEvent = TASK_EVENT.matcher(cmd);
                Matcher mDeadline = TASK_DEADLINE.matcher(cmd);

                if (mTodo.find()) {
                    String description = mTodo.group(2);
                    Task task = new Todo(description);
                    taskMap.addTask(task);
                } else if (mEvent.find()) {
                    String description = mEvent.group(2);
                    String from = mEvent.group(3);
                    String to = mEvent.group(4);
                    Task task = new Event(description, from, to);
                    taskMap.addTask(task);
                } else if (mDeadline.find()) {
                    String description = mDeadline.group(2);
                    String by = mDeadline.group(3);
                    Task task = new Deadline(description, by);
                    taskMap.addTask(task);
                } else {
                    System.out.println("Invalid input!");
                }
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
