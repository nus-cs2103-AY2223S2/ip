import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.stream.Collectors;

public class FileManager {

    public final String folderPath;
    public final String fileName;

    public final String filePath;
    
    FileManager(String folderPath, String fileName) {
        this.folderPath = folderPath;
        this.fileName = fileName;
        this.filePath = folderPath + "/" + fileName;
    }

    public ArrayList<Task> readTasksFromFile() {

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File tasksFile = new File(this.filePath);
            Scanner fileScanner = new Scanner(tasksFile);

            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                tasks.add(Task.getTaskFromString(data));
            }

            fileScanner.close();

        } catch (FileNotFoundException e) {
            File tasksFolderObject = new File(this.folderPath);

            if (!tasksFolderObject.exists()) {
                tasksFolderObject.mkdir();
            }

            File tasksFileObject = new File(this.filePath);
            tasksFileObject.createNewFile();

        } finally {
            return tasks;
        }

    }

    public void writeTasksToFile(ArrayList<Task> tasks) {
        try {

            List<String> taskStrings = tasks.stream()
                    .map(Task::getFileRepresentation)
                    .collect(Collectors.toList());

            String textToBeWritten = String.join("\n", taskStrings);

            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(textToBeWritten);
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("An error occurred in writing the data to file :/");

        }
    }

}
