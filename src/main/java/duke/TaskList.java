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
                    addTodo(line, taskList);
                    if (isMarked(line[1])) {
                        markLast(taskList);
                    }
                    break;
                case "D":
                    addDeadline(line, taskList);
                    if (isMarked(line[1])) {
                        markLast(taskList);
                    }
                    break;
                case "E":
                    addEvent(line, taskList);
                    if (isMarked(line[1])) {
                        markLast(taskList);
                    }
                    break;
                default:
                    assert false : line[0].trim();
                }
            }
        } catch (FileNotFoundException e) {
            Ui.fileExceptionUi();
        }
    }

    /**
     * Adds a todo task to the ArrayList
     *
     * @param line     Parsed task details
     * @param taskList ArrayList of tasks
     */
    public void addTodo(String[] line, ArrayList<Task> taskList) {
        taskList.add(new Todo(line[2].trim()));
    }

    /**
     * Adds an event task to the ArrayList
     *
     * @param line     Parsed task details
     * @param taskList ArrayList of tasks
     */
    public void addEvent(String[] line, ArrayList<Task> taskList) {
        taskList.add(new Event(line[2].trim() + " ", line[3].trim()));
    }

    /**
     * Adds a deadline task to the ArrayList
     *
     * @param line     Parsed task details
     * @param taskList ArrayList of tasks
     */
    public void addDeadline(String[] line, ArrayList<Task> taskList) {
        LocalDate time = LocalDate.parse(line[3].trim());
        taskList.add(new Deadline(line[2].trim() + " ", time));
    }


    /**
     * Marks the last current task in the ArrayList of tasks as done
     *
     * @param taskList The ArrayList of tasks
     */
    public void markLast(ArrayList<Task> taskList) {
        taskList.get(taskList.size() - 1).markAsDone();
    }

    /**
     * Tests whether the task is marked as done
     *
     * @param input The task status indicated as 1 or 0
     * @return True if the task is done and false otherwise
     */
    public boolean isMarked(String input) {
        if (input.trim().equals("1")) {
            return true;
        } else {
            return false;
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
