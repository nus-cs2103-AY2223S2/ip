package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class Parser {
    private FileManager fileManager;
    private TaskList taskList;
    static final int COMMAND_INDEX = 0;
    static final int TASK_INFO_INDEX = 1;
    static final int USER_MESSAGE_SPLIT_COUNT = 2;
    static final int DESCRIPTION_INDEX = 0;
    static final int DATE_SPLIT_COUNT = 2;
    static final int BY_INFO_INDEX = 1;
    static final int TO_INFO_INDEX = 2;
    static final int FROM_INFO_INDEX = 1;
    static final int DATE_INDEX = 1;
    static final int OPERATION_NUMBER_INDEX = 1;

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
        String [] parts = userMessage.split(" ", USER_MESSAGE_SPLIT_COUNT);

        if (parts[COMMAND_INDEX].equals("bye")) {
            return bye();
        } else if (parts[COMMAND_INDEX].equals("list")) {
            return list();
        } else if (parts[COMMAND_INDEX].equals("mark")) {
            return mark(userMessage);
        } else if (parts[COMMAND_INDEX].equals("unmark")) {
            return unmark(userMessage);
        } else if (parts[COMMAND_INDEX].equals("delete")) {
            return delete(userMessage);
        } else if (parts[COMMAND_INDEX].equals("todo")) {
            return todo(userMessage);
        } else if (parts[COMMAND_INDEX].equals("deadline")) {
            return deadline(userMessage);
        } else if (parts[COMMAND_INDEX].equals("event")) {
            return event(userMessage);
        } else if (parts[COMMAND_INDEX].equals("find")) {
            return find(userMessage);
        } else if (parts[COMMAND_INDEX].equals("help")) {
            return help();
        } else if (parts[COMMAND_INDEX].equals("sort")) {
            return sort();
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

    private String sort() {
        return this.taskList.sort();
    }

    private String mark(String userMessage) {
        String [] messageParts = userMessage.split(" ", USER_MESSAGE_SPLIT_COUNT);
        try {
            int taskNumber = Integer.parseInt(messageParts[OPERATION_NUMBER_INDEX]);
            assert taskNumber <= this.taskList.getList().size() : "Invalid Selection";
            return this.taskList.mark(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "Please specify which task to mark~  >:(";
        }
    }

    private String unmark(String userMessage) {
        String [] messageParts = userMessage.split(" ", USER_MESSAGE_SPLIT_COUNT);
        try {
            int taskNumber = Integer.parseInt(messageParts[OPERATION_NUMBER_INDEX]);
            assert taskNumber <= this.taskList.getList().size() : "Invalid Selection";
            return this.taskList.unmark(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "Please specify which task to unmark~  >:(";
        }
    }

    private String delete(String userMessage) {
        String [] messageParts = userMessage.split(" ", USER_MESSAGE_SPLIT_COUNT);
        try {
            int taskNumber = Integer.parseInt(messageParts[OPERATION_NUMBER_INDEX]);
            assert taskNumber <= this.taskList.getList().size() : "Invalid Selection";
            return this.taskList.remove(taskNumber);
        } catch (IndexOutOfBoundsException e) {
            return "Hello?! Which task to delete?~  >:(";
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
            Todo newToDo = new Todo(messageParts[TASK_INFO_INDEX]);
            return this.taskList.add(newToDo);
        } catch (IndexOutOfBoundsException e) {
            return "Bruhh.. The description of todo cannot be empty.";
        }
    }

    private String deadline(String userMessage) {
        String [] messageParts = userMessage.split(" ", 2);
        try {
            String[] deadlineParts = messageParts[TASK_INFO_INDEX].split(" /");
            String[] deadline = deadlineParts[BY_INFO_INDEX].split(" ", DATE_SPLIT_COUNT);
            LocalDate byDate = LocalDate.parse(deadline[DATE_INDEX]);
            Deadline newDeadline = new Deadline(deadlineParts[DESCRIPTION_INDEX], byDate);
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
            String[] eventParts = messageParts[TASK_INFO_INDEX].split(" /");
            String[] from = eventParts[FROM_INFO_INDEX].split(" ", DATE_SPLIT_COUNT);
            String[] to = eventParts[TO_INFO_INDEX].split(" ", DATE_SPLIT_COUNT);
            LocalDate fromDate = LocalDate.parse(from[DATE_INDEX]);
            LocalDate toDate = LocalDate.parse(to[DATE_INDEX]);
            Event newEvent = new Event(eventParts[DESCRIPTION_INDEX], fromDate, toDate);
            return this.taskList.add(newEvent);
        } catch (IndexOutOfBoundsException e) {
            return "Bruhh.. The description of event cannot be empty.";
        } catch (DateTimeParseException e) {
            return "Incorrect date format dude.. try again~\nAsk for 'help' if you have to~";
        }
    }

    private String help() {
        String header = "These are the functions and format: \n";
        String toDoFormat = "1) todo [Description]\n";
        String deadlineFormat = "2) deadline [Description] /by [YYYY-MM-DD]\n";
        String eventFormat = "3) event [Description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]\n";
        String markFormat = "4) mark [Task number]\n";
        String unmarkFormat = "5) unmark [Task number]\n";
        String deleteFormat = "6) delete [Task number]\n";
        String findFormat = "7) find [Keyword]\n";

        return header + toDoFormat + deadlineFormat + eventFormat
                + markFormat + unmarkFormat + deleteFormat + findFormat;
    }
}
