
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

        List<String> tasks = new ArrayList();
        boolean flag_continue = true;
        while (flag_continue) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            switch(command) {
                case "bye":
                    flag_continue = false;
                    break;
                case "list":
                    String output = "";
                    for(int i = 0; i < tasks.size(); i++) {
                        output += Integer.toString(i + 1) + ". " + tasks.get(i) + "\n";
                    }
                    System.out.println(LINE + output + LINE);
                    break;
                default:
                    tasks.add(command);
                    System.out.println(LINE + command + "\n" + LINE);
                    break;
            }
        }

        System.out.println(LINE + " Bye. Hope to see you again soon!\n" + LINE);
    }
}
