package duke.logic;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.logic.task.Deadline;
import duke.logic.task.Event;
import duke.logic.task.Task;

/**
 * Handles storage of tasks.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList object
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a given task to the end of the list of tasks.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTask(int index) {
        assert this.getSize() > index : "index should not be out of range";
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    /**
     * Given an array of integers, returns the tasks of those indexes in string form.
     * @param arrayList Arraylist of integers.
     * @return String of tasks.
     */
    public String toStringIndexes(ArrayList<Integer> arrayList) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < arrayList.size(); i++) {
            str.append(i + 1).append(".").append(this.tasks.get(arrayList.get(i)).toString()).append("\n");
        }

        return str.toString();
    }

    /**
     * Given a word, searches all tasks and compiles the index of all tasks that
     * contain that word into an ArrayList.
     * @param str Word to be found.
     * @return ArrayList of integers corresponding to tasks.
     */
    public ArrayList<Integer> findIndexesContaining(String str) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.getTask(i).getDescription().contains(str)) {
                arrayList.add(i);
            }
        }

        return arrayList;
    }

    /**
     * Given a date, searches all task and compiles the index of all tasks that
     * has a duration in which the given localDate lies in.
     * @param localDate LocalDate to check if is during duration of task.
     * @return List of tasks that have duration that the localDate lies in.
     */
    public ArrayList<Integer> findDates(LocalDate localDate) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task task = this.getTask(i);
            if (task.getType().equals("deadline")) {
                boolean isDueDateMatch = ((Deadline) task).getLocalDateDue().isEqual(localDate);
                if (isDueDateMatch) {
                    arrayList.add(i);
                }
            } else if (task.getType().equals("event")) {
                Event event = (Event) task;
                boolean isAfterStart = !event.getLocalDateStart().isAfter(localDate);
                boolean isBeforeEnd = !event.getLocalDateEnd().isBefore(localDate);
                if (isAfterStart && isBeforeEnd) {
                    arrayList.add(i);
                }
            }
        }

        return arrayList;
    }

    /**
     * Deletes a Task of the given index from the list of tasks.
     *
     * @param index 0-indexed index of task to be deleted.
     * @return Task deleted.
     */
    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            str.append(i + 1).append(".").append(this.tasks.get(i).toString()).append("\n");
        }

        return str.toString();
    }
}
