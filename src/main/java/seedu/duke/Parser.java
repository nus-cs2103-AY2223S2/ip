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
        } else if (command.matches("todo(.*)")) {
            return createTask(command, todolist);
        } else if (command.matches("deadline(.*)")) {
            return createDeadlineTask(command, todolist);
        } else if (command.matches("event(.*)")) {
            return createEventTask(command, todolist);
        } else if (command.matches("delete(.*)")) {
            return createDeleteTask(command, todolist);
        } else if (command.matches("findtask(.*)")) {
            return findTask(command, todolist);
        } else if (command.matches("findtime(.*)")) {
            return findTime(command, todolist);
        } else if (command.matches("how many days until my next deadline")) {
            return findDaysUntilNextDeadline(todolist);
        } else if (command.matches("find free times")) {
            return findDaysUntilNextDeadline(todolist);
        } else if (command.matches("find(.*)")) {
            return askUserWhichFind();
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
            taskExistChecker(todolist, taskNum);
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
            taskExistChecker(todolist, taskNum);
            todolist.unmark(taskNum);
        } catch (NumberFormatException e) {
            return ("Which task would you like to unmark sir?");
        } catch (NoTaskFoundException e) {
            return ("Sir, that task does not exist.");
        }
        return todolist.list();
    }

    /**
     * Asks user for specific find command.
     *
     * @return String of question to ask
     */
    public String askUserWhichFind() {
        return ("Which find command are you looking for?\nfindtask or findtime?");
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
        return todolist.findTaskIfExist(task);
    }

    /**
     * Finds the timeslot indicated by the user.
     *
     * @param command the timeslot to find
     * @param todolist the current todolist
     * @return Confirmation of found timeslots, or timeslot not found
     */
    public String findTime(String command, ToDoList todolist) {
        int spacer = command.indexOf(" ");
        String timeLength = command.substring(spacer + 1);
        return todolist.findTimeslot(timeLength);
    }

    /**
     * Creates a new Task.
     *
     * @param command the user input
     * @param todolist the todolist to save the created Task
     * @return Confirmation of creation of Task, or error message
     */
    public String createTask(String command, ToDoList todolist) {
        int lengthOfStringTodo = 4;
        int spacer = command.indexOf(" ");
        if (spacer == -1) {
            return "Please enter in format 'todo <task>'";
        }
        try {
            String task = command.substring(lengthOfStringTodo + 1);
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
        boolean isValidCommand = command.contains("/");
        int lengthOfStringDeadline = 8;
        if (isValidCommand) {
            int firstSlashIndex = command.indexOf("/");
            String task = command.substring(lengthOfStringDeadline + 1, firstSlashIndex);
            String time = command.substring(firstSlashIndex + 1);

            try {
                LocalDate deadlineParsed = LocalDate.parse(time);
                validDateChecker(deadlineParsed);
                todolist.add(task, deadlineParsed);
            } catch (NotValidDateException e) {
                System.out.println(e);
                return "Please enter date that is current.";
            } catch (DateTimeParseException e) {
                return ("Wrong date format\nInput date format 'event <task> /<YYYY-MM-DD>'");
            }
        } else {
            return ("Please enter in format 'deadline <task> /<deadline>'");
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
        int lengthOfStringEvent = 5;
        int firstSlashIndex = command.indexOf("/");
        String timeString = command.substring(firstSlashIndex + 1);
        boolean isValidCommand = firstSlashIndex == -1 || !timeString.contains("/");
        if (isValidCommand) {
            int secondSlashIndex = timeString.indexOf("/") + firstSlashIndex + 1;
            String startTime = command.substring(firstSlashIndex + 1, secondSlashIndex);
            String endTime = command.substring(secondSlashIndex + 1);
            String task = command.substring(lengthOfStringEvent + 1, firstSlashIndex);

            try {
                LocalDate startTimeParsed = LocalDate.parse(startTime);
                LocalDate endTimeParsed = LocalDate.parse(endTime);
                todolist.add(task, startTimeParsed, endTimeParsed);
            } catch (DateTimeParseException e) {
                return ("Wrong date format\nInput date format 'event <task> /<YYYY-MM-DD>/<YYYY-MM-DD>'");
            }
        } else {
            return ("\tPlease enter in format 'event <task> /<start>/<end>'");
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
            taskExistChecker(todolist, taskNum);
            todolist.delete(taskNum);
        } catch (NumberFormatException e) {
            return "Which task would you like to delete, sir?";
        } catch (NoTaskFoundException e) {
            return "Sir, you may not delete nonexistent tasks.";
        }
        return todolist.list();
    }

    /**
     * Finds time (in days) until next deadline.
     *
     * @param todolist the current todolist
     * @return Confirmation of found time length, or error message
     */
    public String findDaysUntilNextDeadline(ToDoList todolist) {
        return todolist.findDaysUntilNextDeadline();
    }

    /**
     * Checks whether the Task number exists.
     *
     * @param toDo the current ToDoList
     * @param num the task number
     * @throws NoTaskFoundException If no task at specified number exists
     */
    public void taskExistChecker(ToDoList toDo, int num) throws NoTaskFoundException {
        if (num > toDo.getCount()) {
            throw new NoTaskFoundException("");
        }
    }

    /**
     * Checks if date provided is not in the past.
     *
     * @param date date provided
     * @throws NotValidDateException If date not valid
     */
    public void validDateChecker(LocalDate date) throws NotValidDateException {
        if (date.isBefore(Duke.getCurrDate())) {
            throw new NotValidDateException("Ensure date is not in the past.");
        }
    }
}
