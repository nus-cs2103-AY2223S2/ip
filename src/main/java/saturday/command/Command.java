package saturday.command;

import saturday.collections.TaskList;
import saturday.exceptions.SaturdayException;
import saturday.models.Deadline;
import saturday.models.Event;
import saturday.models.Task;
import saturday.models.ToDo;
import saturday.utilities.DateTimeParser;
import saturday.utilities.Ui;
/**
 * Enum class for handling different commands.
 */
public enum Command {
    /**
     * Command for creating a ToDo task.
     */
    TODO("todo") {
        @Override
        public void execute(TaskList taskList, String args) {
            int space = args.indexOf(" ");
            if (space != -1) {
                String description = args.substring(args.indexOf(" ") + 1);
                ToDo task = new ToDo(description);
                taskList.add(task);
                Ui.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size()
                        + " tasks in the list.");
            } else {
                throw new SaturdayException("OOPS!!! The description of a todo cannot be empty");
            }
        }
    },
    /**
     * Command for creating a Deadline task.
     */
    DEADLINE("deadline") {
        @Override
        public void execute(TaskList taskList, String args) {
            int space = args.indexOf(" ");
            int by = args.indexOf("/by");
            if (space != -1 && by != -1 && by > space) {
                String description = args.substring(space + 1, by - 1);
                String deadline = args.substring(by + 4);
                Deadline task = new Deadline(description, deadline);
                taskList.add(task);
                Ui.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size()
                        + " tasks in the list.");
            } else {
                throw new SaturdayException("OOPS!!! The deadline cannot be empty (use /by)");
            }
        }
    },
    /**
     * Command for creating an Event task.
     */
    EVENT("event") {
        @Override
        public void execute(TaskList taskList, String args) {
            int space = args.indexOf(" ");
            int from = args.indexOf("/from");
            int to = args.indexOf("/to");
            if (space != -1 && from != -1 && to != -1 && to > from && from > space) {
                String description = args.substring(space + 1, from - 1);
                String start = args.substring(from + 6, to - 1);
                String end = args.substring(to + 4);
                Event task = new Event(description, start, end);
                taskList.add(task);
                Ui.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size()
                        + " tasks in the list.");
            } else {
                throw new SaturdayException("OOPS!!! The timeframe cannot be empty (use /from and /to)");
            }
        }
    },
    /**
     * Command for displaying the TaskList.
     */
    LIST("list") {
        @Override
        public void execute(TaskList taskList, String args) {
            int on = args.indexOf("/on");
            if (args.equals("list")) {
                Ui.output("Here are the tasks in your list:\n\t" + taskList.toString());
            } else if (on != -1) {
                String date = args.substring(on + 4);
                TaskList taskListOnDate = taskList.getTaskListOnDate(date);
                Ui.output("Here are the tasks on: " + DateTimeParser.printDateTime(DateTimeParser.parseDate(date))
                        + "\n\t" + taskListOnDate.toString());
            } else {
                throw new SaturdayException("OOPS!!! Input a valid date to check your list against");
            }
        }
    },
    /**
     * Command for marking a task.
     */
    MARK("mark") {
        @Override
        public void execute(TaskList taskList, String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.parseInt(number);
                    try {
                        taskList.mark(i);
                        Ui.output("Nice! I've marked this task as done:\n\t  " + taskList.get(i));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    /**
     * Command for unmarking a task.
     */
    UNMARK("unmark") {
        @Override
        public void execute(TaskList taskList, String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.parseInt(number);
                    try {
                        taskList.unMark(i);
                        Ui.output("OK, I've marked this task as not done yet:\n\t  " + taskList.get(i));
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    /**
     * Command for deleting a task.
     */
    DELETE("delete") {
        @Override
        public void execute(TaskList taskList, String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.parseInt(number);
                    try {
                        Task removedTask = taskList.remove(i);
                        Ui.output("Noted. I've removed this task:\n\t  " + removedTask + "\n\tNow you have "
                                + taskList.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        Ui.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    FIND("find") {
        @Override
        public void execute(TaskList taskList, String args) {
            int space = args.indexOf(" ");
            if (space != 1) {
                String query = args.substring(args.indexOf(" ") + 1);
                TaskList queriedTaskList = taskList.find(query);
                Ui.output("Here are the tasks in your list:\n\t" + queriedTaskList.toString());
            } else {
                throw new SaturdayException("OOPS!!! What is it you're trying to find?");
            }
        }
    },
    /**
     * Command for exiting the application.
     */
    BYE("bye") {
        @Override
        public void execute(TaskList taskList, String args) {}
    };

    /**
     * A String command name to identify the class of command.
     */
    private final String command;

    /**
     * Constructor for the Command class.
     * Initializes the command.
     *
     * @param command The command to identify the instance with
     */
    Command(String command) {
        this.command = command;
    }

    /**
     * Method to execute the command on the active TaskList.
     *
     * @abstract
     * @param taskList The TaskList to execute the command on
     * @param args The instructions of the command
     */
    public abstract void execute(TaskList taskList, String args);

    /**
     * Method to identify the command to be used on the program.
     *
     * @param input The input to parse and process
     * @return A Command
     * @throws SaturdayException If unable to find a suitable Command
     */
    public static Command getCommand(String input) {
        for (Command c : Command.values()) {
            if (input.startsWith(c.command)) {
                return c;
            }
        }
        throw new SaturdayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
