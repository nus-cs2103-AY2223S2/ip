import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File storageFile;

    Storage(String filePath) {
        this.storageFile = new File(filePath);
    }
    // create an empty text file if there is no data or load the existing data to task list if data exist
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<Task>();
            if (storageFile.exists()) {
                // Parse the file and add the tasks
                Scanner s = new Scanner(storageFile);
                while (s.hasNext()) {
                    String line = s.nextLine();
                    switch (line.charAt(0)) {
                    case 'T':
                        tasks.add(Todo.fromSaveFormat(line));
                    case 'D':
                        tasks.add(Deadline.fromSaveFormat(line));
                    case 'E':
                        tasks.add(Event.fromSaveFormat(line));
                    }
                }
            } else {
                storageFile.createNewFile();
            }
            return tasks;
        } catch (IOException e) {
            System.out.println("Error creating Storage file");
            return new ArrayList<Task>();
        }
    }

    public void add() {

    }

    public void delete(int index) {

    }

    public void mark(int index) {

    }

    public void saveState() {

    }
}
