package tasklist;

import java.util.ArrayList;
import java.util.List;

import storage.Storage;
import task.Task;
import ui.Ui;

public class TaskList {

	private List<Task> tasks = new ArrayList<>();

	/**
	 * Add new task.
	 * 
	 * @param task
	 */
	public void add(Task task) {
		this.tasks.add(task);
	}

	/**
	 * Get the number of tasks recorded.
	 * 
	 * @return int
	 */
	public int size() {
		return this.tasks.size();
	}

	/**
	 * Get a task by index.
	 * 
	 * @param index
	 * @return Task
	 */
	public Task get(int index) {
		return this.tasks.get(index);
	}

	/**
	 * Mark or unmark a task.
	 * 
	 * @param itemNum
	 * @param isMark
	 * @param storage
	 * @param ui
	 * @throws NumberFormatException
	 */
	public void markItem(String itemNum, boolean isMark, Storage storage, Ui ui) throws NumberFormatException {
		int idx = Integer.parseInt(itemNum) - 1;
		try {
			Task task = tasks.get(idx);
			if (isMark) {
				task.markAsDone();
				ui.printMarkedTask(task, true);
				storage.markSavedTask(idx, true, ui);
				return;
			}
			task.markAsUndone();
			ui.printMarkedTask(task, false);
			storage.markSavedTask(idx, false, ui);
		} catch (IndexOutOfBoundsException ex) {
			ui.printError(String.format("Oops! Please select item from 1 to %d inclusive.", tasks.size()));
		}
	}

	/**
	 * Delete a task from existing tasks.
	 * 
	 * @param itemNum
	 * @param storage
	 * @param ui
	 * @throws NumberFormatException
	 */
	public void deleteTask(String itemNum, Storage storage, Ui ui) throws NumberFormatException {
		try {
			int idx = Integer.parseInt(itemNum) - 1;
			storage.deleteLineFile(idx + 1, ui);
			ui.printDeletedTask(tasks.remove(idx), tasks.size());
		} catch (IndexOutOfBoundsException ex) {
			ui.printError(String.format("Oops! Please select item from 1 to %d inclusive.", tasks.size()));
		}
	}

}
