package wessy.components;

import java.util.ArrayList;
import java.util.List;

import java.time.format.DateTimeParseException;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import wessy.CmdType;
import wessy.components.Parser;
import wessy.task.Task;
import wessy.task.ToDo;
import wessy.task.Deadline;
import wessy.task.DoAfterTask;
import wessy.task.FixedDurationTask;
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
    public Task add(String[] strings, CmdType cmd) throws DateTimeParseException {
        int oldSize = tasks.size();

        switch (cmd) {
            case TODO:
                tasks.add(new ToDo(Parser.removeSpacePadding(strings[0])));
                break;
            case DEADLINE:
                tasks.add(new Deadline(Parser.removeSpacePadding(strings[0]), Parser.parseDateTime(strings[1])));
                break;
            case DOAFTER:
                tasks.add(new DoAfterTask(Parser.removeSpacePadding(strings[0]),
                        Parser.removeSpacePadding(strings[1])));
                break;
            case FIXEDDURATION:
                tasks.add(new FixedDurationTask(Parser.removeSpacePadding(strings[0]),
                        Parser.removeSpacePadding(strings[1])));
                break;
            case EVENT:
                tasks.add(new Event(Parser.removeSpacePadding(strings[0]), Parser.parseDateTime(strings[1]),
                        Parser.parseDateTime(strings[2])));
                break;
        }
        assert tasks.size() == oldSize + 1;
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
    public Task markOrUnmark(int taskNum, boolean isMark) throws EmptyListException, InvalidIntegerException {
        CmdType cmd = isMark ? CmdType.MARK : CmdType.UNMARK;
        checkEmptyList(cmd);
        assert tasks.size() > 0;
        assert !tasks.isEmpty();
        checkOutOfUppBound(taskNum, cmd);
        assert taskNum <= tasks.size();

        int idx = taskNum - 1;
        if (isMark) {
            tasks.get(idx).mark();
        } else {
            tasks.get(idx).unmark();
        }
        assert tasks.get(idx).checkIsDone() == isMark;
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
    public Task delete(int taskNum) throws EmptyListException, InvalidIntegerException {
        checkEmptyList(CmdType.DELETE);
        assert tasks.size() > 0;
        assert !tasks.isEmpty();
        checkOutOfUppBound(taskNum, CmdType.DELETE);
        assert taskNum <= tasks.size();
        return tasks.remove(taskNum - 1);
    }

    /**
     * Delete all the tasks on the list.
     */
    public void clear() {
        tasks.clear();
        assert tasks.size() == 0;
        assert tasks.isEmpty();
    }

    public String[] find(String target) {
        String finalTarget = Parser.removeSpacePadding(target);
        return printAsStr(tasks.stream().filter(task -> task.toString().contains(finalTarget)));
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
        assert tasks.size() > 0;
        assert !tasks.isEmpty();
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
        assert n == tasks.size();
        if (taskNum - 1 >= n) {
            throw new InvalidIntegerException(cmd.toString(), taskNum, n);
        }
        assert taskNum <= tasks.size();
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
        BiFunction<StringBuilder, Task, StringBuilder> appender = (curr, task) -> curr.append(task.saveAsStr(SEPARATOR));
        BinaryOperator<StringBuilder> combiner = (sb1, sb2) -> sb1.append(sb2.toString());
        return tasks.stream().reduce(new StringBuilder(), appender, combiner).toString();
    }

    /**
     * Passes all the tasks on the list to Wessy's ui to print them out in
     * "list" message.
     *
     * @return
     */
    public String[] printAsStr() {
        return printAsStr(tasks);
    }

    // HELPER FUNCTION
    private static String[] printAsStr(List<Task> foundResults) {
        return printToStream(foundResults).toArray(String[]::new);
    }

    private static String[] printAsStr(Stream<Task> foundResults) {
        return printAsStr(foundResults.collect(Collectors.toList()));
    }

    private static Stream<String> printToStream(List<Task> taskList) {
        int n = taskList.size();
        return IntStream.range(0, n)
                .mapToObj(i -> "" + (i + 1) + "." + taskList.get(i));
    }
}
