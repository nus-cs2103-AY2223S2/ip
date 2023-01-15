import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

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
    enum Actions {LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE}

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
        System.out.println("\tNow you have " + taskList.size() + " task(s) in the list.\n" + lines);
    }

    public static void deleteTask(int taskId) {
        System.out.println(lines + "\tNoted. I've removed this task:");
        System.out.println("\t  " + taskList.get(taskId));
        System.out.println("\tNow you have " + (taskList.size() - 1) + " task(s) in the list.\n" + lines);
        taskList.remove(taskId);
    }

    public static void main(String[] args) {
        taskList = new ArrayList<>();
        System.out.println(greet);
        Scanner sc = new Scanner(System.in);
        Actions selection = null;
        String commands = sc.nextLine();
        String[] s = new String[] {};
        while (!commands.equals("bye")) {
            try {
                s = commands.split(" ");
                selection = Actions.valueOf(s[0].toUpperCase());
                switch (selection) {
                    case LIST:
                        printList();
                        break;
                    case MARK:
                        Task t1 = taskList.get(Integer.parseInt(s[1]) - 1);
                        t1.markDone();
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.println("\t  " + t1);
                        break;
                    case UNMARK:
                        Task t2 = taskList.get(Integer.parseInt(s[1]) - 1);
                        t2.markNotDone();
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        System.out.println("\t  " + t2);
                        break;
                    case TODO:
                        if (s.length < 2)
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        Todo todo = new Todo(commands.substring(5));
                        addTask(todo);
                        break;
                    case DEADLINE:
                        if (s.length < 2)
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        String[] deadlineInfo = commands.substring(9).split(" /by ");
                        if (deadlineInfo.length < 2)
                            throw new DukeException("☹ OOPS!!! Deadline cannot be empty.");
                        Deadline deadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                        addTask(deadline);
                        break;
                    case EVENT:
                        if (s.length < 2)
                            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                        String[] eventInfo = commands.substring(6).split(" /from ");
                        if (eventInfo.length < 2)
                            throw new DukeException("☹ OOPS!!! Event start time and end time are required.");
                        String[] eventTime = eventInfo[1].split(" /to ");
                        if (eventTime.length < 2)
                            throw new DukeException("☹ OOPS!!! Event start time and end time are required.");
                        Event event = new Event(eventInfo[0], eventTime[0], eventTime[1]);
                        addTask(event);
                        break;
                    case DELETE:
                        if (s.length < 2)
                            throw new DukeException("☹ OOPS!!! You must choose a task to delete");
                        int taskNumber = Integer.parseInt(s[1]);
                        if (taskNumber > taskList.size() || taskNumber <= 0)
                            throw new DukeException("☹ OOPS!!! No such task found");
                        deleteTask(taskNumber - 1);
                        break;
                    default:
                        System.out.println(lines + "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + lines);
                        break;
                }
            } catch (DukeException e) {
                System.out.println(lines + "\t" + e + "\n" + lines);
            } catch (IllegalArgumentException e) {
                System.out.println(lines + "\tPlease enter a valid action!\n" + lines);
            }
            commands = sc.nextLine();
        }
        System.out.println(bye);
    }
}
