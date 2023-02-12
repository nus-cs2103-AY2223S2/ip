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
    private String dataFolder = new File("data").getAbsolutePath();
    private String tasksFile = new File("data/duke.txt").getAbsolutePath();
    private Path tfPath = Paths.get(tasksFile);
    private ArrayList<String> stringTaskList = new ArrayList<String>();

    /**
     * Constructor for storage object.
     * If data folder or duke.txt folder does not exist, creates them in directory.
     */
    public Storage(){
        //if data folder does not exist
        File f = new File(dataFolder);
        if (!f.exists()){
            f.mkdir();
        }

        File f1 = new File(tasksFile);
        if (f1.exists()) {
            try {
                List<String> fileContent = new ArrayList<>(Files.readAllLines(tfPath, StandardCharsets.UTF_8));
                for (int i = 0; i < fileContent.size(); i++) {
                    stringTaskList.add(fileContent.get(i));
                }
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
        } else{
            try {
                f.createNewFile();
            } catch(IOException e){
                e.getMessage();
            }
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
            Files.write(tfPath, stringTaskList, StandardCharsets.UTF_8);
        } catch(IOException e){
            System.out.println("Something went wrong");
        }
    }
    public void addTask(String desc){
        try{
            FileWriter fw = new FileWriter(tasksFile, true);
            //generate string to write
            String task = "T | 0 | " + desc + "\n";
            fw.write(task);
            fw.close();
        } catch(java.io.IOException e){
            System.out.println("Cannot write to non existent file");
        }
    }
    public void addTask(String desc, String by){
        String task = "T | 0 | " + desc + "| " + by + " |\n";
        fileWriter(task);
    }
    public void addTask(String desc, String from, String to){
        String task = "T | 0 | " + "| " + from + "-" + to + " |\n";
        fileWriter(task);
    }
    public void markFile(String index, TaskList taskList){
        int idx = Integer.parseInt(index);
        assert idx > -1 : "Index cannot be negative";
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(tfPath, StandardCharsets.UTF_8));
            //Files.write(tfPath, taskList, StandardCharsets.UTF_8);
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        //Files.write(FILE_PATH, fileContent, StandardCharsets.UTF_8);

    }

    private void fileWriter(String input){
        try {
            FileWriter fw = new FileWriter(tasksFile, true);
            fw.write(input);
            fw.close();
        } catch(IOException e){
            System.out.println("Something went wrong " + e.getMessage());
        }
    }
    private int getCount() {
        try {
            int count = (int) Files.lines(tfPath).count();
            return count;
        } catch (IOException e){
            System.out.println(e.getMessage());
            return -1;
        }
    }

}
