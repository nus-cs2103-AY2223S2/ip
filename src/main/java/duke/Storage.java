package duke;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import exceptions.DukeException;
import exceptions.FolderNotFoundException;

/**
 * Represents a storage system, in this case a place where extraction and supplying of information to and from the
 * hard disk occurs.
 *
 * @author MrTwit99
 * @since 2023-02-01
 */
public class Storage {
    private String filePath;
    private String folderPath;

    private StringBuilder sb;

    /**
     * Returns a Storage object that retrieves and supplies information about the tasks to and from the file.
     *
     * @param filePath Relative path to locate the file with the stored tasks.
     * @param folderPath Relative path to locate the directory storing the file.
     */
    public Storage(String filePath, String folderPath) {
        this.filePath = filePath;
        this.folderPath = folderPath;
        this.sb = new StringBuilder();
    }

    /**
     * Returns an ArrayList of String type that contains information about the task loaded from the file with the aid
     * of getFileContent().
     * <p></p>
     * This method helps to catch any unexpected <b>exceptions</b> caused by getFileContent() and resolve them.
     *
     * @return ArrayList of String type, containing task information.
     * @throws IOException On input error.
     * @see IOException
     * @see ArrayList
     */
    public ArrayList<String> load() throws IOException {
        ArrayList<String> fileElements = new ArrayList<>();
        try {
            fileElements = getFileContents();
        } catch (FolderNotFoundException e) {
            Path tempFilePath = Paths.get("data/storage.txt");
            Files.createDirectories(tempFilePath.getParent());
            Files.createFile(tempFilePath);
            System.out.println(e);
        } catch (FileNotFoundException e) {
            sb.append("    ____________________________________________________________\n")
                    .append("    File 'storage' cannot be found in the folder 'data'.\n")
                    .append("    A new file 'storage' has been created for you under the folder 'data'\n")
                    .append("    for storing the tasks!\n")
                    .append("    ____________________________________________________________\n");
            Path tempFilePath2 = Paths.get("data/storage.txt");
            Files.createFile(tempFilePath2);
            System.out.println(sb.toString());
            sb.setLength(0);
        } finally {
            return fileElements;
        }
    }

    /**
     * Returns an ArrayList of String type that contains information about the task retrieved from the file.
     *
     * @return ArrayList of String type, containing task information.
     * @throws IOException On input error.
     * @throws FolderNotFoundException When the directory storing the file could not be located.
     * @see IOException
     */
    public ArrayList<String> getFileContents() throws IOException, FolderNotFoundException {
        ArrayList<String> fileElements = new ArrayList<>();
        DukeException.folderCheck(this.folderPath); // Checks if the folder exists
        BufferedReader fr = new BufferedReader(new FileReader(this.filePath));
        // Checks if the storage file is in the right folder
        String currLine;
        while ((currLine = fr.readLine()) != null) {
            fileElements.add(currLine); // Copy tasks from file over
        }
        fr.close();
        return fileElements;
    }

    /**
     * Writes to file allocated for storing task information whenever a new task has been created by the user.
     *
     * @param taskInfo String message of the task description and status.
     * @param taskList ArrayList of Task type, containing all the tasks available on Duke.
     * @throws IOException On input error.
     * @see IOException
     */
    public void writeToFile(String taskInfo, ArrayList<Task> taskList) throws IOException {
        ArrayList<String> fileTasks = new ArrayList<>();
        assert !taskInfo.equals("");
        try {
            fileTasks = load();
        } catch (IOException e) {
            System.out.println("An unexpected error has occurred: " + e.getMessage());
        } finally {
            FileWriter fw = new FileWriter(this.filePath, true);
            if (fileTasks.size() != 0) { // file has information inside
                fw.write(taskInfo);
            } else { // file is empty
                for (int i = 0; i < taskList.size(); i++) {
                    fw.write(taskList.get(i).getTaskInfo() + "\n");
                }
            }
            fw.close();
        }
    }

    /**
     * Overwrites the file allocated for storing task information whenever there is a modification to a task.
     * <p></p>
     * Example of modifications include deletion
     *
     * @param currTaskInfo Current task description and status of the task.
     * @param newTaskInfo Modified task description and status of the task.
     * @param taskNumber Current index of the task in the taskList.
     * @param taskList ArrayList of Task type, containing all the tasks available on Duke.
     * @throws IOException On input error.
     * @see IOException
     */
    public void writeToFile(String currTaskInfo, String newTaskInfo, int taskNumber,
                            ArrayList<Task> taskList) throws IOException {
        ArrayList<String> fileTasks = new ArrayList<>();
        try {
            fileTasks = load();
        } catch (IOException e) {
            System.out.println("An unexpected error has occurred: " + e.getMessage());
        } finally {
            FileWriter fw = new FileWriter("data/storage.txt");
            if (fileTasks.size() != 0) { // file not empty
                for (int i = 0; i < fileTasks.size(); i++) {
                    // ensures correct element is being amended
                    if ((fileTasks.get(i).equals(currTaskInfo)) && (i == taskNumber)) {
                        if (!newTaskInfo.equals("")) { // if content is not to be deleted
                            fw.write(newTaskInfo + "\n");
                        }
                        continue;
                    }
                    fw.write(fileTasks.get(i) + "\n");
                }
            } else { // file is empty
                for (int i = 0; i < taskList.size(); i++) {
                    if (i == taskNumber) { // ensures correct element is being amended
                        if (newTaskInfo.equals("")) { // if content is to be deleted
                            continue;
                        }
                        fw.write(newTaskInfo + "\n");
                        continue;
                    }
                    fw.write(taskList.get(i).getTaskInfo() + "\n");
                }
            }
            fw.close();
        }
    }
}
