package duke;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
public class Storage {
    File saveFile;
    
    public Storage(String filepath) {
        this.saveFile = new File(filepath);
    }

    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        if (saveFile.exists()) {
            try {
                ObjectInputStream objin = new ObjectInputStream(new FileInputStream(saveFile));
                tasks = (TaskList)objin.readObject(); 
                objin.close();
            } catch (Exception e) {
                System.out.println("File is empty");
            }
            return tasks;
        } else {
            try {
                saveFile.createNewFile();
                throw new DukeException("unable to load save file");
            } catch (IOException e) {
                System.out.println("Cant create file");
            }
            return tasks;
            } 

            // try {
            //     saveFile.createNewFile();
            // } catch (IOException e) {
            //     System.out.print("Cant create file");
            // }
    }

    public void saveTaskList(TaskList tasks) {
        try { 
                ObjectOutputStream objout = new ObjectOutputStream(new FileOutputStream(saveFile));
                objout.writeObject(tasks);
                objout.close();
            } catch (IOException e) {
                System.out.println("could not create save file");
            }
    }

}
