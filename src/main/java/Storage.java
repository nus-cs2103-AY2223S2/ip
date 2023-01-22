import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private Path filename;

    public Storage() {
        filename = Paths.get(".", "data", "tasks.ser");
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        File file = filename.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename.toString()))) {
            oos.writeObject(tasks);
        }
    }

    public ArrayList<Task> loadTasks() throws IOException, ClassNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = filename.toFile();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        if(file.length() != 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename.toString()))) {
                tasks = (ArrayList<Task>) ois.readObject();
            }
        }
        return tasks;
    }
}





