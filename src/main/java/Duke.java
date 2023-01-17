import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;

public class Duke {
    private static final Pattern MARK_UNMARK = Pattern.compile("(mark|unmark) \\d+", Pattern.CASE_INSENSITIVE);
    private static final Pattern NUMBERS = Pattern.compile("\\d+");
    private static final Map<Integer, Task> taskMap = new HashMap<>();
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
                taskMap.forEach((k, v) -> System.out.println(k + ". " + v));
            } else if (MARK_UNMARK.matcher(cmd).matches()) {
                Matcher m = NUMBERS.matcher(cmd);
                if (m.find()) {
                    taskNumber = Integer.parseInt(m.group());
                    if (taskNumber > taskMap.size()) {
                        System.out.println("This task don't exists! Please select one from the list.");
                        continue;
                    }
                    Task task = taskMap.get(taskNumber);
                    if (cmd.toLowerCase().startsWith("mark")) {
                        System.out.println(task.markTask());
                    } else {
                        System.out.println(task.unmarkTask());
                    }
                }
            } else {
                Task task = new Task(cmd);
                taskMap.put(taskMap.size() + 1, task);
                System.out.println("added: " + cmd);
            }

        }
    }
}
