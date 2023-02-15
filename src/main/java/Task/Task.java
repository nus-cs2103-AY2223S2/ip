package duke.task;

import duke.DukeException;
import duke.Ui;

/**
 * Represents a Task.
 */
public class Task {

    protected String name;
    private boolean isDone;

    /**
     * Initialises new instance of Task.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Initialises new instance of Task.
     *
     * @param name The name of the task.
     * @param isDone A boolean representing whether task has been completed.
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks a Task as completed.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks a Task as uncompleted.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if task name contains a certain word.
     *
     * @param word The word to check for.
     * @return boolean The boolean value indicates if the word is present in the task name.
     */
    public boolean isRelated(String word) {
        return this.name.contains(word);
    }

    /**
     * Edits the selected task.
     *
     * @param item The item to be updated.
     * @param newInformation The new information.
     * @throws DukeException
     */
    public void edit(String item, String newInformation) throws DukeException {
        if(!item.equals("name")) {
            throw new DukeException(Ui.invalidItemUpdateResponse(this));
        }
        this.name = newInformation;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        if (isDone) {
            return "1" + " | " + this.name;
        } else {
            return "0" + " | " + this.name;
        }
    }

}
