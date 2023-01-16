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

    private static String editStatus(String s, TaskManager tm) {
        if (s.length() >= 6 && Objects.equals(s.substring(0, 5), "mark ") && s.substring(5, s.length()).matches("[0-9]+")) {
            return tm.mark(Integer.parseInt(s.substring(5, s.length())));
        } else if (s.length() >= 8 && Objects.equals(s.substring(0, 7), "unmark ") && s.substring(7, s.length()).matches("[0-9]+")) {
            return tm.unmark(Integer.parseInt(s.substring(7, s.length())));
        } else {
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(Duke.intro);
        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        while (true) {
            String str = sc.nextLine();
            String editStatus = Duke.editStatus(str, tm);
            if (Objects.equals(str, "bye")) {
                System.out.println(Duke.outro);
                break;
            } else if (Objects.equals(str, "list")) {
                System.out.println(tm.getAllTaskStr());
            } else if (Objects.equals(editStatus, "")){
                System.out.println(tm.addTask(str));
            } else {
                System.out.println(editStatus);
            }
        }
    }
}
