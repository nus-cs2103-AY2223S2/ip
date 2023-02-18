package eevee;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deal with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    /**
     * Location of task list in hard disk
     */
    private String eeveeFilePath;

    /**
     * Constructor to instantiate a new Storage object and creates a tasks.txt file to store tasks if
     * one does not already exist in the given file path.
     * @param filePath location of task list in hard disk
     */
    public Storage(String filePath) {
        try {
            String currPath = new File("").getCanonicalPath();
            this.eeveeFilePath = currPath + filePath;
            File eeveeTxt = new File(eeveeFilePath);
            eeveeTxt.getParentFile().mkdirs();
            if (!eeveeTxt.exists()) {
                eeveeTxt.createNewFile();
            }
        } catch (IOException e) {
            System.out.printf("Something went wrong when creating file ): %s", e);
        }
    }

    /**
     * Reads the task list and adds all tasks currently
     * in task list to an <code>ArrayList</code>
     * @return The task list in the form of an <code>ArrayList</code>
     * @throws EeveeException if task list is empty
     */
    public ArrayList<Task> load() throws EeveeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scan = new Scanner(new File(eeveeFilePath));
            while (scan.hasNext()) {
                String currentLine = scan.nextLine();
                Task newTask = Parser.convertTaskFromLineInTaskList(currentLine);
                taskList.add(newTask);
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File is not found.");
        }
        if (taskList.isEmpty()) {
            throw new EeveeException("No task list yet.");
        } else {
            return taskList;
        }
    }

    /**
     * Updates the task list.
     * @param tasks list of updated tasks
     * @throws IOException if something went wrong while updating
     */
    public void updateTaskList(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(eeveeFilePath);
        for (int i = 0; i < tasks.getTaskListSize(); i++) {
            Task currTask = tasks.getTaskByIndex(i);
            writer.write(currTask.formatDescription() + "\n");
        }
        writer.close();
    }
}
