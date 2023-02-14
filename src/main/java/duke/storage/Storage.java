package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    public void saveTodoList(ArrayList<Task> tasksList) {
        int taskNumber = 1;
        StringBuilder stringBuilder = new StringBuilder();

        // this is how to get the path to duke.txt
        Path path = Paths.get("data", "duke.txt");

        for (Task t : tasksList) {
            stringBuilder.append(Integer.toString(taskNumber)).append(". ").append(t).append("\n");
            taskNumber++;
        }

        // tries to create a file
        try {
            File newFile = new File(path.toUri());
            newFile.createNewFile();
//            if (newFile.createNewFile()) {
//                // System.out.println("File created: " + newFile.getName());
//            } else {
//                // System.out.println("duke.txt already exists.");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write into the file
        try {
            FileWriter fileWriter = new FileWriter(path.toFile());
            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
