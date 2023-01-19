
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

        int n = 0;
        String[] task;
        List<String[]> tasks = new ArrayList<String[]>();

        boolean flag_continue = true;
        while (flag_continue) {
            Scanner sc = new Scanner(System.in);
            String input = sc.next();
            switch(input) {
                case "bye":
                    flag_continue = false;
                    break;
                case "list":
                    String output = "";
                    for(int i = 0; i < tasks.size(); i++) {
                        output += Integer.toString(i + 1) + ".[" + tasks.get(i)[1] + "] " + tasks.get(i)[0] + "\n";
                    }
                    System.out.println(LINE + output + LINE);
                    break;
                case "mark":
                    n = sc.nextInt() - 1;
                    task = tasks.get(n);
                    task[1] = "X";
                    tasks.set(n, task);
                    System.out.println(LINE + "Nice! I've marked this task as done:\n  [X] " + task[0] + "\n" + LINE);
                    break;
                case "unmark":
                    n = sc.nextInt() - 1;
                    task = tasks.get(n);
                    task[1] = " ";
                    tasks.set(n, task);
                    System.out.println(LINE + "OK, I've marked this task as not done yet:\n  [ ] " + task[0] + "\n" + LINE);
                    break;
                default:
                    String[] cmd = {input, " "};
                    tasks.add(cmd);
                    System.out.println(LINE + input + "\n" + LINE);
                    break;
            }
        }

        System.out.println(LINE + " Bye. Hope to see you again soon!\n" + LINE);
    }
}
