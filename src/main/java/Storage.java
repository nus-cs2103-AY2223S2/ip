import java.io.IOException;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Storage {
    private final String HOME_DIRECTORY = System.getProperty("user.dir");
    private File dataFile;
    private TaskList tasks;

    public Storage (TaskList tasks) {
        this.tasks = tasks;
    }

//    public void load() throws FileNotFoundException {
//        try {
//            File file = new File(this.HOME_DIRECTORY + "/data/duke.txt");
//            Scanner sc = new Scanner(file);
//
//            while (sc.hasNextLine()) {
//
//            }
//
//        } catch (FileNotFoundException e) {
//            System.out.println(e);
//        }
//    }

//    public void save() throws IOException {
//        try {
//            FileWriter fileWriter = new FileWriter(this.HOME_DIRECTORY + "/data/duke.txt");
//            for (int i = 0; i < this.tasks.size(); i++) {
//                String line = tasks.get(i);
//                fileWriter.write(line);
//            }
//        } catch (IOException e) {
//            System.out.println("Failed to save existing tasks...");
//        }
//
//    }
}
