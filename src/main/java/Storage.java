import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Storage {
    String home = System.getProperty("user.home");
    Path filePath;
    File dukeDataFile;

    public Storage(String s) {
        filePath = Paths.get(home, "data", s);
    }

    //Just a test, will remove later
    public void dirTest() {
        try {
            dukeDataFile = new File(filePath.toString());
            FileUtils.write(dukeDataFile, "Test\ning");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Supposed to return an arraylist of tasks
    //Should save in CSV format for easier reading and writing
    public TaskList load() {
        dukeDataFile = new File(filePath.toString());
        if (Files.exists(filePath)) {
            System.out.println("FILE EXIST");
            //Means the file has been created before
            //This is where we read from it
            //return (loading)
            TaskList loadTaskList= new TaskList();
            try {
                List<String> allLines = Files.readAllLines(filePath);
                for (String line : allLines) {
                    String[] lineArray = line.split(",");
                    boolean b;
                    if (lineArray[1].equals("1")) {
                        b = true;
                    } else {
                        b = false;
                    }
                    switch (lineArray[0]) {
                        case "T":
                            loadTaskList.addTask(lineArray[2], b);
                            break;
                        case "D":
                            loadTaskList.addTask(lineArray[2], lineArray[3], b);
                            break;
                        case "E":
                            loadTaskList.addTask(lineArray[2], lineArray[3], lineArray[4], b);
                            break;
                        case "":
                            break;
                    }
                }
                return loadTaskList;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("NO EXIST");
            try {
                FileUtils.write(dukeDataFile, "");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return new TaskList();
    }

    //Supposed to take in tasklist and save to the file
    //Should save in CSV format for easier reading and writing
    public void save() {
        //Implement saving here
    }
}
