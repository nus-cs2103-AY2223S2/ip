package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filePath;
    final protected String LOAD_ERROR_PREFIX_STRING = "Error retrieving local data: ";
    final protected String SAVE_ERROR_PREFIX_STRING = "Error saving data: ";

    /**
     * Returns a Storage object.
     *
     * @param filePath The file path of the data file to retrieve and store data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the file and generates a TaskList.
     * @return A TaskList based on the given data.
     * @throws DukeException If an unexpected entry is given.
     */
    public ArrayList<Task> load() throws DukeException {
        File dataFile = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        if (!dataFile.exists()) {
            throw new DukeException(LOAD_ERROR_PREFIX_STRING + "A data file does not exist");
        }
        try {
            Scanner fileScanner = new Scanner(dataFile);
            int rowCnt = 0;
            while (fileScanner.hasNext()) {
                rowCnt++;
                String[] inputStr = fileScanner.nextLine().trim().split(" \\| ");
                if (inputStr.length < 3) {
                    continue;
                }
                try {
                    Task newTask = null;
                    if (inputStr[0].equals(Task.TaskIcon.TODO.getSymbol())) {
                        newTask = new Todo(inputStr[2]);
                        tasks.add(newTask);
                    } else if (inputStr[0].equals(Task.TaskIcon.DEADLINE.getSymbol())) {
                        if (inputStr.length < 4) {
                            continue;
                        }
                        newTask = new Deadline(inputStr[2], inputStr[3]);
                        tasks.add(newTask);
                    } else if (inputStr[0].equals(Task.TaskIcon.EVENT.getSymbol())) {
                        if (inputStr.length < 5) {
                            continue;
                        }
                        newTask = new Event(inputStr[2], inputStr[3], inputStr[4]);
                        tasks.add(newTask);
                    }
                    if (newTask != null) {
                        if (Integer.parseInt(inputStr[1]) == 1) {
                            newTask.markAsDone();
                        }
                    }
                } catch (DukeException e) {
                    throw new DukeException(String.format(
                            LOAD_ERROR_PREFIX_STRING +
                                    "Error processing row %d of %s: %s",
                            rowCnt,
                            filePath,
                            e.getMessage()
                    ));
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(LOAD_ERROR_PREFIX_STRING + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the data from a given TaskList onto the data file.
     * @param tasks The TaskList to be saved.
     * @throws DukeException If an error occurs when writing to the file.
     */
    public void save(TaskList tasks) throws DukeException {
        File dataFile = new File(filePath);
        File parentDir = dataFile.getParentFile();
        if (parentDir != null && !parentDir.exists() && !parentDir.mkdirs()) {
            throw new DukeException(SAVE_ERROR_PREFIX_STRING + "Unable to create directory");
        }
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile());
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task;
                try {
                    task = tasks.getTask(i);
                } catch (BadCommandException e) {
                    throw new DukeException(SAVE_ERROR_PREFIX_STRING + "Unable to access task");
                }
                ArrayList<String> params = new ArrayList<>();
                params.add(task.getTaskIcon().getSymbol());
                params.add(task.isDone() ? "1" : "0");
                params.add(task.getDescription());
                if (task instanceof Event) {
                    Event castedTask = (Event) task;
                    params.add(castedTask.getStartTime());
                    params.add(castedTask.getEndTime());
                } else if (task instanceof Deadline) {
                    Deadline castedTask = (Deadline) task;
                    params.add(castedTask.getByDate());
                }
                String outputStr = String.join(" | ", params);
                fw.write(outputStr + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(SAVE_ERROR_PREFIX_STRING + e.getMessage());
        }
    }
}
