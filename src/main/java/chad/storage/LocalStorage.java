package chad.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;

import chad.exception.DukeException;
import chad.exception.InvalidArgumentException;
import chad.parser.DateTimeParser;
import chad.task.Deadline;
import chad.task.Event;
import chad.task.Task;
import chad.task.Todo;

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
        File localFile = readFile(path);
        this.file = localFile;
    }

    /**
     * Read from file in local duke.storage.
     * @param path Path to the file in the local duke.storage.
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
                    String taskType = args[0].strip();
                    String taskStatus = args[1].strip();
                    String taskDesc = args[2].strip();
                    switch (taskType) {
                    case "T":
                        Todo todo = new Todo(taskDesc);
                        if (taskStatus.equals("1")) {
                            todo.markComplete();
                        }
                        tasks.add(todo);
                        break;
                    case "D":
                        String dueDate = args[3].trim();
                        try {
                            LocalDateTime formattedDueDate = DateTimeParser.parse(dueDate);
                            Deadline deadline = new Deadline(taskDesc, formattedDueDate);
                            if (taskStatus.equals("1")) {
                                deadline.markComplete();
                            }
                            tasks.add(deadline);
                            break;
                        } catch (DateTimeException error) {
                            throw new InvalidArgumentException("Wrong date format! Please follow the format "
                                    + "YYYY-MM-DD HHmm (e.g. 2000-01-01 2311)");
                        }
                    case "E":
                        String from = args[3].strip();
                        String to = args[4].strip();
                        try {
                            LocalDateTime startDate = DateTimeParser.parse(from);
                            LocalDateTime endDate = DateTimeParser.parse(to);
                            Event event = new Event(taskDesc, startDate, endDate);
                            if (startDate.isAfter(endDate)) {
                                throw new InvalidArgumentException("Your start date should be before your end date!");
                            }
                            if (taskStatus.equals("1")) {
                                event.markComplete();
                            }
                            tasks.add(event);
                            break;
                        } catch (DateTimeException error) {
                            throw new InvalidArgumentException("Wrong date format! Please follow the format "
                                    + "YYYY-MM-DD HHmm (e.g. 2000-01-01 2311)");
                        }
                    default:
                        assert false : "Invalid data inserted into duke.txt file";
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
     * @param tasks list to be saved into the file in local duke.storage.
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
