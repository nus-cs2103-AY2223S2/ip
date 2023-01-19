
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


            Task task;
            String[] cmd = input.split(" ");

            switch(cmd[0]) {
                case "bye":
                    flag_continue = false;
                    output.append(" Bye. Hope to see you again soon!\n");
                    break;
                case "list":
                    for(int i = 0; i < tasks.size(); i++) {
                        output.append(Integer.toString(i + 1)).append(".");
                        output.append(tasks.get(i).toString()).append("\n");
                    }
                    break;
                case "mark":
                    int n = Integer.parseInt(cmd[1]) - 1;
                    task = tasks.get(n);
                    output.append("Nice! I've marked this task as done:\n  ").append(task.mark()).append("\n");
                    tasks.set(n, task);
                    break;
                case "unmark":
                    n = Integer.parseInt(cmd[1]) - 1;
                    task = tasks.get(n);
                    output.append("OK, I've marked this task as not done yet:\n  ").append(task.unmark()).append("\n");
                    tasks.set(n, task);
                    break;
                default:
                    tasks.add(new Task(input));
                    output.append(input).append("\n");
                    break;
            }
            output.append(LINE);
            System.out.println(output);
        }
    }
}
