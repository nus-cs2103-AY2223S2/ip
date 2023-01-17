import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Duke {

    private static final String LINE =
            " ".repeat(4) + "____________________________________________________________";
    private static final String INDENTATION = " ".repeat(5);

    private static final List<Task> addedTasks = new ArrayList<>();
    private static boolean isDone = false;

    private static void echo(String msg) {
        String displayedMsg = Arrays.stream(msg.split("\n")).map(line -> INDENTATION + line)
                .collect(Collectors.joining("\n"));
        System.out.println(LINE);
        System.out.println(displayedMsg);
        System.out.println(LINE);
    }

    private static String countTasks() {
        int n = addedTasks.size();
        return String.format("%d task%s", n, n < 2 ? "" : "s");
    }

    private static void addTask(Task task) {
        addedTasks.add(task);
        String displayedMsg =
                String.format("Got it. I've added this task:\n  %s\nNow you have %s in the list.",
                        task, countTasks());
        echo(displayedMsg);
    }

    private static void listTasks() {
        String displayedMsg =
                "Here are the tasks in your list:\n" + IntStream.range(0, addedTasks.size())
                        .mapToObj(i -> String.format("%d.%s", i + 1, addedTasks.get(i)))
                        .collect(Collectors.joining("\n"));
        echo(displayedMsg);
    }

    // Note that, indexes start from 1
    private static void markTaskAsDone(int index) {
        Task t = addedTasks.get(index - 1);
        t.markAsDone();
        echo("Nice! I've marked this task as done:\n  " + t);
    }

    private static void markTaskAsNotDone(int index) {
        Task t = addedTasks.get(index - 1);
        t.markAsNotDone();
        echo("OK, I've marked this task as not done yet:\n  " + t);
    }

    private static void deleteTask(int index) {
        Task t = addedTasks.remove(index - 1);
        String displayedMsg =
                String.format("Noted. I've removed this task:\n  %s\nNow you have %s in the list",
                        t, countTasks());
        echo(displayedMsg);

    }

    private static void exit() {
        isDone = true;
        echo("Bye. Hope to see you again soon!");
    }

    private static enum DukeCommand {
        BYE("bye"), LIST("list"), TODO("todo"), DEADLINE("deadline"), EVENT("event"), MARK(
                "mark"), UNMARK("unmark"), DELETE("delete");

        private final String keyword;

        private DukeCommand(String keyword) {
            this.keyword = keyword;
        }
    }

    // Will be moved to different file
    private static class DukeCommandParser {

        private static String input = null;
        private static int offset = 0;

        private static void setInput(String in) {
            input = in;
            offset = 0;
        }

        private static String munchUtilEnd() {
            String view = input.substring(offset);
            offset = input.length();
            return view.strip();
        }

        private static String munchUntil(String end) {
            int endIndex = input.indexOf(end, offset);
            if (endIndex < 0) {
                return null;
            }
            String view = input.substring(offset, endIndex);
            offset = endIndex + end.length();
            return view.strip();
        }

        private static boolean tryMunch(String prefix) {
            if (input.startsWith(prefix, offset)) {
                offset += prefix.length();
                return true;
            } else {
                return false;
            }
        }

        private static DukeCommand parseCommand() {
            for (DukeCommand command : DukeCommand.values()) {
                if (tryMunch(command.keyword)) {
                    return command;
                }
            }
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        private static String parseDescription(Supplier<String> parser) {
            String description = parser.get();
            if (description.isEmpty()) {
                throw new DukeException("Description cannot be empty");
            }
            return description;
        }

        private static String[] parseArgs(DukeCommand cmd) {
            List<String> args = new ArrayList<>();
            switch (cmd) {
            case LIST:
                break;
            case BYE:
                break;
            case TODO:
                args.add(parseDescription(DukeCommandParser::munchUtilEnd));
                break;
            case DEADLINE:
                args.add(parseDescription(() -> munchUntil("/by")));
                args.add(munchUtilEnd());
                break;
            case EVENT:
                args.add(parseDescription(() -> munchUntil("/from")));
                args.add(munchUntil("/to"));
                args.add(munchUtilEnd());
                break;
            case MARK:
                args.add(munchUtilEnd());
                break;
            case UNMARK:
                args.add(munchUtilEnd());
                break;
            case DELETE:
                args.add(munchUtilEnd());
                break;
            }
            return args.toArray(String[]::new);
        }
    }

    private static void executeDukeCommand(DukeCommand cmd, String... args) {
        switch (cmd) {
        case BYE:
            exit();
            break;
        case LIST:
            listTasks();
            break;
        case TODO:
            addTask(new Todo(args[0]));
            break;
        case DEADLINE:
            addTask(new Deadline(args[0], args[1]));
            break;
        case EVENT:
            addTask(new Event(args[0], args[1], args[2]));
            break;
        case MARK:
            markTaskAsDone(Integer.parseInt(args[0]));
            break;
        case UNMARK:
            markTaskAsNotDone(Integer.parseInt(args[0]));
            break;
        case DELETE:
            deleteTask(Integer.parseInt(args[0]));
            break;
        }

    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            echo("Hello! I'm Duke\nWhat can I do for you?");
            do {
                String in = sc.nextLine();
                DukeCommandParser.setInput(in);
                try {
                    DukeCommand cmd = DukeCommandParser.parseCommand();
                    String[] cmdArgs = DukeCommandParser.parseArgs(cmd);
                    executeDukeCommand(cmd, cmdArgs);
                } catch (DukeException de) {
                    echo(de.getMessage());
                } catch (Exception ex) {
                    echo("Oh no! Something is wrong with your command");
                }
            } while (!isDone);
        }
    }
}
