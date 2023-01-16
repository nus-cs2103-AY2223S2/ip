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

    private static String isMark(String s, TaskManager tm) {
        if (s.length() >= 6 && Objects.equals(s.substring(0, 5), "mark ") && s.substring(5, s.length()).matches("[0-9]+")) {
            return tm.mark(Integer.parseInt(s.substring(5, s.length())));
        }
        return "";
    }

    private static String isUnmark(String s, TaskManager tm) {
        if (s.length() >= 8 && Objects.equals(s.substring(0, 7), "unmark ") && s.substring(7, s.length()).matches("[0-9]+")) {
            return tm.unmark(Integer.parseInt(s.substring(7, s.length())));
        }
        return "";
    }

    private static String isTodo(String s, TaskManager tm) {
        if (s.length() >= 6 && Objects.equals(s.substring(0, 5), "todo ")) {
            return tm.addTodo(s.substring(5, s.length()));
        }
        return "";
    }

    private static String isDeadline(String s, TaskManager tm) {
        if (s.length() >= 10 && Objects.equals(s.substring(0, 9), "deadline ")) {
            String[] parts = s.split("/");
            String dl = parts[1].substring(3, parts[1].length()).strip();
            String name = parts[0].strip();
            return tm.addDeadline(name, "by: " + dl);
        }
        return "";
    }

    private static String isEvent(String s, TaskManager tm) {
        if (s.length() >= 7 && Objects.equals(s.substring(0, 6), "event ")) {
            String[] parts = s.split("/");
            String name = parts[0].strip();
            String from = parts[1].substring(5, parts[1].length()).strip();
            String to = parts[2].substring(3, parts[2].length()).strip();
            return tm.addEvent(name, String.format("from: %s to: %s", from, to));
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(Duke.intro);
        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        while (true) {
            String str = sc.nextLine();
            if (Objects.equals(str, "bye")) {
                System.out.println(Duke.outro);
                break;
            }
            if (Objects.equals(str, "list")) {
                System.out.println(tm.getAllTaskStr());
                continue;
            }
            String ismark = Duke.isMark(str, tm);
            if (!Objects.equals(ismark, "")) {
                System.out.println(ismark);
                continue;
            }
            String isUnmark = Duke.isUnmark(str, tm);
            if (!Objects.equals(isUnmark, "")) {
                System.out.println(isUnmark);
                continue;
            }
            String isTodo = Duke.isTodo(str, tm);
            if (!Objects.equals(isTodo, "")) {
                System.out.println(isTodo);
                continue;
            }
            String isDeadline = Duke.isDeadline(str, tm);
            if (!Objects.equals(isDeadline, "")) {
                System.out.println(isDeadline);
                continue;
            }
            String isEvent = Duke.isEvent(str, tm);
            if (!Objects.equals(isEvent, "")) {
                System.out.println(isEvent);
            }
        }
    }
}
