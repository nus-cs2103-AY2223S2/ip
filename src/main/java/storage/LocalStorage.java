package storage;

import java.io.*;
import java.time.DateTimeException;
import java.time.LocalDate;

import dukeException.DukeException;
import dukeException.InvalidArgumentException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Local storage to store the data from local file.
 */
public class LocalStorage {
    private File file;

    /**
     * Constructor to create a local storage using the data from local file.
     * @param path
     */
    public LocalStorage(String path) {
        File local_file = readFile(path);
        this.file = local_file;
    }

    /**
     * Read from file in local storage.
     * @param path Path to the file in the local storage.
     * @return File obtained from local storage
     */
    public static File readFile(String path) {
        File file = new File(path);
        file.getParentFile().mkdirs();

        if (file.exists()) {
            return file;
        }

        try {
            file.createNewFile();
        } catch (IOException io_error) {
            io_error.printStackTrace();
        }

        return file;
    }

    /**
     * Load tasks from file.
     * @param tasks List to add the tasks read from file.
     */
    public void loadTasks(TaskList tasks) {
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

    /**
     * Save tasks in the list into local storage before leaving the program.
     * @param tasks list to be saved into the file in local storage.
     */
    public void saveFile(TaskList tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.file));
            for (Task task: tasks.getTasks()) {
                writer.write(task.toData());
                writer.newLine();
            }
            writer.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}
