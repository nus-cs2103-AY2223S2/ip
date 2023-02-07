package duke.components;

import duke.exceptions.DukeException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws DukeException {
        try{
            FileInputStream readData = new FileInputStream(filePath);
            ObjectInputStream readStream = new ObjectInputStream(readData);
            return (TaskList) readStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
    }

    public void updateStorage(TaskList tasks) {
        try {
            FileOutputStream writeData = new FileOutputStream(filePath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            writeStream.writeObject(tasks);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
