import collections.TaskList;
import exceptions.SaturdayException;
import models.Deadline;
import models.Event;
import models.Task;
import models.ToDo;

import java.util.Scanner;

public class Saturday {
    private static boolean isActive = true;
    private static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        // Greeting
        Utils.divider();
        Utils.output("Hello! I'm Saturday\n\tWhat can I do for you?");
        Utils.divider();
        Utils.newline();

        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            String input = scanner.nextLine();
            Utils.divider();
            try {
                Command command = Command.getCommand(input);
                command.execute(input);
            } catch (SaturdayException e) {
                Utils.output(e.getMessage());
            }
            Utils.divider();
            Utils.newline();
        }
    }

    public static void todo(String text) {
        int s = text.indexOf(" ");
        if (s != -1) {
            String description = text.substring(s + 1);
            ToDo task = new ToDo(description);
            taskList.add(task);
            Utils.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size() + " tasks in the list.");
        } else {
            throw new SaturdayException("OOPS!!! The description of a todo cannot be empty");
        }
    }

    public static void deadline(String text) {
        int s = text.indexOf(" ");
        int d = text.indexOf("/by");
        if (s != -1 && d != -1 && d > s) {
            String description = text.substring(s + 1, d - 1);
            String deadline = text.substring(d + 4);
            Deadline task = new Deadline(description, deadline);
            taskList.add(task);
            Utils.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size() + " tasks in the list.");
        } else {
            throw new SaturdayException("OOPS!!! The deadline cannot be empty (use /by)");
        }
    }

    public static void event(String text) {
        int s = text.indexOf(" ");
        int f = text.indexOf("/from");
        int t = text.indexOf("/to");
        if (s != -1 && f != -1 && t != -1 && t > f && f > s) {
            String description = text.substring(s + 1, f - 1);
            String from = text.substring(f + 6, t - 1);
            String to = text.substring(t + 4);
            Event task = new Event(description, from, to);
            taskList.add(task);
            Utils.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size() + " tasks in the list.");
        } else {
            throw new SaturdayException("OOPS!!! The timeframe cannot be empty (use /from and /to)");
        }
    }

    public static void displayList() {
        Utils.output("Here are the tasks in your list:\n\t" + taskList.toString());
    }

    public static void mark(String text) {
        String[] parts = text.split("\\s");
        if (parts.length > 1) {
            String number = parts[1];
            if (number.matches("^\\d+")) {
                int i = Integer.valueOf(number);
                try {
                    taskList.mark(i);
                    Utils.output("Nice! I've marked this task as done:\n\t  " + taskList.get(i));
                } catch (IndexOutOfBoundsException e) {
                    Utils.output("OOPS!!! There's no such task in your list");
                }
            }
        } else {
            throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
        }
    }

    public static void unMark(String text) {
        String[] parts = text.split("\\s");
        if (parts.length > 1) {
            String number = parts[1];
            if (number.matches("^\\d+")) {
                int i = Integer.valueOf(number);
                try {
                    taskList.unMark(i);
                    Utils.output("OK, I've marked this task as not done yet:\n\t  " + taskList.get(i));
                } catch (IndexOutOfBoundsException e) {
                    Utils.output("OOPS!!! There's no such task in your list");
                }
            }
        } else {
            throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
        }
    }

    public static void delete(String text) {
        String[] parts = text.split("\\s");
        if (parts.length > 1) {
            String number = parts[1];
            if (number.matches("^\\d+")) {
                int i = Integer.valueOf(number);
                try {
                    Task removedTask = taskList.remove(i);
                    Utils.output("Noted. I've removed this task:\n\t  " + removedTask + "\n\tNow you have " + taskList.size() + " tasks in the list.");
                } catch (IndexOutOfBoundsException e) {
                    Utils.output("OOPS!!! There's no such task in your list");
                }
            }
        } else {
            throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
        }
    }

    public static void exit() {
        isActive = false;
        Utils.output("Bye. Hope to see you again soon!");
    }

}
