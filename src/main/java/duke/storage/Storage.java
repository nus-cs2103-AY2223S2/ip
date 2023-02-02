package duke.storage;
import duke.data.TypeOfTask;
import duke.action.Task;
import duke.exception.DukeException;
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
        this.filePath = "src" + File.separator + "main" + File.separator 
                + "java" + File.separator + "duke" + File.separator 
                + "data" + File.separator + "duke.txt";
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
    public List<Task> loadTasks() throws DukeException {
        List<Task> taskList = new ArrayList<>();
        try (ObjectInputStream load = new ObjectInputStream(new FileInputStream(this.filePath))) {
            taskList = (List<Task>) load.readObject();
            //return taskList;
        } catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new DukeException(TypeOfTask.storage,0);
        } finally {
            return taskList; 
        }
    }

    /*
     * save data to disk from program as serialized objects
     */
    public void saveTasks(List<Task> taskList) throws DukeException {
        try (ObjectOutputStream save = new ObjectOutputStream(new FileOutputStream(this.filePath))) {
            save.writeObject(taskList); 
        } catch (Exception e) {
            System.out.println(e);
            //e.printStackTrace();
            throw new DukeException(TypeOfTask.storage,1);
        }
    }

}
