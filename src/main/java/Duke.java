import java.util.NoSuchElementException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.DateUtil;

/**
 * Duke agent that knows how to manage a todo list.
 */
public class Duke {

    /* Exception constants */
    private final static String EXCEPTION_NOSUCH_COMMAND = "I'm sorry, but I don't know what that means :-(";
    private final static String EXCEPTION_INVALID_TODO_ID = "Invalid task id! Task id must be a number.";
    private final static String EXCEPTION_INVALID_TODO_ID_ACCESS = "Unable to find task.";
    private final static String EXCEPTION_INVALID_TODO_CMD = "The description of a todo cannot be empty.";
    private final static String EXCEPTION_INVALID_EVENT_CMD = "Invalid event command format.";
    private final static String EXCEPTION_INVALID_DEADLINE_CMD = "Invalid deadline command format.";
    private final static String EXCEPTION_INVALID_DATE_CMD = "Invalid date command format.";

    private List<Task> tList;
    private boolean isActive;
    
    /**
     * Default constructor.
     */
    Duke() {
        tList = new ArrayList<Task>();
        isActive = true;
    }

    /**
     * @return active status of duke
     */
    private boolean isActive() {
        return isActive;
    }

    /**
     * @return greeting in duke language
     */
    private String greeting() {
        return "Hello from\n"
                + " ____       _          \n"
                + "|  _ \\ _  _| | ____ _   \n"
                + "| | | | | |  | |/ / _ \\ \n"
                + "| |_| | |_|  |   <  __/  \n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "What can I do for you?\n";
    }

    /**
     * Say fatewell and update the active status of duke.
     * 
     * @return farewell
     */
    private String bye() {
        isActive = false;
        return "Bye. Hope to see you again soon!";
    }

    private String listTask() {
        if (tList.isEmpty()) {
            return "No task found, use:\n"
                    + "\t<todo     [title]> \n"
                    + "\t<deadline [title] \\by   [date]> \n"
                    + "\t<event    [title] \\from [date] \\to [date]> \n"
                    + "commands to create task.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        sb.append(printList(tList));
        return sb.toString();
    }

    private String findTaskByDate(String date) {
        LocalDateTime key = DateUtil.toLocalDateTime(date);

        List<Task> filtered = tList.stream().filter(task -> {
                if (task instanceof Deadline) {
                    Deadline d = (Deadline) task;
                    if (d.getBy().equals(key)) {
                        return true;
                    }
                }
                if (task instanceof Event) {
                    Event e = (Event) task;
                    if (e.getFrom().equals(key)) {
                        return true;
                    }
                }
                return false;
            }).toList();

            StringBuilder sb = new StringBuilder();
            sb.append("Here are the tasks in your list happening on ["+ DateUtil.dateToString(key) +"]:\n");
            sb.append(printList(filtered));
            return sb.toString();
    }

    private String printList(List<Task> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(String.format("\t%d. %s\n", i + 1, list.get(i)));
        }
        return sb.toString();
    }

    private String markTask(int idx) throws IllegalDukeTaskAccessException {
        if (idx < 1 || idx > tList.size()) {
            throw new IllegalDukeTaskAccessException(EXCEPTION_INVALID_TODO_ID_ACCESS);
        }

        Task tk = tList.get(idx - 1);
        tk.markAsDone();

        return "Nice! I've marked this task as done:\n"
                + "\t" + tk;
    }

    private String unMarkTask(int idx) throws IllegalDukeTaskAccessException {
        if (idx < 1 || idx > tList.size()) {
            throw new IllegalDukeTaskAccessException(EXCEPTION_INVALID_TODO_ID_ACCESS);
        }

        Task tk = tList.get(idx - 1);
        tk.unmarkDone();

        return "OK, I've marked this task as not done yet:\n"
                + "\t" + tk;
    }

    private String addTask(Task tk) {
        tList.add(tk);
        return "Got it. I've added this task:\n"
                + "\t" + tk + "\n"
                + "Now you have " + tList.size() + " tasks in the list.";
    }

    private String addTodo(String title) {
        return addTask(new Todo(title));
    }

    private String addEvent(String title, String from, String to) {
        return addTask(new Event(title, DateUtil.toLocalDateTime(from), DateUtil.toLocalDateTime(to)));
    }

    private String addDeadline(String title, String by) {
        return addTask(new Deadline(title, DateUtil.toLocalDateTime(by)));
    }

    private String deleteTask(int idx) throws IllegalDukeTaskAccessException {
        if (idx < 1 || idx > tList.size()) {
            throw new IllegalDukeTaskAccessException(EXCEPTION_INVALID_TODO_ID_ACCESS);
        }
        Task tk = tList.remove(idx - 1);

        return "Noted. I've removed this task:\n"
                + "\t" + tk + "\n"
                + "Now you have " + tList.size() + " tasks in the list.";
    }

    /**
     * Handles incomming commands and invoke the corresponding duke functions.
     * 
     * @param command a string command with variable word count
     * @return function outputs
     * @throws NoSuchDukeCommandException          if duke does not understand the
     *                                             command
     * @throws IllegalDukeCommandArgumentException if the command does not follow
     *                                             the command format
     */
    private String read(String command) throws NoSuchDukeCommandException, IllegalDukeCommandArgumentException {
        if (!isActive()) {
            return "This duke is nolonger active, please hire another agent.";
        }

        // Splits raw command by first space.
        // 0: action
        // 1: remaining args for further decoding
        String[] input = command.toLowerCase().split(" ", 2);
        String[] options;

        try {
            switch (DukeCommand.valueOf(input[0].toUpperCase())) {
                case BYE:
                    return bye();
                case LIST:
                    return listTask();
                case MARK:
                    try {
                        return markTask(Integer.parseInt(input[1]));
                    } catch (NumberFormatException e) {
                        throw new IllegalDukeCommandArgumentException(EXCEPTION_INVALID_TODO_ID);
                    } catch (IllegalDukeTaskAccessException e) {
                        return e.getMessage();
                    }
                case UNMARK:
                    try {
                        return unMarkTask(Integer.parseInt(input[1]));
                    } catch (NumberFormatException e) {
                        throw new IllegalDukeCommandArgumentException(EXCEPTION_INVALID_TODO_ID);
                    } catch (IllegalDukeTaskAccessException e) {
                        return e.getMessage();
                    }
                case TODO:
                    if (input.length != 2) {
                        throw new IllegalDukeCommandArgumentException(EXCEPTION_INVALID_TODO_CMD);
                    }

                    return addTodo(input[1]);
                case EVENT:
                    options = input[1].split(" /[a-z]*[^ ] ");

                    if (options.length != 3) {
                        throw new IllegalDukeCommandArgumentException(EXCEPTION_INVALID_EVENT_CMD);
                    }

                    return addEvent(options[0], options[1], options[2]);
                case DEADLINE:
                    options = input[1].split(" /[a-z]*[^ ] ");

                    if (options.length != 2) {
                        throw new IllegalDukeCommandArgumentException(EXCEPTION_INVALID_DEADLINE_CMD);
                    }

                    return addDeadline(options[0], options[1]);
                case DELETE:
                    try {
                        return deleteTask(Integer.parseInt(input[1]));
                    } catch (NumberFormatException e) {
                        throw new IllegalDukeCommandArgumentException(EXCEPTION_INVALID_TODO_ID);
                    } catch (IllegalDukeTaskAccessException e) {
                        return e.getMessage();
                    }
                case DATE:
                    if (input.length != 2) {
                        throw new IllegalDukeCommandArgumentException(EXCEPTION_INVALID_DATE_CMD);
                    }

                    return findTaskByDate(input[1]);
                default:
                    throw new NoSuchDukeCommandException(EXCEPTION_NOSUCH_COMMAND);
            }
        } catch (IllegalArgumentException e) {
            throw new NoSuchDukeCommandException(EXCEPTION_NOSUCH_COMMAND);
        }
    }

    /**
     * Prints any string in duke style.
     * 
     * @param text
     */
    private void say(String text) {
        System.out.println("-".repeat(40));
        System.out.println(text);
        System.out.println("-".repeat(40));
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.say(duke.greeting());

        Scanner sc = new Scanner(System.in);
        while (duke.isActive()) {
            try {
                duke.say(duke.read(sc.nextLine()));
            } catch (NoSuchDukeCommandException | IllegalDukeCommandArgumentException e) {
                duke.say(e.getMessage());
            } catch (NoSuchElementException e) {
                duke.say(duke.bye());
            }
        }

        sc.close();
    }

}
