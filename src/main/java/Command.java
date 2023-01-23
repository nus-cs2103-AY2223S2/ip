import collections.TaskList;
import exceptions.SaturdayException;
import models.Deadline;
import models.Event;
import models.Task;
import models.ToDo;
import utilities.UI;

public enum Command {
    TODO("todo") {
        @Override
        public void execute(TaskList taskList, String args) {
            int space = args.indexOf(" ");
            if (space != -1) {
                String description = args.substring(args.indexOf(" ") + 1);
                ToDo task = new ToDo(description);
                taskList.add(task);
                UI.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size() + " tasks in the list.");
            } else {
                throw new SaturdayException("OOPS!!! The description of a todo cannot be empty");
            }
        }
    },
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
                UI.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size() + " tasks in the list.");
            } else {
                throw new SaturdayException("OOPS!!! The deadline cannot be empty (use /by)");
            }
        }
    },
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
                UI.output("Got it. I've added this task:\n\t " + task + "\n\tNow you have " + taskList.size() + " tasks in the list.");
            } else {
                throw new SaturdayException("OOPS!!! The timeframe cannot be empty (use /from and /to)");
            }
        }
    },
    LIST("list") {
        @Override
        public void execute(TaskList taskList, String args) {
            UI.output("Here are the tasks in your list:\n\t" + taskList.toString());
        }
    },
    MARK("mark") {
        @Override
        public void execute(TaskList taskList, String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.valueOf(number);
                    try {
                        taskList.mark(i);
                        UI.output("Nice! I've marked this task as done:\n\t  " + taskList.get(i));
                    } catch (IndexOutOfBoundsException e) {
                        UI.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    UNMARK("unmark") {
        @Override
        public void execute(TaskList taskList, String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.valueOf(number);
                    try {
                        taskList.unMark(i);
                        UI.output("OK, I've marked this task as not done yet:\n\t  " + taskList.get(i));
                    } catch (IndexOutOfBoundsException e) {
                        UI.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    DELETE("delete") {
        @Override
        public void execute(TaskList taskList, String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.valueOf(number);
                    try {
                        Task removedTask = taskList.remove(i);
                        UI.output("Noted. I've removed this task:\n\t  " + removedTask + "\n\tNow you have " + taskList.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        UI.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    BYE("bye") {
        @Override
        public void execute(TaskList taskList, String args) {
            Saturday.exit();
        }
    };

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public abstract void execute(TaskList taskList, String args);

    public static Command getCommand(String input) {
        for (Command c : Command.values()) {
            if (input.startsWith(c.command)) {
                return c;
            }
        }
        throw new SaturdayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
