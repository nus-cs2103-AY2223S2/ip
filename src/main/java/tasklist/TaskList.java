package tasklist;

import ui.Ui;

import storage.Storage;

import task.Task;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;

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
	 * @param dialogContainer
	 */
	public void markItem(String itemNum, boolean isMark, Storage storage, Ui ui, VBox dialogContainer) {
		int idx = Integer.parseInt(itemNum) - 1;
		try {
			Task task = tasks.get(idx);
			if (isMark) {
				task.markAsDone();
			} else {
				task.markAsUndone();
			}
			ui.printMarkedTask(task, isMark, dialogContainer, storage);
			storage.markSavedTask(idx, isMark, ui);
		} catch (IndexOutOfBoundsException ex) {
			String errorMessage = String.format(
					"Oops! Please select item from 1 to %d inclusive.", tasks.size());
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
		}
	}

	/**
	 * Delete a task from existing tasks.
	 * 
	 * @param ui
	 * @param storage
	 * @param dialogContainer
	 * @param itemNum
	 */
	public void deleteTask(Ui ui, Storage storage, VBox dialogContainer, String itemNum) {
		try {
			int idx = Integer.parseInt(itemNum) - 1;
			storage.deleteLineFile(idx + 1, ui);
			ui.printDeletedTask(tasks.remove(idx), tasks.size(), dialogContainer, storage);
		} catch (IndexOutOfBoundsException ex) {
			String errorMessage = String.format(
					"Oops! Please select item from 1 to %d inclusive.", tasks.size());
			ui.sendResponse(dialogContainer, storage, ui.createLabel(errorMessage));
		}
	}

}
