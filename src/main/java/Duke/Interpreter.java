package Duke;

import Duke.Exceptions.*;
import Duke.Tasks.Mark;
import Duke.Tasks.Unmark;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Exit;
import Duke.Tasks.Table;
import Duke.Tasks.Delete;


import java.lang.String;

/**
 * The Interpreter interpreters the users' input
 */
public class Interpreter {
    private static enum Operation {
        // all the operations
        mark, list, delete, deadline, event, bye, todo, unmark
    }

    // format the time in the form of 11/10/2019 5pm

    /**
     * The method mark marks the Task as done
     * @param command
     * @param table
     * @return Task
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
     * @param command
     * @param table
     * @return Task
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
     * @param command
     * @param table
     * @return Task
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
     * The method addTask adds new task to the Tasktable
     * @param command
     * @param table
     * @return Task
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
                    if (deadlineAndTime.split("/by").length != 2) {
                        // if after splitting by "by", the length is not 2; or "by" is not even in the command
                        // System.out.println("wwowowoowowowowo");
                        throw new InvalidTimeFormatException();
                    } else {
                        try {
                            // System.out.println("wwowowoowowowowo");
                            String deadlineName = deadlineAndTime.split(" /by ")[0];
                            String deadlineTime = deadlineAndTime.split(" /by ")[1];
                            return new Deadline(deadlineName, deadlineTime, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new InvalidTimeFormatException();
                        }
                    }
                case todo:
                    if (command.split(" ").length == 2) {
                        // if the input is normal
                        return new Todo(command.substring(5), false);
                    } else {
                        throw new MissingDescriptionException(op.name());
                    }
                case event:
                    String eventAndTime = command.substring(6);
                    if (eventAndTime.split("/from|\\/to").length != 3) {
                        throw new InvalidTimeFormatException();
                    } else {
                        try {
                            String eventName = eventAndTime.split("/from")[0];
                            String eventTime = eventAndTime.split("/from")[1];
                            String startTime = eventTime.split("/to")[0];
                            String endTime = eventTime.split("/to")[1];
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

    /**
     * The method interpret interprets the command
     * @param command
     * @param table
     * @return Task
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
                default:
                    throw new InvalidCommandException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
