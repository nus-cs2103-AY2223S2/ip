package duke.utils;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.taskers.Task;

/**
 * The task list that tracks all the items in the list.
 */
public class TaskList {

    private ArrayList<Task> listOfThings;

    /**
     * Constructor for task list.
     * @param loadedTasks
     */
    public TaskList(ArrayList<Task> loadedTasks) {
        this.listOfThings = loadedTasks;
    }

    public ArrayList<Task> getList() {
        return this.listOfThings;
    }


    /**
     * Marks the task inside the list as done.
     *
     * @param index The index in the array of the task we want marked.
     * @return The task that is marked.
     */
    public Task markTaskInListDone(int index) throws DukeException {
        if (index < 0 || index >= this.listOfThings.size()) {
            throw new DukeException("Index out of bounds.");
        }
        this.listOfThings.get(index).markDone();
        return this.listOfThings.get(index);
    }


    /**
     * Marks the task inside the list as undone.
     *
     * @param index The index in the array of the task we want unmarked.
     * @return The task we just unmarked.
     */
    public Task markTaskInListUndone(int index) throws DukeException {
        if (index < 0 || index >= this.listOfThings.size()) {
            throw new DukeException("Index out of bounds.");
        }
        this.listOfThings.get(index).markUndone();
        return this.listOfThings.get(index);
    }

    /**
     * Adds an item to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addItem(Task task) {
        this.listOfThings.add(task);
    }

    /**
     * Finds the matching duke.taskers.Task with respect to the keyword.
     *
     * @param keyword The keyword string.
     * @return An array of Tasks that contain that matching keyword.
     */
    public ArrayList<Task> findMatching(String keyword) {
        ArrayList<Task> matchingWords = new ArrayList<>();
        String betterKeyword = keyword.trim().toLowerCase();
        assert betterKeyword.chars().allMatch(Character::isLowerCase);
        for (Task listOfThing : this.listOfThings) {
            if (listOfThing.getDescription().contains(betterKeyword)) {
                matchingWords.add(listOfThing);
            }
        }
        return matchingWords;
    }

    /**
     * Deletes the item from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that is deleted.
     */
    public Task deleteItem(int index) throws DukeException {
        if (index < 0 || index >= this.listOfThings.size()) {
            throw new DukeException("Index out of bounds.");
        }
        return this.listOfThings.remove(index);
    }


}
