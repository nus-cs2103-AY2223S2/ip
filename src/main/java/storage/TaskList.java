package storage;

import command.*;
import task.Task;
import task.Todo;
import task.Deadline;
import task.Event;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final File tasksFile;

    /**
     * Constructor for the task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.tasksFile = null;
    }

    /**
     * Constructor for task list from a file.
     * @param tasksFile     a file containing the stored tasks
     */
    public TaskList(File tasksFile) {
        this.tasks = new ArrayList<>();
        this.tasksFile = tasksFile;
        loadTasksFromFile();
    }

    /**
     * Adds a new todo task.
     * @param task  task to be added
     * @return The new todo
     */
    public Todo createTodo(String task) {
        Todo newTodo = new Todo(task);
        this.tasks.add(newTodo);
        return newTodo;
    }

    /**
     * Adds a new deadline task.
     * @param task      task to be added
     * @param deadline  deadline of the task
     * @return The new deadline
     */
    public Deadline createDeadline(String task, String deadline) {
        Deadline newDeadline = new Deadline(task, deadline);
        this.tasks.add(newDeadline);
        return newDeadline;
    }

    /**
     * Adds a new event task.
     * @param task       task to be added
     * @param startTime  starting time of the task
     * @param endTime    ending time of the task
     * @return The new task
     */
    public Event createEvent(String task, String startTime, String endTime) {
        Event newEvent = new Event(task, startTime, endTime);
        this.tasks.add(newEvent);
        return newEvent;
    }

    /**
     * Lists all tasks.
     * @return  A list of tasks.
     */
    public ArrayList<Task> indexTask() {
        return this.tasks;
    }

    /**
     * Shows one task.
     * @param index     index of the task item
     * @return  The task item.
     */
    public Task showTask(int index) {
        return this.tasks.get(index);
    }

    /**
     * Marks task as completed.
     * @param index     index of task
     */
    public void markTask(int index) {
        this.tasks.get(index).markCompleted();
    }

    /**
     * Marks task as uncompleted.
     * @param index     index of task
     */
    public void unmarkTask(int index) {
        this.tasks.get(index).unmarkCompleted();
    }

    /**
     * Deletes task.
     * @param index     index of task
     */
    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     * @return  Number of tasks.
     */
    public int countTask() {
        return this.tasks.size();
    }

    private void loadTasksFromFile() {
        try {
            FileReader fr = new FileReader(this.tasksFile);
            BufferedReader reader = new BufferedReader(fr);
            String s;
            int currIndex = 0;
            while ((s = reader.readLine()) != null) {
                String[] data = s.split(" \\| ");
                try {
                    String taskType = data[0];
                    String taskIsCompleted = data[1];
                    String task = data[2];

                    switch (taskType) {
                        case "T":
                            new TodoCommand(task).run(this);
                            break;
                        case "D":
                            String deadline = data[3];
                            new DeadlineCommand(task, deadline).run(this);
                            break;
                        case "E":
                            String eventStartTime = data[3].split("-", 2)[0];
                            String eventEndTime = data[3].split("-", 2)[1];
                            new EventCommand(task, eventStartTime, eventEndTime).run(this);
                            break;
                        default:
                            System.out.println("Error occurs at: " + s);
                            throw new RuntimeException("Datafile provided is corrupted," +
                                    "create a new file or follow the format.");
                    }

                    switch (taskIsCompleted) {
                        case "0":
                            new UnmarkCommand(currIndex).run(this);
                            break;
                        case "1":
                            new MarkCommand(currIndex).run(this);
                            break;
                        default:
                            System.out.println("Error occurs at: " + s);
                            throw new RuntimeException("Datafile provided is corrupted," +
                                    "create a new file or follow the format.");
                    }

                    currIndex++;
                } catch (IndexOutOfBoundsException error) {
                    throw new RuntimeException("Datafile provided is corrupted," +
                            "create a new file or follow the format.");
                }
            }
            reader.close();
            fr.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
