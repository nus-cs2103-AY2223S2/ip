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

    private static String isMark(String s, TaskManager tm) throws DukeException {
        //checks if input is mark and throw exception for wrong format if matches
        if (s.length() >= 4 && Objects.equals(s.substring(0, 4), "mark")) {
            if (s.length() < 5) {
                throw new DukeException("Please provide a index to mark.");
            }else if (!Objects.equals(s.charAt(4), ' ')) {
                throw new DukeException("Please provide a spacing after the mark keyword.");
            }else if (s.length() < 6) {
                throw new DukeException("Please provide the index to mark.");
            }else if (!s.substring(5, s.length()).matches("[0-9]+")) {
                throw new DukeException("Please provide only positive integers for index to mark.");
            } else {
                return tm.mark(Integer.parseInt(s.substring(5, s.length())));
            }
        }
        return "";
    }

    private static String isUnmark(String s, TaskManager tm) throws DukeException {
        //checks if input is unmark and throw exception for wrong format if matches
        if (s.length() >= 6 && Objects.equals(s.substring(0, 6), "unmark")) {
            if (s.length() < 7) {
                throw new DukeException("Please provide a index to unmark.");
            }else if (!Objects.equals(s.charAt(6), ' ')) {
                throw new DukeException("Please provide a spacing after the unmark keyword");
            }else if (s.length() < 8) {
                throw new DukeException("Please provide the index to unmark.");
            }else if (!s.substring(7, s.length()).matches("[0-9]+")) {
                throw new DukeException("Please provide only positive integers for index to unmark.");
            } else {
                return tm.unmark(Integer.parseInt(s.substring(7, s.length())));
            }
        }
        return "";
    }

    private static String isTodo(String s, TaskManager tm) throws DukeException {
        //checks if input is todotask and throw exception for wrong format if matches
        if (s.length() >= 4 && Objects.equals(s.substring(0, 4), "todo")) {
            if (s.length() < 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }else if (!Objects.equals(s.charAt(4), ' ')) {
                throw new DukeException("Please provide a spacing after the todo keyword.");
            }else if (s.length() < 6) {
                throw new DukeException("The description of a todo cannot be empty.");
            } else {
                return tm.addTodo(s.substring(5, s.length()));
            }
        }
        return "";
    }

    private static String isDeadline(String s, TaskManager tm) throws DukeException {
        //checks if input is deadline task and throw exception for wrong format if matches
        if (s.length() >= 8 && Objects.equals(s.substring(0, 8), "deadline")) {
            if (s.length() < 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }else if (!Objects.equals(s.charAt(8), ' ')) {
                throw new DukeException("Please provide a spacing after the deadline keyword");
            }else if (s.length() < 10) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                s = s.substring(9, s.length());
                String[] parts = s.split("/");
                String dl = parts[1].substring(3, parts[1].length()).strip();
                String name = parts[0].strip();
                return tm.addDeadline(name, "by: " + dl);
            }
        }
        return "";
    }

    private static String isEvent(String s, TaskManager tm) throws DukeException {
        //check if input is event task and throw exception for wrong format if matches
        if (s.length() >= 5 && Objects.equals(s.substring(0, 5), "event")) {
            if (s.length() < 6) {
                throw new DukeException("The description of a event cannot be empty.");
            }else if (!Objects.equals(s.charAt(5), ' ')) {
                throw new DukeException("Please provide a spacing after the event keyword");
            }else if (s.length() < 7) {
                throw new DukeException("The description of a event cannot be empty.");
            } else {
                s = s.substring(6, s.length());
                String[] parts = s.split("/");
                String name = parts[0].strip();
                String from = parts[1].substring(5, parts[1].length()).strip();
                String to = parts[2].substring(3, parts[2].length()).strip();
                return tm.addEvent(name, String.format("from: %s to: %s", from, to));
            }
        }
        return "";
    }

    private static String isDelete(String s, TaskManager tm) throws DukeException {
        //checks if input is delete and throw exception for wrong format if matches
        if (s.length() >= 6 && Objects.equals(s.substring(0, 6), "delete")) {
            if (s.length() < 7) {
                throw new DukeException("Please provide a index to delete.");
            }else if (!Objects.equals(s.charAt(6), ' ')) {
                throw new DukeException("Please provide a spacing after the delete keyword.");
            }else if (s.length() < 8) {
                throw new DukeException("Please provide the index to delete.");
            }else if (!s.substring(7, s.length()).matches("[0-9]+")) {
                throw new DukeException("Please provide only positive integers for index to delete.");
            } else {
                return tm.deleteTask(Integer.parseInt(s.substring(7, s.length())));
            }
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println(Duke.intro);
        Scanner sc = new Scanner(System.in);
        TaskManager tm = new TaskManager();
        while (true) {
            try {
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
                    continue;
                }
                String isDelete = Duke.isDelete(str, tm);
                if (!Objects.equals(isDelete, "")){
                    System.out.println(isDelete);
                    continue;
                }
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }
}
