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

/**
 *
 */
public class TaskList {
    private static final String SEPARATOR = "~%~";
    private final List<Task> tasks;

    /**
     * Constructs an instance of TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs an instance of TaskList.
     *
     * @param taskList
     */
    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Add a new task (either todo, deadline or event) into the current list of
     * tasks.
     *
     * @param strings
     * @return
     * @throws DateTimeParseException
     */
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

    /**
     * Marks or unmarks a task on the list, based on the specified task number.
     *
     * @param taskNum
     * @param isMark
     * @return
     * @throws EmptyListException
     * @throws InvalidIntegerException
     */
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

    /**
     * Delete a task on the list, based on the specified task number.
     *
     * @param taskNum
     * @return
     * @throws EmptyListException
     * @throws InvalidIntegerException
     */
    Task delete(int taskNum) throws EmptyListException, InvalidIntegerException {
        checkEmptyList(CmdType.DELETE);
        checkOutOfUppBound(taskNum, CmdType.DELETE);
        return tasks.remove(taskNum - 1);
    }

    /**
     * Delete all the tasks on the list.
     */
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

    /**
     * Checks is the current task list empty.
     *
     * @param cmd
     * @return
     * @throws EmptyListException
     */
    public void checkEmptyList(CmdType cmd) throws EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException(cmd.toString());
        }
    }

    /**
     * Checks is the specified task number more than the total number of tasks
     * on the list.
     *
     * @param taskNum
     * @param cmd
     * @return
     * @throws InvalidIntegerException
     */
    public void checkOutOfUppBound(int taskNum, CmdType cmd) throws InvalidIntegerException {
        int n = getSize();
        if (taskNum - 1 >= n) {
            throw new InvalidIntegerException(cmd.toString(), taskNum, n);
        }
    }

    /**
     * Gets the total number of tasks on the list.
     *
     * @return Total number of tasks on the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Saves all the tasks on the list to Wessy's storage, by passing a suitable
     * String format of the task list.
     *
     * @return
     */
    // For interaction with Storage
    public String saveAsStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(tasks.get(i).saveAsStr(SEPARATOR));

        }
        return sb.toString();
    }

    /**
     * Passes all the tasks on the list to Wessy's ui to print them out in
     * "list" message.
     *
     * @return
     */
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
