package duke.task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.ui.Ui;

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
     * Use streams to iterate through the list of Task.
     *
     * @return String representation of list of tasks.
     */
    public String toList() {
        return IntStream.range(0, taskList.size())
                .mapToObj(i -> (i + 1) + ". " + taskList.get(i).toString())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Sort the tasklist based on nearest deadline.
     *
     * @return Sorted tasklist.
     */
    public TaskList sortList() {
        ArrayList<Task> arrayList = new ArrayList<>(taskList.stream().sorted().collect(Collectors.toList()));

        return new TaskList(arrayList);
    }

    /**
     * Find the next available time slot where user is free for the specified duration.
     *
     * @param quantifier Integer quantifier.
     * @param unitOfTime String unit of time.
     * @return String result to be shown on GUI.
     */
    public String seekAvailability(int quantifier, String unitOfTime) {
        TaskList sortedList = sortList();

        LocalDateTime result = null;

        // Goes through sorted list
        for (int i = 0; i < sortedList.size() - 1; i++) {
            Task startTask = sortedList.get(i);
            Task endTask = sortedList.get(i + 1);

            // Once reach a todo, no more tasks with deadline in the list
            if (startTask instanceof ToDo) {
                break;
            }

            // Once the second task used for comparison is a todo, update result with right bound
            if (endTask instanceof ToDo) {
                result = startTask.getSecondEnd();
                break;
            }

            // If the first task ends after the second task starts
            LocalDateTime firstEnd = startTask.getFirstEnd();
            LocalDateTime secondStart = endTask.getSecondStart();

            // Update the right bound to the second task right bound
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
                break;
            default:
                duration = -1;
            }

            if (duration >= quantifier) {
                result = secondStart;
            }

        }

        if (result == null) {
            return "You can get started right away.";
        } else {
            return Ui.printAvailability(result, quantifier, unitOfTime);
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
