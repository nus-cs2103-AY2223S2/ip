package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.DukeException;
import model.tasks.Deadline;
import model.tasks.DoAfter;
import model.tasks.Event;
import model.tasks.Task;
import model.tasks.Todo;

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
