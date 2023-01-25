package Storage;

import Task.Task;
import Task.Todo;
import Task.Event;
import Task.Deadline;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

import DukeException.DukeException;

public class TaskList {

    private ArrayList<Task> tasks;
    private File file;

    /**
     * Constructor to create new Task.Task List
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(File file) {
        this.tasks = new ArrayList<Task>();
        this.file = file;
        loadTasks();
    }

    public void loadTasks() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.file));
            String line = reader.readLine();

            while (line != null) {
                try {
                    String[] args = line.split("\\|");
                    String task_type = args[0].trim();
                    String task_status = args[1].trim();
                    String task_desc = args[2];
                    switch (task_type) {
                        case "T":
                            Todo todo = new Todo(task_desc);
                            if (task_status.equals("1")) {
                                todo.markComplete();
                            }
                            tasks.add(todo);
                            break;
                        case "D":
                            String due_date = args[3];
                            Deadline deadline = new Deadline(task_desc, due_date);
                            if (task_status.equals("1")) {
                                deadline.markComplete();
                            }
                            tasks.add(deadline);
                            break;
                        case "E":
                            String[] duration = args[3].split("-");
                            String startTime = duration[0];
                            String endTime = duration[1];
                            Event event = new Event(task_desc, startTime, endTime);
                            if (task_status.equals("1")) {
                                event.markComplete();
                            }
                            tasks.add(event);
                            break;
                        default:
                            break;
                    }
                    line = reader.readLine();
                } catch (DukeException duke_error) {
                    duke_error.printStackTrace();
                }
            }
            reader.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            for (Task task: tasks) {
                writer.write(task.toData());
                writer.newLine();
            }
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    /**
     * Add new task to current task list
     * @param task new task to be added into the task list
     */
    public void addTask(String task) {
        this.tasks.add(new Task(task));
    }

    /**
     * Add new task to current task list
     * @param task new task to be added into the task list
     * @return the Added Todo
     */
    public Todo addTodo(String task) {
        Todo todo = new Todo(task);
        this.tasks.add(todo);
        return todo;
    }

    /**
     * Add new deadline to current task list
     * @param task new task to be added
     * @param deadline the deadline of the task
     * @return the added Deadline
     */
    public Deadline addDeadline(String task, String deadline) {
        Deadline taskDeadline = new Deadline(task, deadline);
        this.tasks.add(taskDeadline);
        return taskDeadline;
    }

    /**
     * Add new event to current task list
     * @param task event task to be added
     * @param from start time of the event
     * @param to end time of the event
     * @return the added Event
     */
    public Event addEvent(String task, String from, String to) {
        Event event = new Event(task, from, to);
        this.tasks.add(event);
        return event;
    }

    /**
     * Get the specific task at given index, idx
     * @param idx Given index of the task in the list
     * @return Task.Task at index, idx
     */
    public Task getTask(Integer idx) {
        return tasks.get(idx);
    }

    /**
     * Delete task at specific index, idx
     * @param idx index of the task in the array to be deleted
     */
    public void deleteTask(Integer idx) {
        this.tasks.remove(idx.intValue());
    }

    /**
     * Getter method to get the task list
     * @return task list
     */
    public String getTaskList() {
        StringBuilder taskList = new StringBuilder();

        for (int i = 1; i <= this.tasks.size(); i++) {
            if (i == this.tasks.size()) {
                taskList.append(i + ": " + this.tasks.get(i - 1));
                break;
            }
            taskList.append(i + ": " + this.tasks.get(i - 1) + "\n");
        }

        return taskList.toString();
    }

    /**
     * Get the number of tasks in the list
     * @return number of tasks
     */
    public int numOfTask() {
        return this.tasks.size();
    }
}
