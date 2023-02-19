package duke.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.ui.Ui;
import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.tool.Command;
import duke.exception.DukeCommandNotFoundException;
import duke.exception.DukeEmptyTaskException;

public class Parser {
    private static final DateTimeFormatter read_fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final DateTimeFormatter print_fmt = DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm a");

    public static DateTimeFormatter getReadFormat() {
        return read_fmt;
    }

    public static DateTimeFormatter getPrintFormat() {
        return print_fmt;
    }

    public static String parse_date(String input) {
        DateTimeFormatter read_fmt = getReadFormat();
        DateTimeFormatter print_fmt = getPrintFormat();
        try {
            LocalDateTime lt = LocalDateTime.parse(input, read_fmt);
            return lt.format(print_fmt);
        } catch (DateTimeParseException | UnsupportedTemporalTypeException e) {
            e.printStackTrace();
            print("please follow the standard datetime format: yyyy-MM-dd HH:mm");
        }
        return input;
    }

    public static int parse_task_id(String input) {
        int tid = -1;
        try {
            tid = Integer.parseInt(input.split(" ")[1]);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return tid;
    }

    public static Todo parse_todo(String trigger, String input) throws DukeEmptyTaskException {
        if (input.split(trigger).length == 0) {
            return new Todo();
        } else {
            input = input.split(trigger)[1].strip();
            return new Todo(input);
        }
    }

    public static Deadline parse_deadline(String trigger, String input) {
        String content = "", ddl = "";
        try {
            input = input.split(trigger)[1];
            content = input.split("/by")[0].strip();
            ddl = parse_date(input.split("/by")[1].strip());
            return new Deadline(content, ddl);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return new Deadline();
    }

    public static Event parse_event(String trigger, String input) throws DukeEmptyTaskException {
        String content = "", from = "", to = "";
        if (input.split(trigger).length == 1) {
            throw new DukeEmptyTaskException();
        } else {
            input = input.split(trigger)[1].strip();
        }
        try {
            content = input.split("/from")[0].strip();
            from = parse_date(input.split("/from")[1].split("/to")[0].strip());
            to = parse_date(input.split("/from")[1].split("/to")[1].strip());
            return new Event(content, from, to);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return new Event();
    }

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

    public static void print(String str) {
        System.out.println(str);
    }
    public static void switch_input(ArrayList<Task> tasks, String input, Ui ui) throws DukeCommandNotFoundException, DukeEmptyTaskException {
        String trigger = input.split(" ")[0];
        int tid;
        Task task;
        switch (trigger) {
            case "bye":
                ui.print_bye_msg();
                System.exit(0);
            case "list":
                ui.print_task_list(tasks);
                break;
            case "mark":
                tid = parse_task_id(input);
                Command.mark_as_done(tid, tasks, ui);
                break;
            case "unmark":
                tid = parse_task_id(input);
                Command.mark_as_undone(tid, tasks, ui);
                break;
            case "deadline":
                task = parse_deadline(trigger, input);
                Command.add_task_to_list(task, tasks, ui);
                break;
            case "event":
                task = parse_event(trigger, input);
                Command.add_task_to_list(task, tasks, ui);
                break;
            case "todo":
                task = parse_todo(trigger, input);
                Command.add_task_to_list(task, tasks, ui);
                break;
            case "delete":
                tid = parse_delete_task_id(trigger, input);
                Command.delete_task(tid, tasks, ui);
                break;
            default:
                throw new DukeCommandNotFoundException();
        }
    }

    public static void process_input(ArrayList<Task> tasks, Scanner sc, Ui ui) {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                switch_input(tasks, input, ui);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
