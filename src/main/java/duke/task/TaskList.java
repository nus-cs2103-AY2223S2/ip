package duke.task;

import duke.exception.DukeException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

/**
 * A list of Tasks.
 *
 * @author Guo-KeCheng
 */
public class TaskList implements Iterable<Task> {

    private final ArrayList<Task> taskList;

    /**
     * TaskList class constructor.
     * Instantiates taskList to be an empty ArrayList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * TaskList class constructor
     *
     * @param taskList Instantiate taskList with given arraylist
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Get the size of taskList
     *
     * @return Size of taskList
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Get the task at a specific index of taskList
     *
     * @return Task at a specific index of taskList
     */
    public Task get(int index) {
        return this.taskList.get(index);
    }

    /**
     * Add task to taskList
     *
     * @param task Task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Remove task at a specific index of taskList
     *
     * @param index Index of taskList to be removed
     */
    public void remove(int index) {
        this.taskList.remove(index);
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    /**
     * Use streams to iterate through the list of Task
     *
     * @return String representation of list of tasks
     */
    public String toList() {
        return taskList.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }

    public TaskList sortList() {
        ArrayList<Task> arrayList = new ArrayList<>(taskList.stream().sorted().collect(Collectors.toList()));

        return new TaskList(arrayList);
    }


    public LocalDateTime seekAvailability(int quantifier, String unitOfTime) throws DukeException {
        TaskList sortedList = sortList();

        LocalDateTime result = null;

        for (int i = 0; i < sortedList.size() - 1; i++) {
            Task startTask = sortedList.get(i);
            Task endTask = sortedList.get(i + 1);

            if (startTask instanceof ToDo) {
                break;
            }

            if (endTask instanceof ToDo) {
                result = startTask.getSecondEnd();
                break;
            }

            LocalDateTime firstEnd = startTask.getFirstEnd();
            LocalDateTime secondStart = endTask.getSecondStart();

            if (firstEnd.isAfter(secondStart)) {
                result = endTask.getSecondEnd();
                break;
            }

            long duration;
            switch (unitOfTime) {
            case "day":
            case "days":
                duration = firstEnd.until(secondStart, ChronoUnit.DAYS);
                break;
            case "hour":
            case "hours":
                duration = firstEnd.until(secondStart, ChronoUnit.HOURS);
                break;
            case "minute":
            case "minutes":
                duration = firstEnd.until(secondStart, ChronoUnit.MINUTES);
            default:
                duration = -1;
            }

            if (duration >= quantifier) {
                result = secondStart;
            }

        }

        if (result == null) {
            throw new DukeException("You can get started right away.");
        } else {
            return result;
        }
    }

    @Override
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {

            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size() && get(currentIndex) != null;
            }

            @Override
            public Task next() {
                return get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return it;
    }
}
