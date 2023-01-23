package kude;

import kude.models.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputFilter;
import java.io.ObjectOutputStream;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public TaskList readTaskList() throws IOException, ClassNotFoundException {
        var file = new File(path);
        if (file.createNewFile()) {
            writeTaskList(new TaskList());
        }
        var fis = new FileInputStream(file);
        var ois = new ObjectInputStream(fis);
        // restrict to only models
        var filter = ObjectInputFilter.Config.createFilter("kude.models.*;");
        ois.setObjectInputFilter(filter);
        var tasks = (TaskList)ois.readObject();
        ois.close();
        fis.close();
        return tasks;
    }

    public void writeTaskList(TaskList list) throws IOException {
        var file = new File(path);
        file.createNewFile();
        var fos = new FileOutputStream(file);
        var oos = new ObjectOutputStream(fos);
        oos.writeObject(list);
        oos.close();
        fos.close();
    }
}
