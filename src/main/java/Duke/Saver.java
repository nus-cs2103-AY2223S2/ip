package Duke;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class to save and load tasks to and from Duke.
 * @author Bryan Juniano
 */

public class Saver {
    protected final static String PATH = "./data";
    protected final static String FILE_NAME = "/tasks.txt";
    public static void createFile(){
        File directory = new File(PATH);
        if(!directory.exists()) {
            directory.mkdirs();
        }
        File file = new File(PATH+FILE_NAME);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save(TaskList taskList){
        try {
            createFile();
            FileWriter writer = new FileWriter(PATH + FILE_NAME);
            writer.write(taskList.toSave());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(TaskList taskList){
        try {
            createFile();
            Path path = Paths.get(PATH+FILE_NAME);
            taskList.fromSave(Files.readString(path));
            System.out.println("tasks loaded successfully.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
