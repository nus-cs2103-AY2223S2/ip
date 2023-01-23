import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    String home = System.getProperty("user.home");
    java.nio.file.Path path;

    public Storage(String s) {
        path = java.nio.file.Paths.get(home, "data", s);
        try {
            File newFile = new File(path.toString());
            if (newFile.createNewFile()) {
                System.out.println("New File Created!");
            } else {
                System.out.println("File exist!");
            }
        } catch (IOException e) {
            System.out.println("Error Occured");
            e.printStackTrace();
        }
    }
//
//    public void dirTest() {
//
//    }
}
