package bob;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Returns a new TaskList object that can be used to interact with the task list
     */
    public TaskList() {
        list = new ArrayList<>();
    }

    // Check if index is in list
    private boolean isInvalidIndex(int index) {
        return index <= 0 || index > list.size();
    }

    private void validate(int index) throws BobException {
        boolean isEmpty = this.list.size() == 0;
        boolean isOutOfRange = index <= 0 || index > this.list.size();

        if (isEmpty) {
            throw new BobException("Task list is empty!");
        } else if (isOutOfRange) {
            String err = String.format("Index given should be in range [1-%s]", this.list.size());
            throw new BobException(err);
        }
    }

    /**
     * Adds a given Task object to the list
     * @param t
     */
    public void add(Task t) {
        assert t != null;
        list.add(t);
    }

    /**
     * Mark the task at the given index
     * @param index Index of task to mark
     * @throws BobException If index is out of range
     */
    public void mark(int index) throws BobException {
        validate(index);

        // Since list is 0-indexed
        list.get(index - 1).mark();
    }

    /**
     * Unmark the task at the given index
     * @param index Index of task to unmark
     * @throws BobException If index is out of range
     */
    public void unmark(int index) throws BobException {
        validate(index);

        // Since list is 0-indexed
        list.get(index - 1).unmark();
    }

    /**
     * Delete the task at the given index
     * @param index Index of task to delete
     * @throws BobException If index is out of range
     */
    public Task delete(int index) throws BobException {
        validate(index);

        // Since list is 0-indexed
        return list.remove(index - 1);
    }

    /**
     * Returns the task at the given index
     * @param index Index of task to return
     * @return Task at the given index
     * @throws BobException If index is out of range
     */
    public Task get(int index) throws BobException {
        validate(index);

        // Since list is 0-indexed
        return list.get(index - 1);
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> filteredTasks = new ArrayList<>();

        for (Task t : list) {
            // Check if task description contains keyword
            if (t.description.contains(keyword)) {
                filteredTasks.add(t);
            }
        }

        return filteredTasks;
    }

    public ArrayList<Deadline> getReminders() {
        ArrayList<Deadline> reminders = new ArrayList<>();

        for (Task t: list) {
            // Check for Deadlines
            if (t instanceof Deadline) {
                reminders.add((Deadline) t);
            }
        }

        return reminders;
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
