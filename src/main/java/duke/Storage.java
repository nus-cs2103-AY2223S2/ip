package duke;

import duke.exceptions.DukeExceptions;
import duke.exceptions.InvalidFilePathException;

import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads Tasks previously stored in storage into the TaskList.
     *
     * @param parser the Parser needed to translate the Task from storage format to TaskList format
     * @return the ArrayList of Tasks needed to create a TaskList
     * @throws InvalidFilePathException If the file path of storage doesn't exist
     */
    public ArrayList<Task> loadTasks(Parser parser) throws DukeExceptions {
        File file = new File(this.filePath);
        ArrayList<Task> resultingList = new ArrayList<>();

        if(!file.exists() || file.length() == 0) {
            return resultingList;
        } else {
            try {
                List<String> content = Files.readAllLines(Paths.get(this.filePath));
                for (String line : content) {
                    Task thisTask = parser.translateFileToTaskList(line);
                    resultingList.add(thisTask);
                }
                return resultingList;
            } catch (IOException e) {
                throw new InvalidFilePathException();
            }
        }
    }

    /**
     * Updates the Tasks generated and reflects any changes made in storage.
     *
     * @param tasks TaskList of the resulting Tasks
     * @throws InvalidFilePathException If the file path of storage doesn't exist
     */
    public void updateTasks(TaskList tasks) throws DukeExceptions {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < tasks.getSize(); i++) {
                Task thisTask = tasks.getTasks().get(i);
                sb.append(thisTask.getDataString()).append(System.lineSeparator());
            }
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            throw new InvalidFilePathException();
        }
    }
}