package storage;

import tasks.Task;
import tasks.TaskList;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;

/**
 * Represents the tasks stored on harddrive
 */
public class Storage {
    private File file = new File("data/duke.txt");
    /**
     * Constructor for storage object.
     * If data folder or duke.txt folder does not exist, creates them in directory.
     */
    public Storage(){
        if (!file.exists()){
            file.getParentFile().mkdir();
        }
    }

    /**
     * Updates the Storage object to reflect current tasks at hand
     * @param taskList TaskList object containing current tasks
     */
    public void updateFile(TaskList taskList){
        try{
            //generate string array of all tasks in TaskList
            ArrayList<String> stringTaskList = new ArrayList<>();
            for (Task task: taskList){
                stringTaskList.add(task.toFileString());
            }
            Files.write(file.toPath(), stringTaskList, StandardCharsets.UTF_8);
        } catch(IOException e){
            System.out.println("Something went wrong");
        }
    }
}
