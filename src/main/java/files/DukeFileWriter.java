package files;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import tasks.Task;
import tasks.TaskList;

/**
 * A file writer for Duke to write tasks into files.
 */
public class DukeFileWriter {

    /**
     * Writes tasks into file.
     * @param path path of file for Duke to write on
     * @param taskList task list from which Duke retrieves and writes tasks into file
     */
    public static void writeToFile(String path, TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            if (taskArrayList.size() > 0) {
                for (int i = 0; i < taskArrayList.size() - 1; i++) {
                    fileWriter.write(taskArrayList.get(i).writeTask());
                    fileWriter.write("\n");
                }
                fileWriter.write(taskArrayList.get(taskArrayList.size() - 1).writeTask());
            }
            fileWriter.close();
            System.out.println("Saving your tasks my padawan, I am");
        } catch (IOException e) {
            System.out.println("There is an error writing to file!");
            System.out.println(e.getMessage());
        }
    }

}
