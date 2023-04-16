package model;

import java.util.ArrayList;


import model.tasks.Task;


/**
 * Representation of the list containing tasks
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for the TaskList class
     * @param tasks
     */
	public TaskList() {
		this.list = new ArrayList<Task>();
	}

	/**
	 * Returns the size of the list
	 * @return int
	 */
	public int size() {
		return this.list.size();
	}

	/**
	 * Returns the task at the specified index
	 * @param index
	 * @return Task
	 */
	public Task get(int index) {
		return this.list.get(index);
	}

	/**
	 * Adds a task to the list
	 * @param task
	 */
	public void add(Task task) {
		this.list.add(task);
	}

	/**
	 * Deletes a task from the list
	 * @param index
	 * @return Task
	 */
	public Task delete(int index) {
		Task removedTask = this.list.get(index);
		this.list.remove(index);
		return removedTask;
	}

	/**
	 * Marks a task as done
	 * @param index
	 */
	public void markAsDone(int index) {
		this.list.get(index).setIsComplete(true);
	}


	/**
	 * Marks a task as undone
	 * @param index
	 */
	public void markAsUndone(int index) {
		this.list.get(index).setIsComplete(false);
	}

	/**
	 * Returns true if the list is empty
	 */
	public boolean isEmpty() {
		return this.list.isEmpty();
	}
}
