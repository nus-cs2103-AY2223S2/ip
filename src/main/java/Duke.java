import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class implements a personal assistant chatbot
 * that helps a person keep track of various things.
 *
 * @author Chia Jeremy
 */

public class Duke {

    private static final String LOGO
            = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final Feedback fb = new Feedback();

    private enum Command {
        TODO, DEADLINE, EVENT,
        MARK, UNMARK,
        LIST,
        HELP,
        BYE,
        INVALID
    }

    public static Command getCommand(String input) {
        String command = input.split(" ", 2)[0].toLowerCase();
        switch (command) {
            case "todo":
                return Command.TODO;
            case "deadline":
                return Command.DEADLINE;
            case "event":
                return Command.EVENT;
            case "mark":
                return Command.MARK;
            case "unmark":
                return Command.UNMARK;
            case "list":
                return Command.LIST;
            case "help":
                return Command.HELP;
            case "bye":
                return Command.BYE;
            default:
                return Command.INVALID;
        }
    }

    public static void addTask(ArrayList<Task> myTaskList, Task task) {
        myTaskList.add(task);
        fb.addedTask(myTaskList.size(), task);
    }

    public static void markTask(String input, ArrayList<Task> myTaskList, Boolean mark) {
        int index = 0;
        String cmd = mark ? "MARK" : "UNMARK";
        try {
            index = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = myTaskList.get(index);
            if (mark) {
                task.markDone();
                fb.markedTask(task);
            } else {
                task.unmarkDone();
                fb.unmarkedTask(task);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            fb.warning("The index of a " + cmd + " command cannot be empty.");
        } catch (NumberFormatException e) {
            fb.warning("The index of a " + cmd + " command requires a number.");
        } catch (IndexOutOfBoundsException e) {
            fb.warning("Task " + index + " do not exist.");
        }
    }

    private static void checkInput(Boolean isTrue, String errorMessage) throws DukeException {
        if (!isTrue) {
            throw new DukeException(errorMessage);
        }
    }

    public static void main(String[] args) {
        // Initialize components
        Scanner scn = new Scanner(System.in);
        ArrayList<Task> myTaskList = new ArrayList<>(100);

        fb.greet(LOGO);
        String input = scn.nextLine();
        Command command = getCommand(input);

        while (!command.equals(Command.BYE)) {
            String msg, descr;
            try {
                switch (command) {
                    case TODO:
                        msg = input.substring(4);
                        checkInput(!msg.isEmpty(), "The description of a TODO command cannot be empty.");
                        addTask(myTaskList, new Todo(msg));
                        break;

                    case DEADLINE:
                        msg = input.substring(8);
                        String by = " /by ";
                        checkInput(msg.contains(by), "The /by in a DEADLINE command cannot is missing.");

                        int byIndex = msg.indexOf(by);
                        descr = msg.substring(0, byIndex);
                        String dateTime = msg.substring(byIndex + by.length());
                        checkInput(!descr.isEmpty(), "The description of a DEADLINE command cannot be empty.");
                        checkInput(!dateTime.isEmpty(), "The datetime of a DEADLINE command cannot be empty.");

                        addTask(myTaskList, new Deadline(descr, dateTime));
                        break;

                    case EVENT:
                        msg = input.substring(5);
                        String from = " /from ";
                        String to = " /to ";
                        checkInput(msg.contains(from), "The /from in the EVENT command is missing.");
                        checkInput(msg.contains(to), "The /to in the EVENT command is missing.");

                        int fromIndex = msg.indexOf(from);
                        int toIndex = msg.indexOf(to);
                        descr = msg.substring(0, fromIndex);
                        String startDT = msg.substring(fromIndex + from.length(), toIndex);
                        String endDT = msg.substring(toIndex + to.length());
                        checkInput(!descr.isEmpty(), "The description of a EVENT command cannot be empty.");
                        checkInput(!startDT.isEmpty(), "The starting datetime of a EVENT command cannot be empty.");
                        checkInput(!endDT.isEmpty(), "The ending datetime of a EVENT command cannot be empty.");

                        addTask(myTaskList, new Event(descr, startDT, endDT));
                        break;

                    case MARK:
                        markTask(input, myTaskList, true);
                        break;

                    case UNMARK:
                        markTask(input, myTaskList, false);
                        break;

                    case LIST:
                        fb.listTask(myTaskList);
                        break;

                    case HELP:
                        fb.help();
                        break;

                    default:
                        fb.invalid();
                }
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }
            input = scn.nextLine();
            command = getCommand(input);
        }
        fb.exit();
        scn.close();
    }
}
