package duke.utils;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import duke.Tasks.Task;

public class Storage {
    private static DukeIo dukeIo = new DukeIo();
    private ArrayList<Task> storedTasks;
    private FileInputStream fis;
    private ObjectInputStream ois;
    private FileOutputStream fos;
    private ObjectOutputStream o;

    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws IOException, ClassNotFoundException {
        fis = new FileInputStream("./data/duke.txt");
        ois = new ObjectInputStream(fis);
        this.storedTasks = (ArrayList<Task>) ois.readObject();
        fis.close();
        ois.close();
        dukeIo.notifyLoad();
        return storedTasks; 
    }    

    public void saveFrom(ArrayList<Task> tasks) throws IOException {
        if (tasks.size() > 0) {
            Files.createDirectories(Paths.get("./data/"));
            fos = new FileOutputStream("./data/duke.txt");
            o = new ObjectOutputStream(fos);
            o.writeObject(tasks);
            o.close();
            fos.close();
            dukeIo.notifySave();
        }
    }

}
