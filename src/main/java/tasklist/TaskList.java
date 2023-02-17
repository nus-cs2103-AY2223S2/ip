package tasklist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.VBox;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import ui.Ui;

public class TaskList {

	enum TaskType {
		TODO, DEADLINE, EVENT
	}

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
	 * @param itemNum         An item number to be marked/unmarked.
	 * @param isMark
	 * @param storage         A store that represents the data access object (DAO).
	 * @param ui              A service to render the page of GUI.
	 * @param dialogContainer A container that holds all the rows of labels.
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
	 * @param ui              A service to render the page of GUI.
	 * @param storage         A store that represents the data access object (DAO).
	 * @param dialogContainer A container that holds all the rows of labels.
	 * @param itemNum         An item number to be deleted.
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

	/**
	 * Return true if a todo with similar description existed.
	 * 
	 * @param description
	 * @return boolean
	 */
	public boolean hasDuplicateTodo(String description) {
		for (Task task : tasks) {
			if (task instanceof Todo) {
				Todo todo = (Todo) task;
				if (todo.getDescription().equalsIgnoreCase(description)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Return true if a deadline with similar description and 'by' datetime existed.
	 * 
	 * @param description
	 * @param by
	 * @return boolean
	 */
	public boolean hasDuplicateDeadline(String description, LocalDateTime by) {
		for (Task task : tasks) {
			if (task instanceof Deadline) {
				Deadline deadline = (Deadline) task;
				if (deadline.getDescription().equalsIgnoreCase(description)
						&& deadline.getBy().equals(by)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Return true if an event with similar description and, 'from' and 'to'
	 * datetime existed.
	 * 
	 * @param description
	 * @param from
	 * @param to
	 * @return boolean
	 */
	public boolean hasDuplicateEvent(String description, LocalDateTime from, LocalDateTime to) {
		for (Task task : tasks) {
			if (task instanceof Event) {
				Event event = (Event) task;
				if (event.getDescription().equalsIgnoreCase(description)
						&& event.getFrom().equals(from)
						&& event.getTo().equals(to)) {
					return true;
				}
			}
		}
		return false;
	}
}
