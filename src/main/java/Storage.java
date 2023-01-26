import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File storageFile;

    Storage(String filePath) {
        String basePath = "src/main/java/";
        File dataDirectory = new File(basePath + "/data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        this.storageFile = new File(basePath + filePath);

    }
    // create an empty text file if there is no data or load the existing data to task list if data exist
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            if (!storageFile.createNewFile()) {
                // Parse the file and add the tasks
                Scanner s = new Scanner(storageFile);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    switch (line.charAt(0)) {
                    case 'T':
                        tasks.add(Todo.fromSaveFormat(line));
                        break;
                    case 'D':
                        tasks.add(Deadline.fromSaveFormat(line));
                        break;
                    case 'E':
                        tasks.add(Event.fromSaveFormat(line));
                        break;
                    }
                }
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("Error creating Storage file");
            return new ArrayList<Task>();
        }
    }

    public void add(Task t) {
        try {
            FileWriter fw = new FileWriter(storageFile, true);
            if (storageFile.length() == 0) {
                fw.write(t.toSaveFormat());
            } else {
                fw.write(System.lineSeparator() + t.toSaveFormat());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void saveState(TaskList tasks) {
        try {
            int numberOfTasks = tasks.size();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < numberOfTasks; i++) {
                Task t = tasks.getTask(i);
                if (i != 0) {
                    sb.append(System.lineSeparator());
                }
                sb.append(t.toSaveFormat());
            }

            FileWriter fw = new FileWriter(storageFile);
            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }

    }
}
