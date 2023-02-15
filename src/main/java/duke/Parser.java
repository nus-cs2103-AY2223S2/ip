package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Represents commands from user input
 */
public class Parser {
    private static final String INDENTATION = " ";
    private Ui ui;

    /**
     * Parser constructor
     * @param ui
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Inputs commands such as  list, mark, unmark, delete, to do, deadline, and event
     * @param cmd command
     * @param  tasks task
     * @return command
     */
    public static String parse(String cmd, TaskList tasks) {
        String command = cmd.trim();
        String[] words = command.split(" ");

        switch (words[0]) {
        case "find":
            return findCommand(cmd, tasks);
        case "list":
            return Ui.showList(tasks);
        case "mark":
            return markCommand(cmd, tasks);
        case "unmark":
            return unmarkCommand(cmd, tasks);
        case "todo":
            return todoCommand(cmd, tasks);
        case "deadline":
            return deadlineCommand(cmd, tasks);
        case "event":
            return eventCommand(cmd, tasks);
        case "delete":
            return deleteCommand(cmd, tasks);
        case "sort":
            return sortCommand(cmd, tasks);
        case "bye":
            return Ui.exit();
        default:
            return "Sorry, I do not understand your instruction. Plz try again later";
        }
    }

    /**
     * Sorts the command in 3 different groups: todo,deadline, event
     * @param cmd command
     * @param  tasks task
     * @return sort result
     */
    public static String sortCommand(String cmd, TaskList tasks) {
        StringBuilder str = new StringBuilder();
        String[] words = cmd.trim().split(" ");

        try {
            ArrayList<Task> sortTask = new ArrayList<>();
            for (int i = 0; i < tasks.size(); i++) {
                Task items = tasks.get(i);
                if (words[1].equals("todo") && items instanceof Todo) {
                    sortTask.add(items);
                } else if (words[1].equals("deadline") && items instanceof Deadline) {
                    sortTask.add(items);
                } else if (words[1].equals("event") && items instanceof Event) {
                    sortTask.add(items);
                }
            }

            if (sortTask.size() != 0) {
                str.append("Here are the sorted result:\n");
                for (Task task: sortTask) {
                    str.append("\u2764 " + task + "\n");
                }
                return str.toString();
            } else {
                return "Sorry, there is no record of the specified type!";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "  ☹ OOPS!!! The sort command is missing the task type!\n";
        }
    }

    /**
     * Marks the task as done
     * @param cmd command
     * @param  tasks task
     * @return mark command
     */
    public static String markCommand(String cmd, TaskList tasks) {
        String str = "";
        String command = cmd.trim();
        String[] words = command.split(" ");

        try {
            str += Ui.done(words[1], tasks);
            str += "\n";
        } catch (Exception e) {
            str += "  ☹ OOPS!!! The index number cannot be empty. \n";
        }
        return str;
    }

    /**
     * Marks the task as undone
     * @param cmd command
     * @param  tasks task
     * @return unmark command
     */
    public static String unmarkCommand(String cmd, TaskList tasks) {
        String str = "";
        String command = cmd.trim();
        String[] words = command.split(" ");

        try {
            str += Ui.undone(words[1], tasks);
            str += "\n";
        } catch (Exception e) {
            str += "  ☹ OOPS!!! The index number cannot be empty. \n";
        }

        return str;
    }

    /**
     * Deletes the task
     *
     * @param cmd command
     * @param  tasks task
     * @return delete command
     */
    public static String deleteCommand(String cmd, TaskList tasks) {
        String str = "";
        String command = cmd.trim();
        String[] words = command.split(" ");

        try {
            str += Ui.delete(words[1], tasks);
            str += "\n";
        } catch (Exception e) {
            str += "  ☹ OOPS!!! The index number cannot be empty. \n";
        }
        return str;
    }

    /**
     * Finds the task
     *
     * @param cmd command
     * @param  tasks task
     * @return find result
     */
    public static String findCommand(String cmd, TaskList tasks) {
        String info;
        String str = "";
        String command = cmd.trim();

        try {
            info = command.substring(command.indexOf(" ") + 1);

            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(info)) {
                    str += INDENTATION + "Here are the matching tasks in your list:";
                    str += INDENTATION + (i + 1) + "." + tasks.get(i).toString();
                }
            }
        } catch (Exception e) {
            str += "  ☹ OOPS!!! The description of a find cannot be empty. \n";
        }
        return str;
    }

    /**
     * Adds to do task
     * @param cmd command
     * @param  tasks task
     * @return to do command
     */
    public static String todoCommand(String cmd, TaskList tasks) {
        Task task;
        String info;
        String str = "";
        String command = cmd.trim();
        String[] words = command.split(" ");

        try {
            if (!words[1].contains(" ")) {
                info = command.substring(command.indexOf(" ") + 1);
                task = new Todo(info, false, tasks, false);
                tasks.add(task);
                str += new Todo(info, false, tasks, false);

            }
        } catch (Exception e) {
            str += "☹ OOPS!!! The description of a todo cannot be empty.";
        }
        return str;
    }

    /**
     * Adds deadline task
     * @param cmd command
     * @param  tasks task
     * @return deadline command
     */
    public static String deadlineCommand(String cmd, TaskList tasks) {
        Task task;
        String info;
        String str = "";
        String command = cmd.trim();

        try {
            info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /by"));
            String deadline = command.substring(command.indexOf("/by") + 4);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(("MM/dd/yyyy HHmm"));
            LocalDateTime datetime1 = LocalDateTime.parse(deadline, formatter);

            task = new Deadline(info,
                    datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                    false, tasks, false);
            str += new Deadline(info,
                    datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                    false, tasks, false);
            str += "\n";
            tasks.add(task);
        } catch (Exception e) {
            str += "  ☹ OOPS!!! Please follow the format: \n"
                    + "deadline [task name] /by 01/02/2013 1820 \n";
        }
        return str;
    }

    /**
     * Adds event task
     * @param cmd command
     * @param  tasks task
     * @return event command
     */
    public static String eventCommand(String cmd, TaskList tasks) {
        Task task;
        String info;
        String str = "";
        String command = cmd.trim();

        try {
            info = command.substring(command.indexOf(" ") + 1, command.indexOf(" /from "));
            String fromtime = command.substring(command.indexOf(" /from ") + 6, command.indexOf(" /to "));
            String totime = command.substring(command.indexOf(" /to ") + 4);
            try {
                DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern((" MM/dd/yyyy HHmm"));
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern((" MM/dd/yyyy HHmm"));
                LocalDateTime datetime1 = LocalDateTime.parse(fromtime, formatter1);
                LocalDateTime datetime2 = LocalDateTime.parse(totime, formatter2);

                task = new Event(info,
                        datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                        datetime2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                        false,
                        tasks,
                        false);
                str += new Event(info,
                        datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                        datetime2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                        false,
                        tasks,
                        false
                );

            } catch (DateTimeParseException e) {
                task = new Event(info, fromtime, totime, false, tasks, false);
                str += new Event(info, fromtime, totime, false, tasks, false);
            }
            tasks.add(task);
        } catch (Exception e) {
            str += "  ☹ OOPS!!! Please follow the format:\n"
                    + "event [task name] "
                    + "/from 01/02/2013 1820 /to 01/02/2013 2030 \n";
        }
        return str;
    }
}


