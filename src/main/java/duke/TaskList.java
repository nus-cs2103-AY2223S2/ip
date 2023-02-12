package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.Ui;


/**
 * Task List to store all the tasks
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Initialises the task list
     *
     * @param file The file storing the task information
     */
    public TaskList(File file) {
        this.taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(file); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String cur = s.nextLine();

                String[] line = cur.split("\\|");

                switch (line[0].trim()) {
                case "T":
                    taskList.add(new Todo(line[2].trim()));
                    if (line[1].equals("1")) {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                    break;
                case "D":
                    LocalDate time = LocalDate.parse(line[3].trim());
                    taskList.add(new Deadline(line[2].trim() + " ", time));
                    if (line[1].trim().equals("1")) {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                    break;
                case "E":
                    taskList.add(new Event(line[2].trim() + " ", line[3].trim()));
                    if (line[1].trim().equals("1")) {
                        taskList.get(taskList.size() - 1).markAsDone();
                    }
                    break;
                default:
                    assert false: line[0].trim();
                }
            }
        } catch (FileNotFoundException e) {
            Ui.fileExceptionUi();
        }
    }

    /**
     * Deletes a task from the list
     *
     * @param index The index of the task in the list
     * @return The task deleted
     */
    public Task delete(int index) {
        Task removed = taskList.remove(index);
        return removed;
    }

    /**
     * Adds a task to the list
     *
     * @param task The task to be added
     */
    public void add(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks a task as completed
     *
     * @param index The index of the task in the list
     * @return The task marked
     */
    public Task mark(int index) {
        Task marked = taskList.get(index);
        marked.markAsDone();
        return marked;
    }

    /**
     * Marks a task as incomplete
     *
     * @param index The index of the task in the list
     * @return The task unmarked
     */
    public Task unmark(int index) {
        Task unmarked = taskList.get(index);
        unmarked.markAsUnDone();
        return unmarked;
    }

    /**
     * Gets the number of the tasks in the list
     *
     * @return The length of the list
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Gets a task in the list
     *
     * @param index The index of the task in the list
     * @return The task object corresponding at the index
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

}
