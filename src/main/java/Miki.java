import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

public class Miki {
    private static class MikiArgsException extends Exception {
        protected MikiArgsException(String message) {
            super(message);
        }
    }

    private static void printDiv() {
        System.out.println("    ____________________________________________________________________________");
    }

    private static void print(String s) {
        System.out.println("     " + s);
    }

    private static void printAdded(Task t, int taskCount) {
        print("Added this thing! That makes " + taskCount + (taskCount == 1 ? " task" : " tasks") + ":");
        print("  " + t.toString());
    }

    private static int parseIndex(String[] args, int taskCount) throws MikiArgsException {
        int idx;
        if (args.length == 0) throw new MikiArgsException("you didn't specify which one?!");
        try {
            idx = Integer.parseInt(args[0]) - 1;
        } catch (NumberFormatException ex) {
            throw new MikiArgsException("\"" + args[0] + "\" isn't a real integer! There's no task #" + args[0] + "!");
        }
        if (idx < 0 || idx >= taskCount) {
            String message = "There's no task #" + args[0] + "! ";
            if (taskCount == 1) message += "There is currently 1 task...";
            else message += "There are currently " + taskCount + " tasks...";
            throw new MikiArgsException(message);
        }
        return idx;
    }

    private static void printList(ArrayList<Task> tasks, String[] args) throws MikiArgsException {
        String from = "";
        String to = "";
        boolean token_from = false;
        boolean token_to = false;
        if (Collections.frequency(Arrays.asList(args), "/from") > 1) throw new MikiArgsException("too many filter-froms...");
        if (Collections.frequency(Arrays.asList(args), "/to") > 1) throw new MikiArgsException("too many filter-tos...");
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("/from")) {
                token_from = true;
                token_to = false;
                continue;
            }
            if (args[i].equals("/to")) {
                token_from = false;
                token_to = true;
                continue;
            }
            if (token_from) {
                from += (from.isEmpty() ? "" : " ") + args[i];
            } else if (token_to) {
                to += (to.isEmpty() ? "" : " ") + args[i];
            }
        }
        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;
        if (!from.isEmpty()) {
            try {
                fromDate = LocalDateTime.parse(from, Task.DATE_IN_FMT);
            } catch (DateTimeParseException ex) {
                throw new MikiArgsException(from + " needs to be formatted as " + Task.DATE_IN_FMT_STR + "!");
            }
        }
        if (!to.isEmpty()) {
            try {
                toDate = LocalDateTime.parse(to, Task.DATE_IN_FMT);
            } catch (DateTimeParseException ex) {
                throw new MikiArgsException(to + " needs to be formatted as " + Task.DATE_IN_FMT_STR + "!");
            }
        }
        for (int i = 0; i < tasks.size(); i++) {
            if ((fromDate == null || tasks.get(i).afterDate(fromDate))
                    && (toDate == null || tasks.get(i).beforeDate(toDate))) {
                print(Integer.toString(i + 1) + ". " + tasks.get(i));
            }
        }
    }

    public static void main(String[] args) {
        boolean ascii_only = false;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--ascii-only")) ascii_only = true;
        }

        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        printDiv();
        String username = System.getProperty("user.name");
        print("in honour / fuzuki miki / 2020 | 2021");
        if (!ascii_only) {
            print("\uD83C\uDF80✨");
            print("Hello " + username + " !! Konmiki! ＼(￣▽￣)/");
        } else {
            print("01 f3 80 / 27 28");
            print("Hello " + username + " !! Konmiki! \\(^v^)/");
        }
        printDiv();

        boolean exit_cmd = false;
        while (!exit_cmd) {
            System.out.print(">");
            String cmd_line = sc.nextLine();
            String cmd = cmd_line.split(" ")[0].toLowerCase();
            String[] cmd_args = {};
            if (cmd_line.contains(" ")) {
                cmd_args = cmd_line.substring(cmd.length() + 1).split(" ");
            }
            try {
                printDiv();
                switch (cmd) {
                    case "bye":
                        exit_cmd = true;
                        print("Otsumiki!~ I'll see you later!");
                        break;
                    case "list":
                        print("caught in 4k:");
                        printList(tasks, cmd_args);
                        break;
                    case "mark": {
                        int idx = parseIndex(cmd_args, tasks.size());
                        tasks.get(idx).mark();
                        print("Yay!! Task marked as done:");
                        print("  " + tasks.get(idx));
                        break;
                    }
                    case "unmark": {
                        int idx = parseIndex(cmd_args, tasks.size());
                        tasks.get(idx).unmark();
                        print("okay...! task unmarked as undone:");
                        print("  " + tasks.get(idx));
                        break;
                    }
                    case "todo": {
                        Todo newTodo = Todo.parseArgs(cmd_args);
                        tasks.add(newTodo);
                        printAdded(newTodo, tasks.size());
                        break;
                    }
                    case "deadline": {
                        Deadline newDeadline = Deadline.parseArgs(cmd_args);
                        tasks.add(newDeadline);
                        printAdded(newDeadline, tasks.size());
                        break;
                    }
                    case "event": {
                        Event newEvent = Event.parseArgs(cmd_args);
                        tasks.add(newEvent);
                        printAdded(newEvent, tasks.size());
                        break;
                    }
                    case "delete": {
                        int idx = parseIndex(cmd_args, tasks.size());
                        Task delTask = tasks.remove(idx);
                        print("hm hmm... task #" + (idx + 1) + " deleted! " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " left.");
                        print("- " + delTask);
                        break;
                    }
                    default:
                        throw new MikiArgsException("\"" + cmd + "\" isn't a real word!");
                }
            } catch (TaskParseException | MikiArgsException ex) {
                print("?!?!? " + ex.getMessage());
            }
            printDiv();
        }
    }
}
