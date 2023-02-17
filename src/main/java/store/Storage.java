package store;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import dukeexception.DukeException;
import task.DeadLine;
import task.Event;
import task.Task;
import task.ToDo;

/**
 * Storage class to save data to file.
 */
public class Storage {
    private static final String PATH_STORAGE = "data/filepath.txt";
    private static final String DEFAULT_FILE_PATH = "data/tasks.txt";
    private static final String DEFAULT_DIRECTORY_PATH = "data";
    /**
     * Path to file for saving.
     */
    private String filePath;
    /**
     * Path to directory that contains file for saving.
     */
    private String directoryPath;

    /**
     * Public Constructor.
     */
    public Storage() throws DukeException {
        readFilePath();
        createFile();
    }

    private void readFilePath() throws DukeException {
        try {
            makeDirectory(DEFAULT_DIRECTORY_PATH);
            File fileStoragePath = new File(PATH_STORAGE);
            if (!fileStoragePath.exists()) {
                initFilePath(fileStoragePath);
                return;
            }
            Scanner scanner = new Scanner(fileStoragePath);
            if (scanner.hasNext()) {
                filePath = scanner.nextLine();
            } else {
                System.out.println("File corrupted, reinitialising file");
                initFilePath(fileStoragePath);
            }
            if (scanner.hasNext()) {
                directoryPath = scanner.nextLine();
            }
            scanner.close();
        } catch (IOException e) {
            throw new DukeException("File unable to be created");
        } catch (Exception e) {
            throw new DukeException("File corrupted");
        }
    }

    /**
     * Creates file for saving if it does not exist.
     */
    private void createFile() throws DukeException {
        try {
            if (directoryPath.equals(filePath)) {
                makeFile(filePath);
            } else {
                makeDirectory(directoryPath);
                makeFile(filePath);
            }
        } catch (IOException e) {
            throw new DukeException("File unable to be created");
        }
    }

    private void makeDirectory(String directoryPath) {
        if (directoryPath.isEmpty()) {
            return;
        }
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully");
            }
        }
        assert directory.exists();
    }

    private void makeFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("File created successfully");
            }
        }
        assert file.exists();
    }

    private void saveFilePath() throws IOException {
        FileWriter fileWriter = new FileWriter(PATH_STORAGE);
        fileWriter.write(filePath + "\n");
        fileWriter.write(directoryPath);
        fileWriter.close();
    }

    private void initFilePath(File file) throws IOException {
        if (file.createNewFile()) {
            System.out.println("File created successfully");
        }
        filePath = DEFAULT_FILE_PATH;
        directoryPath = DEFAULT_DIRECTORY_PATH;
        saveFilePath();
    }

    /**
     * Change data storage file path.
     * @param inputArr User input.
     * @return Message to user.
     */
    public String changeDataSource(String[] inputArr) throws DukeException {
        if (inputArr.length < 2) {
            throw new DukeException("Invalid command");
        }
        String[] testFilePath = inputArr[1].split("/");
        filePath = inputArr[1];
        if (testFilePath.length < 2) {
            directoryPath = inputArr[1];
        } else {
            directoryPath = inputArr[1].substring(0, inputArr[1].lastIndexOf("/"));
        }
        createFile();
        try {
            saveFilePath();
        } catch (IOException e) {
            throw new DukeException("Failed to save file path");
        }
        String output = "File path successfully changed";
        System.out.println(output);
        return output;
    }

    /**
     * Reads data from file and generates a TaskList.
     *
     * @return TaskList that is generated from data saved in file.
     */
    public TaskList readData() throws DukeException {
        ArrayList<Task> arrayList = new ArrayList<>();
        File file = new File(filePath);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String nextLine = scanner.nextLine();
                String[] taskLine = nextLine.trim().split(" \\| ");
                switch (taskLine[0]) {
                case "T":
                    arrayList.add(ToDo.generateTask(taskLine));
                    break;
                case "E":
                    arrayList.add(Event.generateTask(taskLine));
                    break;
                case "D":
                    arrayList.add(DeadLine.generateTask(taskLine));
                    break;
                default:
                    throw new DukeException("Invalid data!");
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
        return new TaskList(arrayList);
    }

    /**
     * Writes data of tasks to file.
     *
     * @param tasks To write data to file.
     */
    public void writeData(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            ArrayList<Task> arrayList = tasks.getTasks();
            for (Task task : arrayList) {
                fileWriter.write(task.getStoreTaskString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("Error: Unable to save task");
        }
    }
}
