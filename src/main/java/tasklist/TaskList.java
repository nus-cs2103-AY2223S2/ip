package tasklist;

import java.util.ArrayList;
import java.util.List;

import storage.Storage;
import task.Task;
import ui.Ui;

public class TaskList {

	private List<Task> items = new ArrayList<>();

	public void add(Task task) {
		this.items.add(task);
	}

	public int size() {
		return this.items.size();
	}

	public Task get(int index) {
		return this.items.get(index);
	}

	public void markItem(String itemNum, boolean isMark, Storage storage, Ui ui) throws NumberFormatException {
		int idx = Integer.parseInt(itemNum) - 1;
		try {
			Task task = items.get(idx);
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
			ui.printError(String.format("Oops! Please select item from 1 to %d inclusive.", items.size()));
		}
	}

	public void deleteItem(String itemNum, Storage storage, Ui ui) throws NumberFormatException {
		try {
			int idx = Integer.parseInt(itemNum) - 1;
			storage.deleteLineFile(idx + 1, ui);
			ui.printItemDeleted(items.remove(idx), items.size());
		} catch (IndexOutOfBoundsException ex) {
			ui.printError(String.format("Oops! Please select item from 1 to %d inclusive.", items.size()));
		}
	}

}
