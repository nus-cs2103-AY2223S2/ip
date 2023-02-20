package duke.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeCommandNotFoundException;
import duke.exception.DukeEmptyTaskException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Constructs a (static) parser that parses user input strings.
 */
public class Parser {
    private static final DateTimeFormatter read_fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter print_fmt = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    /**
     * Parses the date and time from input string.
     * @param input User-provided input string.
     * @return A string that conforms to the specified printing format.
     */
    public static String parse_date(String input) {
        try {
            LocalDateTime lt = LocalDateTime.parse(input, read_fmt);
            return lt.format(print_fmt);
        } catch (DateTimeParseException | UnsupportedTemporalTypeException e) {
            print("please follow the standard datetime format: yyyy-MM-dd HH:mm");
        }
        return input;
    }

    /**
     * Parses integer from a string input.
     * @param input User-provided input string.
     * @return Parsed integer returned as the task id.
     * @throws DukeEmptyTaskException Indicates that task id is not found
     */
    public static int parse_task_id(String input) throws DukeEmptyTaskException {
        int tid;
        try {
            tid = Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeEmptyTaskException();
        }
        return tid;
    }

    /**
     * Parses match string from a string input.
     * @param input User-provided input string.
     * @return Parsed string returned as the matching string.
     * @throws DukeEmptyTaskException Indicates that match string is not found
     */
    public static String parse_match_string(String input) throws DukeEmptyTaskException {
        String matchStr;
        try {
            matchStr = input.split(" ")[1].strip();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeEmptyTaskException();
        }
        return matchStr;
    }

    /**
     * Parses and constructs Todo object from a string input.
     * @param trigger Task trigger, in this case, it is "todo".
     * @param input User-provided input string.
     * @return Constructed Todo object from the parsed contents.
     */
    public static Todo parse_todo(String trigger, String input) {
        if (input.split(trigger).length == 0) {
            return new Todo();
        } else {
            input = input.split(trigger)[1].strip();
            return new Todo(input);
        }
    }

    /**
     * Parses and constructs Deadline object from a string input.
     * @param trigger Task trigger, in this case, it is "deadline".
     * @param input User-provided input string.
     * @return Constructed Deadline object from the parsed contents.
     */
    public static Deadline parse_deadline(String trigger, String input) {
        String content;
        String deadline;
        try {
            input = input.split(trigger)[1];
            content = input.split("/by")[0].strip();
            deadline = parse_date(input.split("/by")[1].strip());
            return new Deadline(content, deadline);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return new Deadline();
    }

    /**
     * Parses and constructs Event object from a string input.
     * @param trigger Task trigger, in this case, it is "event".
     * @param input User-provided input string.
     * @return Constructed Event object from the parsed contents.
     * @throws DukeEmptyTaskException Indicates that task description is not found
     */
    public static Event parse_event(String trigger, String input) throws DukeEmptyTaskException {
        String content;
        String fromTime;
        String toTime;
        if (input.split(trigger).length == 1) {
            throw new DukeEmptyTaskException();
        } else {
            input = input.split(trigger)[1].strip();
        }
        try {
            content = input.split("/from")[0].strip();
            fromTime = parse_date(input.split("/from")[1].split("/to")[0].strip());
            toTime = parse_date(input.split("/from")[1].split("/to")[1].strip());
            return new Event(content, fromTime, toTime);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return new Event();
    }


    /**
     * Parses task id to delete from a string input.
     * @param trigger Task trigger, in this case, it is "delete".
     * @param input User-provided input string.
     * @return Task id to delete from.
     * @throws DukeEmptyTaskException Indicates that task description is not found
     */
    public static int parse_delete_task_id(String trigger, String input) throws DukeEmptyTaskException {
        int tid = -1;
        if (input.split(trigger).length == 1) {
            throw new DukeEmptyTaskException();
        }
        try {
            tid = Integer.parseInt(input.split(trigger)[1].strip());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return tid;
    }

    /**
     * Print a string via System.in.
     * @param str The string to print.
     */
    public static void print(String str) {
        System.out.println(str);
    }

    /**
     * Switch on cases where different triggering commands are met.
     * @param tasks The list of tasks to operate upon.
     * @param input The input user-provided instruction string.
     * @param ui The user-interface object that handles printing jobs.
     * @return A status string that indicates job status.
     * @throws DukeCommandNotFoundException Indicates when user-provided instruction cannot be catered.
     * @throws DukeEmptyTaskException Indicates when user fails to provide a concrete task description.
     */
    public static String switch_input(ArrayList<Task> tasks, String input, Ui ui)
            throws DukeCommandNotFoundException, DukeEmptyTaskException {
        String trigger = input.split(" ")[0];
        Command command = new Command(tasks, ui);
        int tid;
        Task task;
        String output;
        switch (trigger) {
        case "bye":
            output = ui.print_bye_msg();
            break;
        case "list":
            output = ui.print_task_list(tasks);
            break;
        case "mark":
            tid = parse_task_id(input);
            output = command.mark_as_done(tid);
            break;
        case "unmark":
            tid = parse_task_id(input);
            output = command.mark_as_undone(tid);
            break;
        case "deadline":
            task = parse_deadline(trigger, input);
            output = command.add_task(task);
            break;
        case "event":
            task = parse_event(trigger, input);
            output = command.add_task(task);
            break;
        case "todo":
            task = parse_todo(trigger, input);
            output = command.add_task(task);
            break;
        case "delete":
            tid = parse_delete_task_id(trigger, input);
            output = command.delete_task(tid);
            break;
        case "find":
            String matchStr = parse_match_string(input);
            output = command.find_tasks(matchStr);
            break;
        default:
            throw new DukeCommandNotFoundException();
        }
        return output;
    }

    /**
     * Processes inputs from Scanner input stream when used without GUI.
     * @param tasks The list of tasks to operate upon.
     * @param sc The scanner object that accepts input streams.
     * @param ui The user-interface object that handles printing jobs.
     */
    public static void process_input(ArrayList<Task> tasks, Scanner sc, Ui ui) {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                print(switch_input(tasks, input, ui));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
