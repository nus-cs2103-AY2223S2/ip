
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static String LINE = "____________________________________________________________\n";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + " Hello! I'm Duke\n What can I do for you?\n" + LINE);

        List<Task> tasks = new ArrayList<Task>();
        boolean flag_continue = true;
        while (flag_continue) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            StringBuilder output = new StringBuilder(LINE);
            String[] cmd = input.split(" ", 2);

            switch(cmd[0]) {
                case "bye":
                    flag_continue = false;
                    output.append(" Bye. Hope to see you again soon!\n");
                    break;
                case "list":
                    for(int i = 0; i < tasks.size(); i++) {
                        output.append(Integer.toString(i + 1)).append(".").append(tasks.get(i)).append("\n");
                    }
                    break;
                case "mark":
                    int n = Integer.parseInt(cmd[1]) - 1;
                    Task task = tasks.get(n);
                    task.mark();
                    tasks.set(n, task);
                    output.append("Nice! I've marked this task as done:\n  ").append(task).append("\n");
                    break;
                case "unmark":
                    n = Integer.parseInt(cmd[1]) - 1;
                    task = tasks.get(n);
                    task.unmark();
                    tasks.set(n, task);
                    output.append("OK, I've marked this task as not done yet:\n  ").append(task).append("\n");
                    break;
                case "todo":
                    task = new Todo(cmd[1]);
                    tasks.add(task);
                    output.append(task).append("\n");
                    output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                    break;
                case "deadline":
                    cmd = cmd[1].split(" /by ", 2);
                    task = new Deadline(cmd[0], cmd[1]);
                    tasks.add(task);
                    output.append(task).append("\n");
                    output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                    break;
                case "event":
                    cmd = cmd[1].split(" /from ", 2);
                    String[] time = cmd[1].split(" /to ", 2);
                    task = new Event(cmd[0], time[0], time[1]);
                    tasks.add(task);
                    output.append(task).append("\n");
                    output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                    break;
                default:
                    task = new Task(input);
                    tasks.add(task);
                    output.append(task).append("\n");
                    output.append("Now you have ").append(tasks.size()).append(" tasks in the list.\n");
                    break;
            }
            output.append(LINE);
            System.out.println(output);
        }
    }
}
