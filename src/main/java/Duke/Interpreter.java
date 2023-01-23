package Duke;

import Duke.Exceptions.*;
import Duke.Tasks.*;
import java.lang.String;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * The Duke.Interpreter interpreters the users' input
 */
public class Interpreter {
    private final static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static enum Operation {
        // all the operations
        mark, list, delete, deadline, event, bye, todo, unmark, find
    }

    // format the time in the form of 11/10/2019 5pm

    /**
     * The method mark marks the Task as done
     * @param command the command by the user
     * @param table the task table
     * @return Task
     * @throws InvalidCommandException
     * @throws OutRangeException
     */
    public static Task mark(String command, TaskTable table) throws InvalidCommandException, OutRangeException {
        int len = table.size();
        int index;
        if (command.split(" ").length != 2) {
            throw new InvalidCommandException();
        } else {
            try {
                // sample command "mark 3"
                // split by the space, turn "3" into Integer and -1
                index = Integer.parseInt(command.split(" ")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException();
            }
            if (index > len - 1) {
                throw new OutRangeException();
            } else {
                return new Mark(index);
            }
        }
    }

    /**
     * The method unmark unmarks the Task as undone
     * @param command the command from the user
     * @param table the task table
     * @return Task
     * @throws InvalidCommandException
     * @throws OutRangeException
     */
    public static Task unmark(String command, TaskTable table) throws InvalidCommandException, OutRangeException {
        int len = table.size();
        int index;
        if (command.split(" ").length != 2) {
            throw new InvalidCommandException();
        } else {
            try {
                // sample command "mark 3"
                // split by the space, turn "3" into Integer and -1
                index = Integer.parseInt(command.split(" ")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException();
            }
            if (index > len - 1) {
                throw new OutRangeException();
            } else {
                return new Unmark(index);
            }
        }
    }

    /**
     * The method delete deletes the certain Task
     * @param command the command from the user
     * @param table the task table
     * @return Task
     * @throws InvalidCommandException
     * @throws OutRangeException
     */
    public static Task delete(String command, TaskTable table) throws InvalidCommandException, OutRangeException {
        int len = table.size();
        int index;
        if (command.split(" ").length != 2) {
            throw new InvalidCommandException();
        } else {
            try {
                // sample command "mark 3"
                // split by the space, turn "3" into Integer and -1
                index = Integer.parseInt(command.split(" ")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidCommandException();
            }
            if (index > len - 1) {
                throw new OutRangeException();
            } else {
                return new Delete(index);
            }
        }
    }

    /**
     * The method addTask adds new task to the Task table
     * @param command the command from the user
     * @param table the task table
     * @return Task
     * @throws InvalidTimeFormatException
     * @throws MissingDescriptionException
     * @throws EmptyCommandException
     */
    public static Task addTask(String command, TaskTable table) throws InvalidTimeFormatException,
            MissingDescriptionException, EmptyCommandException {
        int size = table.size(); // get the current size of TaskTable
        // get the kind of operation
        Interpreter.Operation op = Interpreter.Operation.valueOf(command.toLowerCase().split(" ")[0]);
        if (command.split(" ").length == 1) {
            // if after splitting, there is only array of length 1
            switch (op) {
                case deadline:
                    throw new MissingDescriptionException(op.name());
                case event:
                    throw new MissingDescriptionException(op.name());
                case todo:
                    throw new MissingDescriptionException(op.name());
                default:
                    // when the input is ""
                    throw new EmptyCommandException();
            }

        } else {
            switch (op) {
                case deadline:
                    // e.g. "deadline submit report by 11/10/2019 5pm"
                    String deadlineAndTime = command.substring(9);
                    if (deadlineAndTime.split("/by").length != 2 || deadlineAndTime.split("/by")[0] == "")  {
                        // if after splitting by "by", the length is not 2; or "by" is not even in the command
                        // System.out.println("wwowowoowowowowo");
                        throw new MissingDescriptionException("deadline");
                    } else {
                        try {
                            // System.out.println("wwowowoowowowowo");
                            String deadlineName = (deadlineAndTime.split(" /by ")[0]);
                            LocalDateTime deadlineTime = LocalDateTime.parse(deadlineAndTime.split(" /by ")[1], format);
                            return new Deadline(deadlineName, deadlineTime, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new InvalidTimeFormatException();
                        }
                    }
                case todo:
                    if (command.split(" ").length == 1) {
                        // if the input is normal
                        throw new MissingDescriptionException(op.name());
                    } else {
                        return new Todo(command.substring(5), false);
                    }
                case event:
                    String eventAndTime = command.substring(6);
                    if (eventAndTime.split("/from").length != 2) {
                        throw new InvalidTimeFormatException();
                    } else {
                        try {
                            String eventName = eventAndTime.split(" /from ")[0];
                            String eventTime = eventAndTime.split(" /from ")[1];
                            LocalDateTime startTime = LocalDateTime.parse(eventTime.split(" /to ")[0], format);
                            LocalDateTime endTime = LocalDateTime.parse(eventTime.split(" /to ")[1], format);
                            return new Event(
                                    eventName, startTime, endTime, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new InvalidTimeFormatException();
                        }
                    }

            }

        }
        return null; // to be modified
    }

    public static Find find(String command, TaskTable table) throws MissingDescriptionException {
        if (command.split(" ").length == 1) {
            throw new MissingDescriptionException("find method");
        } else {
            return new Find(command.substring(5), table);
        }
    }


    /**
     * The method interpret interprets the command
     * @param command the command from the user
     * @param table the task table
     * @return Task
     * @throws InvalidTimeFormatException
     * @throws MissingDescriptionException
     * @throws EmptyCommandException
     * @throws InvalidCommandException
     */
    public static Task interpret(String command, TaskTable table) throws InvalidTimeFormatException,
            MissingDescriptionException, EmptyCommandException, InvalidCommandException {
        Operation op;
        try{
            try {
                op = Interpreter.Operation.valueOf(command.toLowerCase().split(" ")[0]);
            } catch (Exception e) {
                throw new InvalidCommandException();
            }
            // System.out.println(op);
            switch (op) {
                case mark:
                    return mark(command, table);
                case unmark:
                    return unmark(command, table);
                case todo:
                    return addTask(command, table);
                case deadline:
                    return addTask(command, table);
                case event:
                    return addTask(command, table);
                case bye:
                    return new Exit();
                case delete:
                    return delete(command, table);
                case list:
                    return new Table();
                case find:
                    return find(command, table);
                default:
                    throw new InvalidCommandException();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("    ____________________________________________________________");
            System.out.println(e.getMessage());
            System.out.println("    ____________________________________________________________\n");
            return null;
        }
    }
}
