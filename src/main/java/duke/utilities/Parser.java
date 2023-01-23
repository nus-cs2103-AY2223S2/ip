package duke.utilities;

import duke.exceptions.*;
import duke.tasks.ITask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    private boolean processed = false;
    private String _input;
    private String _description;
    public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
    public static final DateFormat outputFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss aa");

    public Date getBy() {
        return _by;
    }

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
                _by = dateFormat.parse(temp[1].trim());
            } catch (ParseException e) {
                throw new DateParseException(e.getMessage());
            }

            processed = true;
        }
    }


    public Date getFrom() throws DukeException {
        if (_from == null) {
            throw new ContentEmpty("'from'");
        }
        return _from;
    }

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

    public ITask.TaskTypes getType() {
        return _type;
    }

    private ITask.TaskTypes _type;
    private int _index;

    public  Parser(String input){
        _input = input;
    }
    public Parser(TaskManager taskManager) {
        _taskManager = taskManager;
    }

    public Parser(String input, TaskManager taskManager) {
        _input = input;
        _taskManager = taskManager;
    }

    public Parser(TaskManager taskManager, String input, ITask.TaskTypes type) {
        _taskManager = taskManager;
        _input = input;
        _type = type;
    }

    public TaskManager getTaskManager() {
        return _taskManager;
    }


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


    public String getDescription() throws DukeException {
        if (_description.isEmpty()) {
            throw new ContentEmpty("'description'");
        }
        return _description;
    }

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
            _from = dateFormat.parse(result[1].trim());
            _to = dateFormat.parse(result[2].trim());
        } catch (ParseException e) {
            throw new DateParseException(e.getMessage());
        }
        _description = result[0].trim();
        processed = true;

    }

    public void forTodo() {
        _description = _input;
        processed = true;
    }
}
