package vic.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import vic.exceptions.ContentEmpty;
import vic.exceptions.DateParseException;
import vic.exceptions.DukeException;
import vic.exceptions.IncompleteCommandException;
import vic.exceptions.InvalidMarkInput;
import vic.tasks.ITask;


/**
 * Parser class to translate user input to valid program input
 */
public class Parser {
    /**
     * Pre-define input date format
     */
    public static final DateFormat DATE_IN_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Pre-define output date format
     */
    public static final DateFormat DATE_OUTPUT_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss aa");


    private boolean isProcessed = false;
    private String input;
    private int index;
    private String description;
    private ITask.TaskTypes type;
    private Date by;
    private Date from;
    private Date to;

    private TaskManager taskManager;

    /**
     * Constructor for Parser
     *
     * @param input input from user
     */
    public Parser(String input) {
        this.input = input;
    }

    /**
     * Constructor for Parser
     *
     * @param taskManager to handle to task
     */
    public Parser(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Constructor for Parser
     *
     * @param input       input from user
     * @param taskManager to handle to task
     */
    public Parser(String input, TaskManager taskManager) {
        this.input = input;
        this.taskManager = taskManager;
    }

    /**
     * Constructor for Parser
     *
     * @param input       input from user
     * @param taskManager to handle to task
     * @param type        of the command
     */
    public Parser(String input, TaskManager taskManager, ITask.TaskTypes type) {
        this.taskManager = taskManager;
        this.input = input;
        this.type = type;
    }


    /**
     * Returns the 'by' date of deadline task
     *
     * @throws DukeException IF error occur
     */
    public Date getBy() throws DukeException {
        assert isProcessed : "Processed the Parser first";

        if (by == null) {
            throw new ContentEmpty("'by'");
        }
        return by;

    }


    /**
     * Converts the user input to valid program input for a deadline
     * task
     *
     * @throws DukeException IF error occur
     */
    public void forDeadline() throws DukeException {
        if (!isProcessed) {
            if (!input.contains("/by")) {
                throw new IncompleteCommandException("/by");
            }

            String[] temp = input.split("/by");
            if (temp.length < 1) {
                throw new ContentEmpty("'/by command'");
            }
            description = temp[0].trim();

            try {
                by = DATE_IN_FORMAT.parse(temp[1].trim());
            } catch (ParseException e) {
                throw new DateParseException(e.getMessage());
            }

            isProcessed = true;
        }
    }

    /**
     * Returns the 'from' date of event task
     *
     * @throws DukeException IF error occur
     */
    public Date getFrom() throws DukeException {
        assert isProcessed : "Processed the Parser first";

        if (from == null) {
            throw new ContentEmpty("'from'");
        }
        return from;
    }

    /**
     * Returns the 'to' date of event task
     *
     * @throws DukeException IF error occur
     */
    public Date getTo() throws DukeException {
        assert isProcessed : "Processed the Parser first";

        if (to == null) {
            throw new ContentEmpty("'to'");
        }
        return to;
    }


    /**
     * Returns the enum type of task
     */
    public ITask.TaskTypes getType() {
        return type;
    }

    /**
     * Returns the task manager
     */

    public TaskManager getTaskManager() {
        return taskManager;
    }

    /**
     * Returns the index of task for a mark or unmark task
     * task
     *
     * @throws DukeException IF error occur
     */
    public int getIndex() throws DukeException {
        if (!isProcessed) {
            try {
                index = Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidMarkInput(input);
            }
            if (index < 0 || index > taskManager.size() - 1) {
                throw new InvalidMarkInput(input);
            }
            isProcessed = true;
        }
        return index;
    }

    /**
     * Returns the description of task
     *
     * @throws DukeException IF error occur
     */
    public String getDescription() throws DukeException {
        if (description.isEmpty()) {
            throw new ContentEmpty("'description'");
        }
        return description;
    }


    /**
     * Converts the user input to valid program input for an event
     * task
     *
     * @throws DukeException IF error occur
     */
    public void forEvent() throws DukeException {

        if (!input.contains("/from")) {
            throw new IncompleteCommandException("/from");
        }
        String[] result = new String[3];
        String[] temp = input.split("/from");
        result[0] = temp[0];
        String[] temp2 = temp[1].split("/to");

        if (!input.contains("/to")) {
            throw new IncompleteCommandException("/to");
        }
        result[1] = temp2[0];
        result[2] = temp2[1];
        try {
            from = DATE_IN_FORMAT.parse(result[1].trim());
            to = DATE_IN_FORMAT.parse(result[2].trim());
        } catch (ParseException e) {
            throw new DateParseException(e.getMessage());
        }
        description = result[0].trim();
        isProcessed = true;

    }

    /**
     * Converts the user input to valid program input for a todos
     * task
     */
    public void forTodo() {
        description = input;
        isProcessed = true;
    }

    /**
     * Converts the user input to valid program input for a Find
     * task
     */
    public void forFind() {
        description = input;
        isProcessed = true;
    }




}
