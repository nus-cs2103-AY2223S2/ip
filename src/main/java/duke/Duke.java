package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static duke.Parser.parseMark;
import static duke.Ui.*;

/**
 * Duke class that helps to handle and execute commands
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor method
     * @param filePath
     * @throws DukeException
     */
    public Duke(String filePath) throws DukeException {
        // Initialize the task list
        this.tasks = new TaskList();
        // Initialize the user interface
        this.ui = new Ui();
        // Initialize the storage object
        this.storage = new Storage(filePath);


        try {
            // Attempt to load tasks from storage
            this.tasks = this.storage.loadFile();
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String handleCommand(String s) throws DukeException {
        assert this.tasks.getTasks().size() >= 0: "Number of tasks should be not be a  negative number";assert s.length() >= 0: "number of letters in command should not be a negative number";

        // user enters list command
        try {
            if (Parser.isList(s)) {
                return displayTasks(tasks);

            } else if (Parser.isMark(s)) {

                int taskNumber = parseMark(s);
                executeMark(taskNumber, tasks);
                return displayMarked(taskNumber, tasks);

            } else if (Parser.isUnmark(s)) {
                int taskNumber = parseMark(s);
                executeUnmark(taskNumber, tasks);
                return displayUnmarked(taskNumber, tasks);

            } else if (Parser.isTodo(s)) {
                Todo todo = Parser.parseTodo(s);
                tasks.add(todo);
                return displayTask(todo);

            } else if (Parser.isDeadline(s)) {
                Deadline deadline = Parser.parseDeadline(s);
                tasks.add(deadline);
                return displayTask(deadline);

            } else if (Parser.isEvent(s)) {
                Event event = Parser.parseEvent(s);
                tasks.add(event);
                return displayTask(event);

            } else if (Parser.isDelete(s)) {
                return executeDelete(s);

            } else if (Parser.isFind(s)) {
                TaskList searchResults = executeFind(s, tasks);
                return displayFind(searchResults);

            } else if (Parser.isSnooze(s)) {
                executeSnooze(s, tasks);
                return displaySnooze(s, tasks);

            } else if (Parser.isBye(s)) {
                return "    Bye. Hope to see you soon!";

            } else {
                return "    OOPS!!! I'm sorry, but I don't know what that means :-(";

            }
        } catch (DukeException e ) {
            return e.getMessage();
        }
    }

    public String executeDelete(String s) throws DukeException {
        int index = Parser.getIndex(s);
        if (tasks.size() != 0 && index >= 0 && index <= tasks.size() - 1) {
            Task deletedTask = tasks.get(index);
            tasks.remove(index);
            return "    Noted. I've removed this task:\n      " + deletedTask +
                    "\n    Now you have " + tasks.size() + " tasks in the list";
        } else {
            throw new DukeException("Index Out Of Bounds!");
        }
    }


    public static TaskList executeFind(String s, TaskList tasks) {
        String findString = Parser.getFindable(s);
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++ ) {
            Task task = tasks.get(i);
            if (task.toString().contains(findString)) {
                foundTasks.add(task);
            }
        }
        return  foundTasks;
    }

    public static void executeMark(int taskNumber, TaskList tasks) throws DukeException {
        if ( taskNumber > tasks.size() - 1 || taskNumber < 0) {
            throw new DukeException("     OOPS! Index Out Of Bounds !");
        }
        tasks.get(taskNumber).mark();
    }

    public static void executeUnmark(int taskNumber, TaskList tasks) throws DukeException {
        if ( taskNumber > tasks.size() - 1 || taskNumber < 0) {
            throw new DukeException("     OOPS! Index Out Of Bounds !");
        }
        tasks.get(taskNumber).unmark();
    }

    /**
     *
     * @param s
     * @param tasks
     * @throws DukeException
     */
    public static void executeSnooze(String s, TaskList tasks) throws DukeException {
        //edit existing deadline
        //format: snooze 1 /to
        try {
            Parser.getTaskNum(s);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

        int taskNumber = Parser.getTaskNum(s) - 1;

        Task snoozedTask = tasks.get(taskNumber);
        if (!(snoozedTask instanceof Deadline)) {
            throw new DukeException("    OOPS!!! Task must be a deadline to snooze!");
        }

        Deadline deadline = (Deadline) snoozedTask;

        String date  = Parser.parseSnooze(s);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        try {
            LocalDate.parse(date, formatter);
        } catch(DateTimeParseException e) {
            throw new DukeException("Date is an invalid format! Should be yyyy-MM-dd HH:mm");
        }

        if (LocalDateTime.parse(date, formatter).compareTo(deadline.getDeadline()) > 0) {
            tasks.set(taskNumber, new Deadline(deadline.getDescription(), date));
            Deadline newDeadline = (Deadline) tasks.get(taskNumber);
            throw new DukeException(deadline + " has been snoozed to " + newDeadline.getDeadline());
        } else {
            throw new DukeException("    OOPS!!! Cannot snooze to an earlier or same timing!");
        }
    }

    TaskList getTasks() {
        return this.tasks;
    }

    /**
     * Takes an input and generates a response
     * @param input
     * @return response as a String
     */
    String getResponse(String input) {
        try {
            return handleCommand(input);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }


}

