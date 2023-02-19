package duke.tool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
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

    public static String parse_date(String s) {
        DateTimeFormatter read_fmt = getReadFormat();
        DateTimeFormatter print_fmt = getPrintFormat();
        try {
            LocalDateTime lt = LocalDateTime.parse(s, read_fmt);
            return lt.format(print_fmt);
        } catch (DateTimeParseException | UnsupportedTemporalTypeException e) {
            e.printStackTrace();
            print("please follow the standard datetime format: yyyy-MM-dd HH:mm");
        }
        return s;
    }

    public static void print(String str) {
        System.out.println(str);
    }
    public static void switch_input(ArrayList<Task> todos, String input) throws DukeCommandNotFoundException, DukeEmptyTaskException {
        String trigger = input.split(" ")[0];
        int tid = 1;
        Task task;
        String content = "", ddl = "", from = "", to = "";
        switch (trigger) {
            case "bye":
                print("Bye. Hope to see you again soon!");
                System.exit(0);
            case "list":
                if (todos.isEmpty()) {
                    print("No items yet.");
                } else {
                    int i = 1;
                    for (Task t : todos) {
                        print(i + "." + t.toString());
                        i++;
                    }
                }
                break;
            case "mark":
                try {
                    tid = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                task = todos.get(tid - 1);
                task.markAsDone();
                print("Nice! I've marked this duke.task as done:");
                print("\t" + task);
                break;
            case "unmark":
                try {
                    tid = Integer.parseInt(input.split(" ")[1]);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                task = todos.get(tid - 1);
                task.markAsNotDone();
                print("OK, I've marked this duke.task as not done yet:");
                print("\t" + task);
                break;
            case "deadline":
                try {
                    input = input.split(trigger)[1];
                    content = input.split("/by")[0].strip();
                    ddl = input.split("/by")[1].strip();
                    ddl = parse_date(ddl);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                task = new Deadline(content, ddl);
                todos.add(task);
                print("Got it. I've added this duke.task:");
                print("\t" + task);
                print("Now you have " + todos.size() + " tasks in the list.");
                break;
            case "event":
                if (input.split(trigger).length == 1) {
                    throw new DukeEmptyTaskException();
                } else {
                    input = input.split(trigger)[1].strip();
                }
                try {
                    content = input.split("/from")[0].strip();
                    from = input.split("/from")[1].split("/to")[0].strip();
                    from = parse_date(from);
                    to = input.split("/from")[1].split("/to")[1].strip();
                    to = parse_date(to);
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                task = new Event(content, from, to);
                todos.add(task);
                print("Got it. I've added this duke.task:");
                print("\t" + task);
                print("Now you have " + todos.size() + " tasks in the list.");
                break;
            case "todo":
                if (input.split(trigger).length == 0) {
                    throw new DukeEmptyTaskException();
                } else {
                    input = input.split(trigger)[1].strip();
                }
                task = new Todo(input);
                todos.add(task);
                print("Got it. I've added this duke.task:");
                print("\t" + task);
                print("Now you have " + todos.size() + " tasks in the list.");
                break;
            case "delete":
                if (input.split(trigger).length == 1) {
                    throw new DukeEmptyTaskException();
                }
                try {
                    tid = Integer.parseInt(input.split(trigger)[1].strip());
                    task = todos.get(tid - 1);
                    todos.remove(task);
                    print("Noted. I've removed this duke.task:");
                    print("\t" + task);
                    print("Now you have " + todos.size() + " tasks in the list.");
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    e.printStackTrace();
                    System.exit(1);
                }
                break;
            default:
                throw new DukeCommandNotFoundException();
        }
    }

    public static void process_input(ArrayList<Task> todos, Scanner sc) {
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            try {
                switch_input(todos, input);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
