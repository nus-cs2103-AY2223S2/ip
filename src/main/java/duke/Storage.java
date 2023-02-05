package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.CorruptedFileException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * This is a class responsible for the read/write of data for Duke.
 */
public class Storage {
    private final String FILE_PATH;

    /**
     * Constructor for Storage class.
     *
     * @param filePath the filePath of the text file containing data for Duke.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
    }

    /**
     * Loads the data previously saved.
     *
     * @return an ArrayList of type Task containing all the data previously saved.
     * @throws CorruptedFileException if file contains unreadable data.
     * @throws IOException if there is an error in the IO.
     */
    public ArrayList<Task> loadData() throws CorruptedFileException, IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        File targetFile = new File(FILE_PATH);
        assert targetFile != null;
        targetFile.getParentFile().mkdirs();
        targetFile.createNewFile();
        Scanner scanner = new Scanner(targetFile);
        while (scanner.hasNext()) {
            String taskData = scanner.nextLine();
            taskList.add(readData(taskData));
        }
        return taskList;
    }

    private Task readData(String data) throws CorruptedFileException {
        String[] dataSegments = data.split(" / ");
        String taskType = dataSegments[0];
        boolean isDone = dataSegments[1].equals("1");
        String taskDescription = dataSegments[2];
        if (taskType.equals("T")){
            return new Todo(taskDescription, isDone);
        } else if (taskType.equals("D")) {
            String[] taskInfo = taskDescription.split(" - ");
            try {
                LocalDate byDate = LocalDate.parse(taskInfo[1].trim(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
                return new Deadline(taskInfo[0], byDate, isDone);
            } catch (DateTimeParseException e) {
                throw new CorruptedFileException("Saved files have corrupted data for dates.\n"
                        + "Delete data/tasks.txt and restart Duke to try again");
            }
        } else if (taskType.equals("E")) {
            String[] taskInfo = taskDescription.split("-");
            try {
                LocalDate fromDate = LocalDate.parse(taskInfo[1].trim(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
                LocalDate toDate = LocalDate.parse(taskInfo[2].trim(), DateTimeFormatter.ofPattern("d/MM/yyyy"));
                return new Event(taskInfo[0], fromDate, toDate, isDone);
            } catch (DateTimeParseException e) {
                throw new CorruptedFileException("Saved files have corrupted data for dates.\n"
                        + "Delete data/tasks.txt and restart Duke to try again");
            }
        } else {
            throw new CorruptedFileException("Saved files have corrupted data for header.\n"
                                            + "Delete data/tasks.txt and restart Duke to try again");
        }
    }

    /**
     * Writes the data on Duke to the text file linked by filePath.
     *
     * @param taskList the TaskList containing local data of Duke.
     * @throws IOException when there is an error in IO.
     */
    public void saveData(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        assert fw != null;
        for (Task task : taskList.tasks) {
            fw.write(task.toDataFormatString() + "\n");
        }
        fw.close();
    }
}
