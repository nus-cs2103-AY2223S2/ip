package wessy;

import java.util.List;
import java.util.ArrayList;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import wessy.task.Task;
import wessy.task.ToDo;
import wessy.task.Deadline;
import wessy.task.Event;

public class Storage {
    private final String folderPath;
    private final String fileName;

    public Storage(String filePath) {
        int lastIdx = filePath.lastIndexOf('/');
        if (lastIdx == -1) {
            this.folderPath = "";
            this.fileName = filePath;
        } else {
            this.folderPath = filePath.substring(0, lastIdx);
            this.fileName = filePath.substring(lastIdx + 1); // Need to add a slash
        }
    }

    public Storage() {
        this("data/savedTasks.txt");
    }

    String getFullPath() {
        return folderPath + "/" + fileName;
    }

    List<Task> load() throws SecurityException, IOException {
        List<Task> tasks = new ArrayList<Task>();

        File savedFile = new File(folderPath + "/" + fileName);
        if (savedFile.exists()) {
            Scanner sc = new Scanner(savedFile);

            while (sc.hasNextLine()) {
                String[] taskComponents = sc.nextLine().split("~%~");
                boolean isDone = taskComponents[1].charAt(0) == '1';

                switch (taskComponents[0].charAt(0)) {
                case 'T':
                    tasks.add(new ToDo(taskComponents[2], isDone));
                    break;
                case 'D':
                    tasks.add(new Deadline(taskComponents[2], Parser.parseDateTime(taskComponents[3]), isDone));
                    break;
                case 'E':
                    tasks.add(new Event(taskComponents[2], Parser.parseDateTime(taskComponents[3]),
                            Parser.parseDateTime(taskComponents[4]), isDone));
                    break;
                }
            }
        }
        return tasks;
    }

    void save(String tasksAsStr) throws IOException, SecurityException {
        // CREATE FOLDERS
        Path path = Paths.get(folderPath);
        Files.createDirectories(path);

        // CREATE FILE
        File file = new File(getFullPath());
        file.createNewFile();

        // WRITE FILE
        FileWriter fw = new FileWriter(getFullPath());
        fw.write(tasksAsStr);
        fw.close();
    }
}
