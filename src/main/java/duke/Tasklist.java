package duke;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The Tasklist class encapsulates
 * the task list and all operations on it.
 */

public class Tasklist {
	ArrayList<Task> listOfTasks = new ArrayList<Task>();

	/**
	 * Adds a task to the tasklist.
	 *
	 * @param task Task to be added to the Tasklist.
	 */
	public void add(Task task) {
		listOfTasks.add(task);
	}

	/**
	 * Marks a task as 'done'.
	 *
	 * @param i The i-th task to be marked.
	 */
	public void mark(int i) {
		Task thisTask = listOfTasks.get(i - 1);
		thisTask.setDone();
		listOfTasks.set(i - 1, thisTask);
	}

	/**
	 * Marks a task as 'undone'.
	 *
	 * @param i The i-th task to be marked.
	 */
	public void unmark(int i) {
		Task thisTask = listOfTasks.get(i - 1);
		thisTask.setUndone();
		listOfTasks.set(i - 1, thisTask);
	}

	/**
	 * Deletes a task from the tasklist.
	 *
	 * @param i The i-th task to be deleted.
	 */
	public void delete(int i) {
		listOfTasks.remove(i - 1);
	}

	/**
	 * Shows the size of the tasklist.
	 *
	 * @return The number of tasks.
	 */
	public int size() {
		return listOfTasks.size();
	}

	/**
	 * Gets a task from the tasklist.
	 *
	 * @param i The i-th index.
	 * @return The i-th task.
	 */
	public Task get(int i) {
		return listOfTasks.get(i);
	}

	/**
	 * Sets index i of the tasklist to a
	 * certain task.
	 *
	 * @param i The i-th index.
	 * @param task The task to be set.
	 */
	public void set(int i, Task task) {
		listOfTasks.set(i, task);
	}

	/**
	 * Finds all tasks matching a keyword.
	 *
	 * @param searchWord The keyword.
	 *
	 * @return String listing all the matching tasks.
	 */
	public String findTasks(String searchWord) {
		String returnedString = "";
		Predicate<Task> byMatch = task -> task.description.contains(searchWord);
		returnedString = listOfTasks.stream()
				.filter(byMatch).map(Task::toString).collect(Collectors.joining("\n"));
		if (returnedString.isEmpty()) {
			return "No tasks found!";
		} else {
			return "Here are some tasks found!\n" + returnedString;
		}
	}

	/**
	 * Returns the String representation of the TaskList.
	 *
	 * @return String representation of the TaskList.
	 */
	@Override
	public String toString() {
		String returnedString = "";
		for (int i = 0; i < listOfTasks.size(); i++) {
			returnedString += i + 1 + ". " + listOfTasks.get(i) + "\n";
		}
		return returnedString;
	}
} 
