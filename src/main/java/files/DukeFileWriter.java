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
    public static String writeToFile(String path, TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            ArrayList<Task> taskArrayList = taskList.getTaskList();
            assert taskArrayList != null;
            if (taskArrayList.size() > 0) {
                for (int i = 0; i < taskArrayList.size() - 1; i++) {
                    fileWriter.write(taskArrayList.get(i).writeTask());
                    fileWriter.write("\n");
                }
                fileWriter.write(taskArrayList.get(taskArrayList.size() - 1).writeTask());
            }
            fileWriter.close();
            return "Saving your tasks my padawan, I am";
        } catch (IOException e) {
            return String.format("%s\n%s", "There is an error writing to file!", e.getMessage());
        }
    }

}
