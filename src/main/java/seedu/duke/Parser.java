package seedu.duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Deals with understanding user commands.
 */
public class Parser {

    public Parser() {};

    /**
     * Parses the user commands.
     *
     * @param command user command
     * @param todolist the current ToDoList
     * @param storage the storage associated with the current ToDoList
     * @return isBye boolean indicated the ending 'bye' command
     */
    public String parse(String command, ToDoList todolist, Storage storage) {
        if (command.equals("bye")) {
            assert command.equals("bye") : "Command != 'bye'";
            return endDuke(storage, todolist);
        } else if (command.equals("list")) {
            assert command.equals("list") : "Command != 'list'";
            return todolist.list();
        } else if (command.matches("mark(.*)")) {
            return markTask(command, todolist);
        } else if (command.matches("unmark(.*)")) {
            return unmarkTask(command, todolist);
        } else if (command.matches("find(.*)")) {
            return findTask(command, todolist);
        } else if (command.matches("todo(.*)")) {
            return createTask(command, todolist);
        } else if (command.matches("deadline(.*)")) {
            return createDeadlineTask(command, todolist);
        } else if (command.matches("event(.*)")) {
            return createEventTask(command, todolist);
        } else if (command.matches("delete(.*)")) {
            return createDeleteTask(command, todolist);
        }
        return ("I do not know that command, sir.");
    }

    /**
     * Ends the current Duke program.
     *
     * @param storage the current storage
     * @param todolist the current todolist
     * @return Confirmation of ending process, or error message
     */
    public String endDuke(Storage storage, ToDoList todolist) {
        try {
            storage.saveTasks(todolist);
        } catch (IOException e) {
            return ("Unable to store properly.");
        }
        return ("Have a nice day sir.\nBye!");
    }

    /**
     * Marks the task indicated.
     *
     * @param command the user input
     * @param todolist the current todolist
     * @return Confirmation of marking, or error message
     */
    public String markTask(String command, ToDoList todolist) {
        try {
            int spacer = command.indexOf(" ");
            int taskNum = Integer.parseInt(command.substring(spacer + 1));
            validateTask(todolist, taskNum);
            todolist.markDone(taskNum);
        } catch (NumberFormatException e) {
            return ("Which task have you completed, sir?");
        } catch (NoTaskFoundException e) {
            return ("Sir, that task does not exist.");
        }
        return todolist.list();
    }

    /**
     * Unmarks the task indicated.
     *
     * @param command the user input
     * @param todolist the current todolist
     * @return Confirmation of unmarking, or error message
     */
    public String unmarkTask(String command, ToDoList todolist) {
        try {
            int spacer = command.indexOf(" ");
            int taskNum = Integer.parseInt(command.substring(spacer + 1));
            validateTask(todolist, taskNum);
            todolist.unmark(taskNum);
        } catch (NumberFormatException e) {
            return ("Which task would you like to unmark sir?");
        } catch (NoTaskFoundException e) {
            return ("Sir, that task does not exist.");
        }
        return todolist.list();
    }

    /**
     * Finds the task indicated by the user.
     *
     * @param command the task to find
     * @param todolist the current todolist
     * @return Confirmation of found Task, or Task not found
     */
    public String findTask(String command, ToDoList todolist) {
        int spacer = command.indexOf(" ");
        String task = command.substring(spacer + 1);
        return todolist.printTaskIfExist(task);
    }

    /**
     * Creates a new Task.
     *
     * @param command the user input
     * @param todolist the todolist to save the created Task
     * @return Confirmation of creation of Task, or error message
     */
    public String createTask(String command, ToDoList todolist) {
        int spacer = command.indexOf(" ");
        if (spacer == -1) {
            return "Please enter in format 'todo <task>'";
        }
        try {
            String task = command.substring(5);
            todolist.add(task);
        } catch (StringIndexOutOfBoundsException e) {
            return ("Please enter in format 'todo <task>'");
        }
        return todolist.list();
    }

    /**
     * Creates a new Deadline task.
     *
     * @param command the user input
     * @param todolist the todolist to save the created Task
     * @return Confirmation of creation of Deadline, or error message
     */
    public String createDeadlineTask(String command, ToDoList todolist) {
        // check format
        if (!command.contains("/")) {
            return ("Please enter in format 'deadline <task> /<deadline>'");
        } else {
            int firstSlash = command.indexOf("/");
            String task = command.substring(9, firstSlash);
            String time = command.substring(firstSlash + 1);

            try {
                LocalDate startTimeParsed = LocalDate.parse(time);
                todolist.add(task, startTimeParsed);
            } catch (DateTimeParseException e) {
                return ("Wrong date format\nInput date format 'event <task> /<YYYY-MM-DD>'");
            }
        }
        return todolist.list();
    }

    /**
     * Creates a new Event task.
     *
     * @param command the user input
     * @param todolist the todolist to save the created Task
     * @return Confirmation of creation of Event, or error message
     */
    public String createEventTask(String command, ToDoList todolist) {
        int firstSlash = command.indexOf("/");
        // check format
        if (firstSlash == -1 || !command.substring(firstSlash + 1).contains("/")) {
            return ("\tPlease enter in format 'event <task> /<start>/<end>'");
        } else {
            int secondSlash = command.substring(firstSlash + 1).indexOf("/") + firstSlash + 1;
            String startTime = command.substring(firstSlash + 1, secondSlash);
            String endTime = command.substring(secondSlash + 1);
            String task = command.substring(6, firstSlash);

            try {
                LocalDate startTimeParsed = LocalDate.parse(startTime);
                LocalDate endTimeParsed = LocalDate.parse(endTime);
                todolist.add(task, startTimeParsed, endTimeParsed);
            } catch (DateTimeParseException e) {
                return ("Wrong date format\nInput date format 'event <task> /<YYYY-MM-DD>/<YYYY-MM-DD>'");
            }
        }
        return todolist.list();
    }

    /**
     * Creates a new Delete task.
     *
     * @param command the user input
     * @param todolist the todolist to save the created Task
     * @return Confirmation of creation of Delete, or error message
     */
    public String createDeleteTask(String command, ToDoList todolist) {
        try {
            int spacer = command.indexOf(" ");
            int taskNum = Integer.parseInt(command.substring(spacer + 1));
            validateTask(todolist, taskNum);
            todolist.delete(taskNum);
        } catch (NumberFormatException e) {
            return "Which task would you like to delete, sir?";
        } catch (NoTaskFoundException e) {
            return "Sir, you may not delete nonexistent tasks.";
        }
        return todolist.list();
    }

    /**
     * Checks whether the Task number exists.
     *
     * @param toDo the current ToDoList
     * @param num the task number
     * @throws NoTaskFoundException If no task at specified number exists
     */
    public static void validateTask(ToDoList toDo, int num) throws NoTaskFoundException {
        if (num > toDo.getCount()) {
            throw new NoTaskFoundException("");
        }
    }
}
