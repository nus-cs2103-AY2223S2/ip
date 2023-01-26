import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    File file;

    /** 
     * A public constructor to initialize Storage instance.
     * 
     * @param path The path of the file.
     */
    Storage(String path) {
        this.file = new File(path);
    }

    /** 
     * Opens file containing task data and loads them into Task arraylist.
     * 
     * @param tasks List of tasks.
     */
    protected void loadData(TaskList tasks) {
        try {

            if (!file.exists()) {
                throw new DukeException("File does not exists!\n");
            }
            
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] descriptions = input.split(" ", 2);
                Parser.parseInput(tasks, descriptions[0], descriptions);
            }

            tasks.finishInitialization();

        } catch (DukeException error) {
            tasks.finishInitialization();
            // Ui.errorMsg(error.getMessage());
        } catch (FileNotFoundException error) {
            tasks.finishInitialization();
            // Ui.errorMsg(error.getMessage());
        }
    }

    /** 
     * Saves task description into file. 
     *
     * @param path The path of the file.
     * @param tasks List of task objects.
     */
    protected void saveData(String path, TaskList tasks) {
        try {
            FileWriter fileWriter = new FileWriter(path);

            for (Task task : tasks.getTaskList()) {
                fileWriter.write(task.getDescription());
                fileWriter.write(System.lineSeparator());
            }

            fileWriter.close();
        } catch (IOException error) {
            // Ui.errorMsg("Something went wrong: " + error.getMessage());
        }
    }

}