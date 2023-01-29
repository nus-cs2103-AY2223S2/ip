import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    //public static final String NAME = "./data/lulu.txt";
    public File file;
    public String path;
    public Storage(String filePath) {
        this.file = new File(filePath);
        path = filePath;
    }

    public boolean isSavePresent() {
        try {
            boolean isPresent = !(file.createNewFile());
            if (!isPresent) {
                System.out.println("Installing text file...");
            }
            return isPresent;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }

    public void writeSave(ArrayList<String> list) {
        try {
            FileWriter myWriter = new FileWriter(path);
            int size = list.size();
            for (int i = 0; i < size; i ++) {
                myWriter.write(list.get(i));
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }

    public void readSave(TaskList tasks) {
        try {
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                tasks.load(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
}
