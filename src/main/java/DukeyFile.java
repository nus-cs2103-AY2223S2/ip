import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DukeyFile {

    public static File openFile() {
        File f = new File("DukeySave.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error! Unable to create file");
            }
        }
        return f;
    }

    public static void writeToFile(File file, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(file, true);
        fw.write(textToAdd + "\n");
        fw.close();
    }

    public static void deleteFile(File file) {
        file.delete();
    }

    public static void clearFile(File file) {
        deleteFile(file);
        openFile();
    }
}
