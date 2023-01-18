import java.lang.String;
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

    private enum Command {
        TODO, DEADLINE, EVENT,
        MARK, UNMARK,
        LIST,
        BYE,
        INVALID
    }

    public static Command getCommand(String input) {
        String cmd = input.split(" ", 2)[0].toLowerCase();
        switch (cmd) {
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
            case "bye":
                return Command.BYE;
            default:
                return Command.INVALID;
        }
    }

    public static void addTask(ArrayList<Task> myTaskList, Task task, Feedback fb) {
        myTaskList.add(task);
        fb.addedTask(myTaskList.size(), task);
    }

    public static void markTask(Task task, Boolean isMark, Feedback fb) {
        if (isMark) {
            task.markDone();
            fb.markedTask(task);
        } else {
            task.unmarkDone();
            fb.unmarkedTask(task);
        }
    }

    public static void main(String[] args) {
        // Initialize components
        Feedback fb = new Feedback();
        Scanner scn = new Scanner(System.in);
        ArrayList<Task> myTaskList = new ArrayList<>(100);

        fb.greet(LOGO);
        String input = scn.nextLine();
        Command cmd = getCommand(input);

        while (!cmd.equals(Command.BYE)) {
            String msg, descr;
            int index;

            switch (cmd) {
                case TODO:
                    msg = input.split(" ", 2)[1];
                    addTask(myTaskList, new Todo(msg), fb);
                    break;

                case DEADLINE:
                    msg = input.split(" ", 2)[1];
                    String by = " /by ";
                    int byIndex = msg.indexOf(by);

                    descr = msg.substring(0, byIndex);
                    String dateTime = msg.substring(byIndex + by.length());
                    addTask(myTaskList, new Deadline(descr, dateTime), fb);
                    break;

                case EVENT:
                    msg = input.split(" ", 2)[1];
                    String from = " /from ";
                    String to = " /to ";
                    int startDTIndex = msg.indexOf(from);
                    int endDTIndex = msg.indexOf(to);

                    descr = msg.substring(0, startDTIndex);
                    String startDT = msg.substring(startDTIndex + from.length(), endDTIndex);
                    String endDT = msg.substring(endDTIndex + to.length());
                    addTask(myTaskList, new Event(descr, startDT, endDT), fb);
                    break;

                case MARK:
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    markTask(myTaskList.get(index), true, fb);
                    break;

                case UNMARK:
                    index = Integer.parseInt(input.split(" ")[1]) - 1;
                    markTask(myTaskList.get(index), false, fb);
                    break;

                case LIST:
                    fb.listTask(myTaskList);
                    break;

                default:
                    System.out.println("Invalid Command. Please try again.\n");
            }
            input = scn.nextLine();
            cmd = getCommand(input);
        }
        fb.exit();
        scn.close();
    }
}
