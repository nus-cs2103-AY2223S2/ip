package store;

import task.DeadLine;
import task.Event;
import task.Task;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class to save data to file.
 */
public class Storage {
    /**
     * Path to file for saving.
     */
    private final String filePath;
    /**
     * Path to directory that contains file for saving.
     */
    private final String directoryPath;

    /**
     * Public Constructor.
     *
     * @param filePath String path to file for saving.
     * @param directoryPath String path to directory that contains file for saving.
     */
    public Storage(String filePath, String directoryPath) {
        this.filePath = filePath;
        this.directoryPath = directoryPath;
        createFile();
    }

    /**
     * Creates file for saving if it does not exist.
     */
    private void createFile() {
        try {
            File directory = new File(directoryPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("File unable to be created");
        }
    }

    /**
     * Reads data from file and generates a TaskList.
     *
     * @return TaskList that is generated from data saved in file.
     */
    public TaskList readData() {
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
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new TaskList(arrayList);
    }

    /**
     * Writes data of tasks to file.
     *
     * @param taskList To write data to file.
     */
    public void writeData(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            ArrayList<Task> arrayList = taskList.getStore();
            for (Task task : arrayList) {
                fileWriter.write(task.getStoreTaskString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Unable to save task");
        }
    }
}
