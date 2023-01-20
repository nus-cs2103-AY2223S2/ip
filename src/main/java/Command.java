import exceptions.SaturdayException;
import models.Deadline;
import models.Event;
import models.Task;
import models.ToDo;
import utilities.Extensions;

public enum Command {
    TODO("todo") {
        @Override
        public void execute(String args) {
            int space = args.indexOf(" ");
            if (space != -1) {
                String description = args.substring(args.indexOf(" ") + 1);
                ToDo task = new ToDo(description);
                Saturday.addToTaskList(task);
            } else {
                throw new SaturdayException("OOPS!!! The description of a todo cannot be empty");
            }
        }
    },
    DEADLINE("deadline") {
        @Override
        public void execute(String args) {
            int space = args.indexOf(" ");
            int by = args.indexOf("/by");
            if (space != -1 && by != -1 && by > space) {
                String description = args.substring(space + 1, by - 1);
                String deadline = args.substring(by + 4);
                Deadline task = new Deadline(description, deadline);
                Saturday.addToTaskList(task);
            } else {
                throw new SaturdayException("OOPS!!! The deadline cannot be empty (use /by)");
            }
        }
    },
    EVENT("event") {
        @Override
        public void execute(String args) {
            int space = args.indexOf(" ");
            int from = args.indexOf("/from");
            int to = args.indexOf("/to");
            if (space != -1 && from != -1 && to != -1 && to > from && from > space) {
                String description = args.substring(space + 1, from - 1);
                String start = args.substring(from + 6, to - 1);
                String end = args.substring(to + 4);
                Event task = new Event(description, start, end);
                Saturday.addToTaskList(task);
            } else {
                throw new SaturdayException("OOPS!!! The timeframe cannot be empty (use /from and /to)");
            }
        }
    },
    LIST("list") {
        @Override
        public void execute(String args) {
            Saturday.displayList();
        }
    },
    MARK("mark") {
        @Override
        public void execute(String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.valueOf(number);
                    try {
                        Saturday.markTaskList(i);
                        Extensions.output("Nice! I've marked this task as done:\n\t  " + Saturday.getTask(i));
                    } catch (IndexOutOfBoundsException e) {
                        Extensions.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    UNMARK("unmark") {
        @Override
        public void execute(String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.valueOf(number);
                    try {
                        Saturday.unMarkTaskList(i);
                        Extensions.output("OK, I've marked this task as not done yet:\n\t  " + Saturday.getTask(i));
                    } catch (IndexOutOfBoundsException e) {
                        Extensions.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    DELETE("delete") {
        @Override
        public void execute(String args) {
            String[] parts = args.split("\\s");
            if (parts.length > 1) {
                String number = parts[1];
                if (number.matches("^\\d+")) {
                    int i = Integer.valueOf(number);
                    try {
                        Task removedTask = Saturday.removeTask(i);
                        Extensions.output("Noted. I've removed this task:\n\t  " + removedTask + "\n\tNow you have " + Saturday.getTaskListSize() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException e) {
                        Extensions.output("OOPS!!! There's no such task in your list");
                    }
                }
            } else {
                throw new SaturdayException("OOPS!!! Please input the number of the item you would like to mark");
            }
        }
    },
    BYE("bye") {
        @Override
        public void execute(String args) {
            Saturday.exit();
        }
    };

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public abstract void execute(String args);

    public static Command getCommand(String input) {
        for (Command c : Command.values()) {
            if (input.startsWith(c.command)) {
                return c;
            }
        }
        throw new SaturdayException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
