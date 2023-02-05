package duke.task;

import duke.ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TaskList {
    /** Arraylist of tasks */
    private ArrayList<Task> tasks;

    /**
     * Constructs TaskList class.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets ArrayList of tasks.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sets ArrayList of tasks.
     *
     * @param tasks ArrayList of tasks to be set to.
     */
    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets size of ArrayList of tasks.
     *
     * @return Size of ArrayList of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Gets task of specified index in ArrayList.
     *
     * @param index Index of the task in ArrayList.
     * @return Task of specified index in ArrayList.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Adds task to ArrayList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks task in ArrayList.
     *
     * @param task Task to be marked.
     */
    public void markTask(Task task) {
        task.setDone(true);
    }

    /**
     * Unmarks task in ArrayList.
     *
     * @param task Task to be unmarked.
     */
    public void unmarkTask(Task task) {
        task.setDone(false);
    }

    /**
     * Deletes task of specified index in ArrayList.
     *
     * @param index Index of the task in ArrayList.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Shows tasks in ArrayList using Ui.
     *
     * @param ui Ui to show tasks.
     */
    public void showList(Ui ui) {
        ui.showList(this);
    }

    /**
     * Filter tasks by their date.
     * Shows deadlines and ongoing events on that date.
     *
     * @param ui Ui to show filtered tasks.
     * @param date Date to filter tasks by.
     */
    public void filterDate(Ui ui, LocalDate date) {
        int count = 0;
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDeadline().toLocalDate().isEqual(date)) {
                    ui.printUi(deadline.toString());
                    count++;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getStartDateTime().toLocalDate().isEqual(date) ||
                        event.getEndDateTime().toLocalDate().isEqual(date) ||
                        (event.getStartDateTime().toLocalDate().isBefore(date) &&
                                event.getEndDateTime().toLocalDate().isAfter(date))) {
                    ui.printUi(event.toString());
                    count++;
                }
            }
        }
        ui.printUi("Number of tasks on " + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ": " + count);
    }

    public void filter(Ui ui, String keyword) {
        int count = 0;
        for (Task task : tasks) {
            String desc = task.getDescription();
            if (desc.contains(keyword)) {
                ui.printUi(task.toString());
                count++;
            }
        }
        ui.printUi("Number of tasks with " + "'" + keyword + "'" + ": " + count);
    }
}
