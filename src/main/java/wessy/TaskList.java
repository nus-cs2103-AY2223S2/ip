package wessy;

import wessy.exceptions.int_exceptions.EmptyListException;
import wessy.exceptions.int_exceptions.InvalidIntegerException;
import wessy.task.Deadline;
import wessy.task.Event;
import wessy.task.Task;
import wessy.task.ToDo;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<Task>();
    static String SEPARATOR = "~%~";

    public TaskList() {}

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
            tasks.add(new Event(Parser.removeSpacePadding(strings[0]), Parser.parseDateTime(strings[1]), Parser.parseDateTime(strings[2])));
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

    public String[] find(String target) {
        target = Parser.removeSpacePadding(target);
        List<Task> foundResults = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.toString().contains(target)) {
                foundResults.add(task);
            }
        }
        return printAsStr(foundResults).toArray(new String[foundResults.size()]);
    }

    // Check for empty list exception
    public void checkEmptyList(CmdType cmd) throws EmptyListException {
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

    // HELPER FUNCTION
    private static List<String> printAsStr(List<Task> foundResults) {
        int n = foundResults.size();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            list.add("" + (i + 1) + "." + foundResults.get(i));
        }
        return list;
    }
}
