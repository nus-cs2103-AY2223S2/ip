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
            return showList(tasks);
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
     * Shows list details
     * @param task task
     */
    public static String showList(TaskList task) {
        StringBuilder str = new StringBuilder();

        if (task.size() == 0) {
            return "Sorry! There is no task in the list!";
        }
        str.append(INDENTATION + "Here are the tasks in your list: ");
        str.append(System.getProperty("line.separator"));

        for (int i = 0; i < task.size(); i++) {
            str.append(INDENTATION).append(i + 1).append(". ").append(task.get(i).toString());
            str.append(System.getProperty("line.separator"));
        }

        str.append(INDENTATION).append("There are ").append(task.size()).append(" tasks right now!");
        str.append(System.getProperty("line.separator"));
        return str.toString();
    }


    /**
     * Sorts the command in 3 different groups: to-do,deadline, event
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
                    str.append("❤ ").append(task).append("\n");
                }
                return str.toString();
            } else {
                return "Sorry, there is no record of the specified type!";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return "  ☹ OOPS!!! The format of sort command: sort [todo/deadline/event]!\n";
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
        } catch (ArrayIndexOutOfBoundsException e) {
            str += "  ☹ OOPS!!! The index number cannot be empty. \n";
        } catch (IndexOutOfBoundsException e) {
            str += "  ☹ OOPS!!! The index number is out of bounds. \n";
        } catch (NumberFormatException e) {
            str += "  ☹ OOPS!!! The index number cannot be string. \n";
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
        } catch (ArrayIndexOutOfBoundsException e) {
            str += "  ☹ OOPS!!! The index number cannot be empty. \n";
        } catch (IndexOutOfBoundsException e) {
            str += "  ☹ OOPS!!! The index number is out of bounds. \n";
        } catch (NumberFormatException e) {
            str += "  ☹ OOPS!!! The index number cannot be string. \n";
        }

        return str;
    }

    /**
     * Deletes the task
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
        } catch (ArrayIndexOutOfBoundsException e) {
            str += "  ☹ OOPS!!! The index number cannot be empty. \n";
        } catch (IndexOutOfBoundsException e) {
            str += "  ☹ OOPS!!! The index number is out of bounds. \n";
        } catch (NumberFormatException e) {
            str += "  ☹ OOPS!!! The index number cannot be string. \n";
        }
        return str;
    }

    /**
     * Finds the task
     * @param cmd command
     * @param  tasks task
     * @return find result
     */
    public static String findCommand(String cmd, TaskList tasks) {
        StringBuilder str = new StringBuilder();
        boolean isInList = false;
        String command = cmd.trim();
        String[] words = command.split(" ");
        String temp = "";

        try {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getDescription().contains(words[1])) {
                    isInList = true;
                    temp += INDENTATION + (i + 1) + ". " + tasks.get(i).toString() + "\n";
                }
            }
            if (isInList == true) {
                str.append(INDENTATION + "Here are the matching tasks in your list:\n");
                str.append(temp + "\n");
            } else {
                str.append("Sorry! There is no result!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            str.append("  ☹ OOPS!!! The description of a find cannot be empty. \n");
        }



        return str.toString();
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
                task = new Todo(info, false);
                tasks.add(task);
                str += "Got it! I've added this task: \n";
                str += task;
                str += "\n";
                str += "Now you have " + tasks.size() + " tasks in the list!\n";
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            str += "☹ OOPS!!! The description of a todo cannot be empty.\n";
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
                    datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")), false);
            str += "Got it! I've added this task: \n";
            str += task;
            tasks.add(task);
            str += "\n";
            str += "Now you have " + tasks.size() + " tasks in the list!\n";
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            str += "  ☹ OOPS!!! Please follow the format: \n"
                    + "deadline [task name] /by MM/dd/yyyy HHmm \n";
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

            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern((" MM/dd/yyyy HHmm"));
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern((" MM/dd/yyyy HHmm"));
            LocalDateTime datetime1 = LocalDateTime.parse(fromtime, formatter1);
            LocalDateTime datetime2 = LocalDateTime.parse(totime, formatter2);

            task = new Event(info,
                    datetime1.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                    datetime2.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")),
                    false);

            str += "Got it! I've added this task: \n";
            str += task;
            tasks.add(task);
            str += "\n";
            str += "Now you have " + tasks.size() + " tasks in the list!\n";
        } catch (StringIndexOutOfBoundsException | DateTimeParseException e) {
            str += "  ☹ OOPS!!! Please follow the format: \n"
                    + "event [task name] /from MM/dd/yyyy HHmm /to MM/dd/yyyy HHmm\n";
        }
        return str;
    }
}


