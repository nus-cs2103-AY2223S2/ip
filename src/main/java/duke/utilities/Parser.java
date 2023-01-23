package duke.utilities;

import duke.exceptions.ContentEmpty;
import duke.exceptions.DateParseException;
import duke.exceptions.DukeException;
import duke.exceptions.IncompleteCommandException;
import duke.exceptions.InvalidMarkInput;
import duke.tasks.ITask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Parser class to translate user input to valid program input
 */
public class Parser {
    private boolean processed = false;
    private String _input;
    private String _description;
    /**
     * Pre-define input date format
     */
    public final static DateFormat DATE_IN_FORMAT = new SimpleDateFormat("dd/MM/yyyy HHmm");

    /**
     * Pre-define output date format
     */
    public final static DateFormat DATE_OUTPUT_FORMAT = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss aa");


    /**
     * Return the 'by' date of deadline task
     *
     * @throws DukeException IF error occur
     */
    public Date getBy() throws DukeException {
        if (_by == null) {
            throw new ContentEmpty("'by'");
        }
        return _by;

    }


    /**
     * Convert the user input to valid program input for a deadline
     * task
     *
     * @throws DukeException IF error occur
     */
    public void forDeadline() throws DukeException {
        if (!processed) {
            if (!_input.contains("/by")) {
                throw new IncompleteCommandException("/by");
            }

            String[] temp = _input.split("/by");
            if (temp.length < 1) {
                throw new ContentEmpty("'/by command'");
            }
            _description = temp[0].trim();

            try {
                _by = DATE_IN_FORMAT.parse(temp[1].trim());
            } catch (ParseException e) {
                throw new DateParseException(e.getMessage());
            }

            processed = true;
        }
    }

    /**
     * Return the 'from' date of event task
     *
     * @throws DukeException IF error occur
     */
    public Date getFrom() throws DukeException {
        if (_from == null) {
            throw new ContentEmpty("'from'");
        }
        return _from;
    }

    /**
     * Return the 'to' date of event task
     *
     * @throws DukeException IF error occur
     */
    public Date getTo() throws DukeException {
        if (_to == null) {
            throw new ContentEmpty("'to'");
        }
        return _to;
    }

    private Date _by;
    private Date _from;
    private Date _to;


    private TaskManager _taskManager;

    /**
     * Return the enum type of task
     */
    public ITask.TaskTypes getType() {
        return _type;
    }

    private ITask.TaskTypes _type;
    private int _index;

    /**
     * Constructor for Parser
     *
     * @param input input from user
     */
    public Parser(String input) {
        _input = input;
    }

    /**
     * Constructor for Parser
     *
     * @param taskManager to handle to task
     */
    public Parser(TaskManager taskManager) {
        _taskManager = taskManager;
    }

    /**
     * Constructor for Parser
     *
     * @param input       input from user
     * @param taskManager to handle to task
     */
    public Parser(String input, TaskManager taskManager) {
        _input = input;
        _taskManager = taskManager;
    }

    /**
     * Constructor for Parser
     *
     * @param input       input from user
     * @param taskManager to handle to task
     * @param type        of the command
     */
    public Parser(String input, TaskManager taskManager, ITask.TaskTypes type) {
        _taskManager = taskManager;
        _input = input;
        _type = type;
    }

    /**
     * Return the task manager
     */

    public TaskManager getTaskManager() {
        return _taskManager;
    }

    /**
     * Return the index of task for a mark or unmark task
     * task
     *
     * @throws DukeException IF error occur
     */
    public int getIndex() throws DukeException {
        if (!processed) {
            try {
                _index = Integer.parseInt(_input) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidMarkInput(_input);
            }
            if (_index < 0 || _index > _taskManager.size() - 1) {
                throw new InvalidMarkInput(_input);
            }
            processed = true;
        }
        return _index;
    }

    /**
     * Return the description of task
     *
     * @throws DukeException IF error occur
     */
    public String getDescription() throws DukeException {
        if (_description.isEmpty()) {
            throw new ContentEmpty("'description'");
        }
        return _description;
    }


    /**
     * Convert the user input to valid program input for an event
     * task
     *
     * @throws DukeException IF error occur
     */
    public void forEvent() throws DukeException {

        if (!_input.contains("/from")) {
            throw new IncompleteCommandException("/from");
        }
        String[] result = new String[3];
        String[] temp = _input.split("/from");
        result[0] = temp[0];
        String[] temp2 = temp[1].split("/to");

        if (!_input.contains("/to")) {
            throw new IncompleteCommandException("/to");
        }
        result[1] = temp2[0];
        result[2] = temp2[1];
        try {
            _from = DATE_IN_FORMAT.parse(result[1].trim());
            _to = DATE_IN_FORMAT.parse(result[2].trim());
        } catch (ParseException e) {
            throw new DateParseException(e.getMessage());
        }
        _description = result[0].trim();
        processed = true;

    }

    /**
     * Convert the user input to valid program input for a todo
     * task
     */
    public void forTodo() {
        _description = _input;
        processed = true;
    }
    public void forFind() {
        _description = _input;
        processed = true;
    }


}
