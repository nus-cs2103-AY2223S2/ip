package Week2.src.main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class Storage {
    private String filePath = "";

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
    }

    public String load() {
        return filePath;
    }

}
