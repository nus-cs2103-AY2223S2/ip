package duke;

import java.util.Scanner;

import duke.task.*;
import duke.constant.Message;
import duke.database.DukeRepo;
import duke.database.DukeRepoImpl;
import duke.exception.DukeException;
import duke.exception.IllegalDukeCommandArgumentException;
import duke.exception.IllegalDukeTaskAccessException;
import duke.exception.InvalidTaskTypeException;
import duke.exception.NoSuchDukeCommandException;

/**
 * Duke agent that knows how to manage a todo list.
 */
public class Duke {

    private boolean isActive;
    private DukeRepo db;

    /**
     * Default constructor.
     */
    Duke() {
        isActive = true;
        db = new DukeRepoImpl();
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
     * Says fatewell and closes the database.
     * 
     * @return farewell
     */
    private String bye() {
        isActive = false;
        db.close();
        return "Bye. Hope to see you again soon!";
    }

    private String listTask() {
        if (db.getAllTask().isEmpty()) {
            return "No task found, use:\n"
                    + "\t<todo     [title]> \n"
                    + "\t<deadline [title] \\by   [date]> \n"
                    + "\t<event    [title] \\from [date] \\to [date]> \n"
                    + "commands to create task.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < db.count(); i++) {
            sb.append(String.format("\t%d. %s\n", i + 1, db.getAllTask().get(i)));
        }
        return sb.toString();
    }

    private String markTask(int idx) throws IllegalDukeTaskAccessException {
        if (idx < 1 || idx > db.count()) {
            throw new IllegalDukeTaskAccessException(Message.EXCEPTION_INVALID_TODO_ID_ACCESS);
        }

        Task tk = db.getTask(idx - 1);
        tk.markAsDone();

        return "Nice! I've marked this task as done:\n"
                + "\t" + tk;
    }

    private String unMarkTask(int idx) throws IllegalDukeTaskAccessException {
        if (idx < 1 || idx > db.count()) {
            throw new IllegalDukeTaskAccessException(Message.EXCEPTION_INVALID_TODO_ID_ACCESS);
        }

        Task tk = db.getTask(idx - 1);
        tk.unmarkDone();

        return "OK, I've marked this task as not done yet:\n"
                + "\t" + tk;
    }

    private String addTask(Task tk) {
        db.addTask(tk);
        return "Got it. I've added this task:\n"
                + "\t" + tk + "\n"
                + "Now you have " + db.count() + " tasks in the list.";
    }

    private String deleteTask(int idx) throws IllegalDukeTaskAccessException {
        if (idx < 1 || idx > db.count()) {
            throw new IllegalDukeTaskAccessException(Message.EXCEPTION_INVALID_TODO_ID_ACCESS);
        }
        Task tk = db.removeTask(idx - 1);

        return "Noted. I've removed this task:\n"
                + "\t" + tk + "\n"
                + "Now you have " + db.count() + " tasks in the list.";
    }

    /**
     * Handles incoming commands and invoke the corresponding duke functions.
     * 
     * @param command a string command with variable word count
     * @return function outputs
     * @throws NoSuchDukeCommandException          if duke does not understand the
     *                                             command
     * @throws IllegalDukeCommandArgumentException if the command does not follow
     *                                             the command format
     */
    private String read(String command)
            throws IllegalDukeCommandArgumentException, NoSuchDukeCommandException, IllegalDukeTaskAccessException {
        if (!isActive()) {
            return "This duke is nolonger active, please hire another agent.";
        }

        DukeParser dp = DukeParser.parseInput(command);

        switch (dp.getCommand()) {
            // commands without args
            case BYE:
                return bye();
            case LIST:
                return listTask();

            // commands with 1 args
            case MARK:
                return markTask(dp.getTaskId());
            case UNMARK:
                return unMarkTask(dp.getTaskId());
            case DELETE:
                return deleteTask(dp.getTaskId());

            // commands with special args
            case TODO:
            case EVENT:
            case DEADLINE:
                try {
                    return addTask(DukeParser.toTask(dp));
                } catch (InvalidTaskTypeException e) {
                    e.printStackTrace();
                }
            default:
                return "";
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
            } catch (DukeException e) {
                duke.say(e.getMessage());
            }
        }

        sc.close();
    }

}
