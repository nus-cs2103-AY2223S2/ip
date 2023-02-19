package duke;

import duke.tasks.Task;

import java.util.ArrayList;

/**
 * Contains the task list of the program.
 */
public class TaskList {
    ArrayList<Task> data = new ArrayList<>();

    /**
     * Adds a task into the existing list of tasks.
     * @param task A Task to be added.
     */
    public void addEntry(Task task) {
        this.data.add(task);
        System.out.println("added: " + task.getDescription());
    }

    /**
     * Adds a task into the existing list of tasks without printing
     * a response message.
     * Only to be used during initialisation.
     * @param task A Task to be added.
     */
    public void addFileEntry(Task task) {
        this.data.add(task);
    }

    /**
     * Gets a task at a given position from the list of tasks.
     * @param pos Position of Task in the list to be extracted.
     * @return The extracted Task
     */
    public Task getEntry(int pos) {
        return this.data.get(pos);
    }

    /**
     * Removes an existing entry at a given position in the list of entries.
     * @param pos Position of Task to be removed.
     */
    public void removeEntry(int pos) {
        this.data.remove(pos);
    }

    /**
     * Marks an existing entry at a given position to be done.
     * @param pos Position of Task to be marked.
     */
    public void mark(int pos) {
        this.data.get(pos).mark();
    }

    /**
     * Unmarks an existing entry at a given position to be undone.
     * @param pos Position of Task to be unmarked.
     */
    public void unmark(int pos) {
        this.data.get(pos).unmark();
    }

    /**
     * Returns the size of the current list of tasks.
     * @return size of task list.
     */
    public int getSize() {
        return this.data.size();
    }

    @Override
    public String toString() {
        String tasks = "";
        for (int i = 0; i < this.data.size(); i++) {
            tasks = (i+1) + ". " + this.data.get(i).toString() + "\n";
        }
        return tasks;
    }
}
