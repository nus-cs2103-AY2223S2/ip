import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File file;

    public Storage() {
        try {
            this.file = new File("./" + "data/duke.txt");
            file.createNewFile();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    public void update(String s) {
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
