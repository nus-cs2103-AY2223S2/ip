import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static String intro = "    ____________________________________________________________\n" +
            "     Hello! I'm Duke\n" +
            "     What can I do for you?\n" +
            "    ____________________________________________________________\n";
    public static String outro = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________\n";

    public static void main(String[] args) {
        System.out.println(Duke.intro);
        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        while (true) {
            String str = sc.nextLine();
            if (Objects.equals(str, "bye")) {
                System.out.println(Duke.outro);
                break;
            } else if (Objects.equals(str, "list")) {
                System.out.println(tm.getAllTaskStr());
            } else {
                System.out.println(tm.addTask(str));
            }
        }
    }
}
