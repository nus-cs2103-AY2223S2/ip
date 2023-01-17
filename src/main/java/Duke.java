import java.util.Optional;
import java.util.Scanner;
import java.util.regex.*;

public class Duke {
    private static final Pattern MARK_UNMARK =
            Pattern.compile("(mark|unmark) [-+]?\\d+", Pattern.CASE_INSENSITIVE);
    private static final Pattern NUMBERS = Pattern.compile("[-+]?\\d+");

    private static final Pattern TASK = Pattern.compile("^(event|deadline|todo).*", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_TODO = Pattern.compile("^(todo) (.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_EVENT =
            Pattern.compile("^(event) (.+) /from (.+) /to (.+)$", Pattern.CASE_INSENSITIVE);
    private static final Pattern TASK_DEADLINE =
            Pattern.compile("^(deadline) (.+) /by (.+)$", Pattern.CASE_INSENSITIVE);

    private static final TaskMap<Task> taskMap = new TaskMap<>();
    private static final Scanner in = new Scanner(System.in);

    private void processTask(Matcher matcher, TaskType type, DukeException error) throws DukeException {
        if (matcher.find()) {
            String description = matcher.group(2);
            Task task;
            switch (type) {
                case TODO -> task = new Todo(description);
                case EVENT -> {
                    String from = matcher.group(3);
                    String to = matcher.group(4);
                    task = new Event(description, from, to);
                }
                case DEADLINE -> {
                    String by = matcher.group(3);
                    task = new Deadline(description, by);
                }
                default -> task = null;
            }
            if (task != null) taskMap.addTask(task);
        } else {
            throw error;
        }
    }


    public void begin()  {
        System.out.println("Hello! I'm Duke. What can i do for you?");
        System.out.println("""
                These are the available commands:
                 \t todo [description]
                 \t event [description] \\from [date] \\to [date]
                 \t deadline [description] \\by [date]
                 ----------------------------------------""");
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
                    // Checks for invalid taskNumber
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
            } else if (TASK.matcher(cmd).matches()) {
                Matcher mTodo = TASK_TODO.matcher(cmd);
                Matcher mEvent = TASK_EVENT.matcher(cmd);
                Matcher mDeadline = TASK_DEADLINE.matcher(cmd);

                if (cmd.startsWith(TaskType.TODO.getType())) {
                    try {
                        processTask(mTodo, TaskType.TODO,
                                new DukeException("The description is compulsory."));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (cmd.startsWith(TaskType.EVENT.getType())) {
                    try{
                        processTask(mEvent, TaskType.EVENT,
                                new DukeException("The description and date is compulsory."));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else if (cmd.startsWith(TaskType.DEADLINE.getType())) {
                    try {
                        processTask(mDeadline, TaskType.DEADLINE,
                                new DukeException("The description and date is compulsory."));
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                        System.out.println("☹ OOPS!!! It seems like you entered an unrecognized command.");
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
