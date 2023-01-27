import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public TaskList load() throws DukeException{
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
