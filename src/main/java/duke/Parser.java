package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Arrays;

public class Parser {
    private FileManager fileManager;
    private TaskList taskList;

    public Parser(FileManager fileManager, TaskList taskList) {
        this.fileManager = fileManager;
        this.taskList = taskList;
    }

    /**
     * Returns void.
     * <p>
     * The userMessage argument must be of a certain format depending on the task to be added.
     * Examples showned below:
     * <p></p>
     * todo [Event description]
     * <p></p>
     * deadline [Event description] /by [Date in YYYY-MM-DD format]
     * <p></p>
     * event [Event description] /from [Date in YYYY-MM-DD format] /to [Date in YYYY-MM-DD format]
     * @param userMessage String input from the user
     */
    public String parse(String userMessage) {
        String [] parts = userMessage.split(" ", 2);

        if (parts[0].equals("bye")) {
            return bye();
        } else if (parts[0].equals("list")) {
            return list();
        } else if (parts[0].equals("mark")) {
            return mark(userMessage);
        } else if (parts[0].equals("unmark")) {
            return unmark(userMessage);
        } else if (parts[0].equals("delete")) {
            return delete(userMessage);
        } else if (parts[0].equals("todo")) {
            return todo(userMessage);
        } else if (parts[0].equals("deadline")) {
            return deadline(userMessage);
        } else if (parts[0].equals("event")) {
            return event(userMessage);
        } else if (parts[0].equals("find")) {
            return find(userMessage);
        } else {
            return "I have no idea what are you talking about dude..";
        }
    }

    private String bye() {
        this.fileManager.saveFile(this.taskList.getList());
        return "Cya boi";
    }

    private String list() {
        return this.taskList.list();
    }

    private String mark(String userMessage) {
        String [] messageParts = userMessage.split(" ", 2);
        try {
            int taskNumber = Integer.parseInt(messageParts[1]);
            assert taskNumber <= this.taskList.getList().size() : "Invalid Selection";
            return this.taskList.mark(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "Please specify which task to mark~  >:(";
        }
    }

    private String unmark(String userMessage) {
        String [] messageParts = userMessage.split(" ", 2);
        try {
            int taskNumber = Integer.parseInt(messageParts[1]);
            assert taskNumber <= this.taskList.getList().size() : "Invalid Selection";
            return this.taskList.unmark(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "Please specify which task to unmark~  >:(";
        }
    }

    private String delete(String userMessage) {
        String [] messageParts = userMessage.split(" ", 2);
        try {
            int taskNumber = Integer.parseInt(messageParts[1]);
            assert taskNumber <= this.taskList.getList().size() : "Invalid Selection";
            return this.taskList.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "Please specify which task to delete~  >:(";
        }
    }

    private String find(String userMessage) {
        String [] messageParts = userMessage.split(" ", 2);
        try {
            String keyword = messageParts[1];
            assert keyword.isEmpty() == false : "No keyword input";
            return this.taskList.find(keyword);
        } catch (IndexOutOfBoundsException e) {
            return "Bruhh.. You gotta tell me what to search for :<";
        }
    }

    private String todo(String userMessage) {
        String [] messageParts = userMessage.split(" ", 2);
        try {
            Todo newToDo = new Todo(messageParts[1]);
            return this.taskList.add(newToDo);
        } catch (IndexOutOfBoundsException e) {
            return "Bruhh.. The description of todo cannot be empty.";
        }
    }

    private String deadline(String userMessage) {
        String [] messageParts = userMessage.split(" ", 2);
        try {
            String[] deadlineParts = messageParts[1].split(" /");
            String[] deadline = deadlineParts[1].split(" ", 2);
            LocalDate byDate = LocalDate.parse(deadline[1]);
            Deadline newDeadline = new Deadline(deadlineParts[0], byDate);
            return this.taskList.add(newDeadline);
        } catch (IndexOutOfBoundsException e) {
            return "Bruhh.. The description of deadline cannot be empty.";
        } catch (DateTimeParseException e) {
            return "Incorrect date format dude.. try again~";
        }
    }

    private String event(String userMessage) {
        String [] messageParts = userMessage.split(" ", 2);
        try {
            String[] eventParts = messageParts[1].split(" /");
            String[] from = eventParts[1].split(" ", 2);
            String[] to = eventParts[2].split(" ", 2);
            LocalDate fromDate = LocalDate.parse(from[1]);
            LocalDate toDate = LocalDate.parse(to[1]);
            Event newEvent = new Event(eventParts[0], fromDate, toDate);
            return this.taskList.add(newEvent);
        } catch (IndexOutOfBoundsException e) {
            return "Bruhh.. The description of event cannot be empty.";
        } catch (DateTimeParseException e) {
            return "Incorrect date format dude.. try again~";
        }
    }
}
