package src.main.c4po;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {

    String filePath;
    String directoryPath;

    File storageFile;

    /**
     * Creates a Storage object, which stores information of file path, directory path and
     * handles writing to the file. Upon
     * @param filePath
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        List<String> direPath = Arrays.asList(filePath.split("/"));
        ArrayList<String> dirPath = new ArrayList<>(direPath);
        dirPath.remove(dirPath.size()-1);

        this.directoryPath = String.join("/", dirPath);

        try {
            //----- Init directory and storage
            Files.createDirectories(Paths.get(directoryPath));
            storageFile = new File(filePath);

            if (!storageFile.exists()) {
                boolean isSuccess = storageFile.createNewFile();
                if (isSuccess) {
                    Ui.showCreatedDbMsg();
                } else {
                    Ui.showCreateDbError();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    /**
     * Loads the tasks from the given file path, into a ArrayList<Task>
     * to be accepted by TaskList constructor
     */
    public ArrayList<Task> load() throws FileNotFoundException {

        Scanner s = new Scanner(storageFile); // create a Scanner using the File as the source
        ArrayList<Task> taskItems = new ArrayList<>();

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] lineItems = line.split(Task.delimiter);
            String tag = lineItems[0];
            String isDone = lineItems[1];
            boolean isDoneBool = isDone.equals("1");
            String desc = lineItems[2];
            switch (tag) {
                case "D":
                    String by = lineItems[3];
                    Deadline newDl = new Deadline(desc, by, isDoneBool);
                    taskItems.add(newDl);
                    break;
                case "E":
                    String start = lineItems[3];
                    String end = lineItems[4];
                    Event newEv = new Event(desc, start, end, isDoneBool);
                    taskItems.add(newEv);
                    break;
                case "T":
                    ToDo newTd = new ToDo(desc, isDoneBool);
                    taskItems.add(newTd);
                    break;
                default:
                    System.out.println("Error creating task");
                    break;
            }
        }


        return taskItems;

    }

    /**
     * Writes all tasks (overriding) to the data file
     * @param tasks
     * @throws IOException
     */
    public void writeToFile(TaskList tasks) throws IOException {
        String toWrite = tasks.getListFileFormat();
        FileWriter fw = new FileWriter(filePath);
        fw.write(toWrite);
        fw.close();
    }


    /**
     * Appends to end of file, content. Does not override old content
     * @param content
     * @throws IOException
     */
    public void writeToFile(String content) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(content);
        fw.close();
    }
}
