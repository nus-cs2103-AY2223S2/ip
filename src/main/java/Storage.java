import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import org.apache.commons.io.FileUtils;

public class Storage {
    String home = System.getProperty("user.home");
    Path filePath;
    File dukeDataFile;

    public Storage(String s) {
        filePath = Paths.get(home, "data", s);
    }

    //Just a test, will remove later
    public void dirTest() {
        try {
            dukeDataFile = new File(filePath.toString());
            FileUtils.write(dukeDataFile, "Test\ning");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Supposed to return TaskList, which will be done later
    //Should save in CSV format for easier reading and writing
    public void load() {
//        dukeDataFile = new File(filePath.toString());
        if (Files.exists(filePath)) {
            //Means the file has been created before
            //This is where we read from it
            //return (loading)
        } else {
            //create file here, then return nothing?
        }
    }

    //Supposed to take in tasklist and save to the file
    //Should save in CSV format for easier reading and writing
    public void save() {
        //Implement saving here
    }
}
