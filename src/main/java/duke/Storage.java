package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


/**
 * Represents a storage of task list.
 * The main programme reads and writes the task list in disk through the storage.
 */
public class Storage {

    protected File savedTasks;

    /**
     * Class constructor.
     * @param filePath Location of the saved task list in disk.
     */
    public Storage(String filePath) {
        this.savedTasks = new File(filePath);
    }

    /**
     * Loads the saved task list.
     * @return An arraylist of saved tasks.
     * @throws DukeException when target file is not found at the given location / tasks saved are of incorrect format.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!savedTasks.createNewFile()) {
                // Load the exist file
                Scanner fileScanner = new Scanner(savedTasks);
                while (fileScanner.hasNextLine()) {
                    String record = fileScanner.nextLine();
                    String[] fields = record.split("\\|");
                    Task currTask;
                    if (fields[0].equals("T")) {
                        currTask = new Todo(fields[2]);
                    } else if (fields[0].equals("D")) {
                        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                        LocalDateTime by = LocalDateTime.parse(fields[3], dateformatter);
                        currTask = new Deadline(fields[2], by);
                    } else if (fields[0].equals("E")) {
                        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                        LocalDateTime from = LocalDateTime.parse(fields[3], dateformatter);
                        LocalDateTime to = LocalDateTime.parse(fields[4], dateformatter);
                        currTask = new Event(fields[2], from, to);
                    } else {
                        currTask = new Task("");
                    }

                    if (fields[1].equals("1")) {
                        currTask.setDone();
                    } else {
                        currTask.setNotDone();
                    }
                    taskList.add(currTask);
                }
            }
        } catch (IOException | DateTimeParseException e) {
            throw new DukeException("Loading failed");
        }
        return taskList;
    }

    /**
     * Writes the current task list into the file at given location
     * @param tasks Current task list.
     */
    public void write(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter("saved_tasks_list.txt");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toRecord() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

