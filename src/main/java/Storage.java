import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//check if the file and directory exists
// write to file

public class Storage {
    ArrayList<Task> tasks;

    public void store(ArrayList<Task> task) throws IOException {
        Path dataDir = Paths.get("ip/data");
        Path dataFile = Paths.get("ip/data/Duke.txt");
        // directory does not exists
        if (! Files.isDirectory(dataDir)) {
            Files.createDirectory(dataDir);
            Files.createFile(dataFile);
        } else {
            // if directory exist, check if duke.txt exists
            if (! Files.exists(dataFile)) {
                Files.createFile(dataFile);
            }
        }
        FileWriter writer = new FileWriter("ip/data/Duke.txt");
        int length = task.size();
        int i = 0;
        String finalTasks = "";
        while (i < length) {
            String taskInfo = task.get(i).toString().replace("[ ]", " | 0 |").replace("[X]", "| 1 |");
            taskInfo = taskInfo.replace("[", "").replace("]", "");
            finalTasks = finalTasks + taskInfo + "\n";
            i++;
        }
        writer.write(finalTasks);
        writer.close();
    }
}
