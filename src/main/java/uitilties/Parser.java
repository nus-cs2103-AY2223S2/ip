package uitilties;

import exceptions.ContentEmpty;
import exceptions.DukeException;
import exceptions.IncompleteCommandException;
import exceptions.InvalidMarkInput;
import tasks.ITask;

import java.util.ArrayList;

public class Parser {
    private boolean processed = false;
    private String _input;
    private String _description;

    public String getBy() {
        return _by;
    }

    public void forDeadline() throws DukeException {
        if (!processed) {
            if (!_input.contains("/b")) {
                throw new IncompleteCommandException("/b");
            }

            String[] temp = _input.split("/b");
            if (temp.length < 1) {
                throw new ContentEmpty("'/b command'");
            }
            _description = temp[0].trim();
            _by = temp[1].trim();
            processed = true;
        }
    }


    public String getFrom() throws DukeException {
        if (_from.isEmpty()) {
            throw new ContentEmpty("'from'");
        }
        return _from;
    }

    public String getTo() throws DukeException {
        if (_to.isEmpty()) {
            throw new ContentEmpty("'to'");
        }
        return _to;
    }

    private String _by;
    private String _from;
    private String _to;


    private final ArrayList<ITask> _tasks;

    public ITask.TaskTypes getType() {
        return _type;
    }

    private ITask.TaskTypes _type;
    private int _index;


    public Parser(ArrayList<ITask> tasks) {
        _tasks = tasks;
    }

    public Parser(String input, ArrayList<ITask> tasks) {
        _input = input;
        _tasks = tasks;
    }

    public Parser(ArrayList<ITask> tasks, String input, ITask.TaskTypes type) {
        _tasks = tasks;
        _input = input;
        _type = type;
    }

    public ArrayList<ITask> getTasks() {
        return _tasks;
    }


    public int getIndex() throws DukeException {
        if (!processed) {
            try {
                _index = Integer.parseInt(_input) - 1;
            } catch (NumberFormatException e) {
                throw new InvalidMarkInput(_input);
            }
            if (_index < 0 || _index > _tasks.size() - 1) {
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
        _from = result[1].trim();
        _to = result[2].trim();
        _description = result[0].trim();
        processed = true;

    }

    public void forTodo() {
        _description = _input;
        processed = true;
    }
}
