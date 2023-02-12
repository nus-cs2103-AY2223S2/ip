package wessy;

import java.util.ArrayList;
import java.util.List;

import java.time.format.DateTimeParseException;

import wessy.task.Task;
import wessy.task.ToDo;
import wessy.task.Deadline;
import wessy.task.Event;

import wessy.exceptions.integer.EmptyListException;
import wessy.exceptions.integer.InvalidIntegerException;

public class TaskList {
    private static final String SEPARATOR = "~%~";
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    // MAIN METHODS TO UPDATE THE LIST
    // For "todo", "deadline" & "event"
    Task add(String[] strings) throws DateTimeParseException {
        int len = strings.length;
        if (len == 1) {
            tasks.add(new ToDo(Parser.removeSpacePadding(strings[0])));
        } else if (len == 2) {
            tasks.add(new Deadline(Parser.removeSpacePadding(strings[0]), Parser.parseDateTime(strings[1])));
        } else if (len == 3) {
            tasks.add(new Event(Parser.removeSpacePadding(strings[0]), Parser.parseDateTime(strings[1]),
                    Parser.parseDateTime(strings[2])));
        }
        return tasks.get(getSize() - 1);
    }

    // For "mark" & "unmark"
    Task markOrUnmark(int taskNum, boolean isMark) throws EmptyListException, InvalidIntegerException {
        CmdType cmd = isMark ? CmdType.MARK : CmdType.UNMARK;
        checkEmptyList(cmd);
        checkOutOfUppBound(taskNum, cmd);

        int idx = taskNum - 1;
        if (isMark) {
            tasks.get(idx).mark();
        } else {
            tasks.get(idx).unmark();
        }
        return tasks.get(idx);
    }

    // For "delete"
    Task delete(int taskNum) throws EmptyListException, InvalidIntegerException {
        checkEmptyList(CmdType.DELETE);
        checkOutOfUppBound(taskNum, CmdType.DELETE);
        return tasks.remove(taskNum - 1);
    }

    // For "clear"
    public void clear() {
        tasks.clear();
    }

    // Check for empty list exception
    private void checkEmptyList(CmdType cmd) throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException(cmd.toString());
        }
    }

    // Check for array index out of upper bound
    public void checkOutOfUppBound(int taskNum, CmdType cmd) throws InvalidIntegerException {
        int n = getSize();
        if (taskNum - 1 >= n) {
            throw new InvalidIntegerException(cmd.toString(), taskNum, n);
        }
    }

    // Used when adding or deleting ta
    public int getSize() {
        return tasks.size();
    }

    // For interaction with Storage
    public String saveAsStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(tasks.get(i).saveAsStr(SEPARATOR));

        }
        return sb.toString();
    }

    // For interaction with UI
    public String[] printAsStr() {
        int n = getSize();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = "" + (i + 1) + "." + tasks.get(i);
        }
        return arr;
    }
}
