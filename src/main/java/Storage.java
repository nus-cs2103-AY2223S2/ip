import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    String filePath;
    
    /*
     * default Constructor
     */
    public Storage(){
        this.filePath = "data" + File.separator + "acerizm.txt";
    }

    /*
     *  constructor that accepts a custom and different filepath
     */
    public Storage(String filePath){
        this.filePath = filePath;
    }

    /*
     * load data from disk to list of tasks as deserialized objects
     */
    public List<Task> loadTasks() {
        try (ObjectInputStream load = new ObjectInputStream(new FileInputStream(this.filePath))) {
            List<Task> taskList = (List<Task>) load.readObject();
            return taskList;
        } catch(Exception e){
            System.out.println("file is mssing!");
            return new ArrayList<Task>();
        }
    }

    /*
     * save data to disk from program as serialized objects
     */
    public static void saveTasks(List<Task> taskList) {
        String filePath = "data" + File.separator + "acerizm.txt";
        try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(filePath))) {
            save.writeObject(taskList);
            
        } catch (Exception e) {
            System.out.println("Cannot save");
        }
    }


}
