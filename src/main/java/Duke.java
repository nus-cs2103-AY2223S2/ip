import java.util.Scanner;

/**
 * The Duke class implements a personal assistant chatbot
 * that helps a person keep track of various things.
 *
 * @author Chia Jeremy
 */

public class Duke {

    private static final String LOGO
            = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private enum Command {
        TODO, DEADLINE, EVENT,
        DELETE,
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
            case "delete":
                return Command.DELETE;
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

    public static void deleteOrMark(String cmd, TaskList taskList, String input) {
        int index = 0;
        try {
            index = Integer.parseInt(input.split(" ")[1]);
            switch (cmd) {
                case "DELETE":
                    taskList.deleteTask(index - 1);
                    break;
                case "MARK":
                    taskList.markTask(index - 1);
                    break;
                case "UNMARK":
                    taskList.unmarkTask(index - 1);
                    break;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            Feedback.warning("The index of a " + cmd + " command cannot be empty.");
        } catch (NumberFormatException e) {
            Feedback.warning("The index of a " + cmd + " command requires a number.");
        } catch (IndexOutOfBoundsException e) {
            Feedback.warning("Task " + index + " do not exist.");
        }
    }

    private static void checkExist(Boolean isTrue, String errorMessage) throws DukeException {
        if (!isTrue) {
            throw new DukeException(errorMessage);
        }
    }

    public static void main(String[] args) {
        // Initialize components
        Scanner scn = new Scanner(System.in);
        TaskList myTaskList = new TaskList(100);

        Feedback.greet(LOGO);
        String input = scn.nextLine();
        Command cmd = getCommand(input);

        while (!cmd.equals(Command.BYE)) {
            String msg, descr;
            try {
                switch (cmd) {
                    case TODO:
                        msg = input.substring(4).trim();
                        checkExist(!msg.isBlank(), "The description of a TODO command cannot be empty.");
                        myTaskList.addTask(new Todo(msg));
                        break;

                    case DEADLINE:
                        msg = input.substring(8).trim();
                        String by = "/by";
                        checkExist(msg.contains(by), "The /by in the DEADLINE command is missing.");

                        int byIndex = msg.indexOf(by);
                        descr = msg.substring(0, byIndex).trim();
                        String dateTime = msg.substring(byIndex + by.length()).trim();
                        checkExist(!descr.isBlank(), "The description of a DEADLINE command cannot be empty.");
                        checkExist(!dateTime.isBlank(), "The datetime of a DEADLINE command cannot be empty.");

                        myTaskList.addTask(new Deadline(descr, dateTime));
                        break;

                    case EVENT:
                        msg = input.substring(5).trim();
                        String from = "/from";
                        String to = "/to";
                        checkExist(msg.contains(from), "The /from in the EVENT command is missing.");
                        checkExist(msg.contains(to), "The /to in the EVENT command is missing.");

                        int fromIndex = msg.indexOf(from);
                        int toIndex = msg.indexOf(to);
                        descr = msg.substring(0, fromIndex).trim();
                        String startDT = msg.substring(fromIndex + from.length(), toIndex).trim();
                        String endDT = msg.substring(toIndex + to.length()).trim();
                        checkExist(!descr.isBlank(), "The description of a EVENT command cannot be empty.");
                        checkExist(!startDT.isBlank(), "The starting datetime of a EVENT command cannot be empty.");
                        checkExist(!endDT.isBlank(), "The ending datetime of a EVENT command cannot be empty.");

                        myTaskList.addTask(new Event(descr, startDT, endDT));
                        break;

                    case DELETE:
                        deleteOrMark("DELETE", myTaskList, input);
                        break;

                    case MARK:
                        deleteOrMark("MARK", myTaskList, input);
                        break;

                    case UNMARK:
                        deleteOrMark("UNMARK", myTaskList, input);
                        break;

                    case LIST:
                        myTaskList.list();
                        break;

                    case HELP:
                        myTaskList.help();
                        break;

                    default:
                        myTaskList.invalid();
                }
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }
            input = scn.nextLine();
            cmd = getCommand(input);
        }
        Feedback.exit();
        scn.close();
    }
}
