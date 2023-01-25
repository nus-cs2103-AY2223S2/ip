package berry.storage;

import berry.task.Task;
import berry.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(createFile());
            for (Task task : tasks.getList()) {
                writer.write(task.interpretTaskToString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> listOfTasks = new ArrayList<Task>();

        Scanner sc = new Scanner(createFile());
        while (sc.hasNext()) {
            Task task = Task.interpretStringToTask(sc.nextLine());
            listOfTasks.add(task);
        }
        return listOfTasks;
    }

    private File createFile() {
        File dataFile = new File(filePath);
        File folder =  new File(filePath.split("/")[0]);
        try {
            folder.mkdir();
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("handle exception case");
        }
        return dataFile;
    }
}
