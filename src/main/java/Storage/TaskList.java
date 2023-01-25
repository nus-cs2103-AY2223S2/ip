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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import DukeException.*;

public class TaskList {

    private ArrayList<Task> tasks;
    private File file;

    /**
     * Constructor to create new Task.Task List
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(File file) {
        this.tasks = new ArrayList<>();
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
                    System.out.println(task_type);
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
                            String due_date = args[3].trim();
                            try {
                                LocalDate dueDate = LocalDate.parse(due_date);
                                Deadline deadline = new Deadline(task_desc, dueDate);
                                if (task_status.equals("1")) {
                                    deadline.markComplete();
                                }
                                tasks.add(deadline);
                                break;
                            } catch (DateTimeException error) {
                                throw new InvalidArgumentException("Wrong date format! Please follow the format YYYY-MM-DD (e.g. 2000-01-01)");
                            }
                        case "E":
                            System.out.println(args[3]);
                            String from = args[3].trim();
                            String to = args[4].trim();
                            try {
                                LocalDate startDate = LocalDate.parse(from);
                                LocalDate endDate = LocalDate.parse(to);
                                Event event = new Event(task_desc, startDate, endDate);
                                if (startDate.isAfter(endDate)) {
                                    throw new InvalidArgumentException("Your start date should be before your end date!");
                                }
                                if (task_status.equals("1")) {
                                    event.markComplete();
                                }
                                tasks.add(event);
                                break;
                            } catch (DateTimeException error) {
                                throw new InvalidArgumentException("Wrong date format! Please follow the format YYYY-MM-DD (e.g. 2000-01-01)");
                            }
                        default:
                            break;
                    }
                    line = reader.readLine();
                } catch (DukeException duke_error) {
                    duke_error.printStackTrace();
                    break;
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
     * @param dueDate the deadline of the task
     * @return the added Deadline
     */
    public Deadline addDeadline(String task, LocalDate dueDate) {
        Deadline taskDeadline = new Deadline(task, dueDate);
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
    public Event addEvent(String task, LocalDate from, LocalDate to) {
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
