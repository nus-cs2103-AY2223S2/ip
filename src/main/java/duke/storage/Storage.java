package duke.storage;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> load() {
        try {
            ArrayList<String> result = new ArrayList<>();
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String task = s.nextLine();
                result.add(task);
            }
            return result;
        } catch (FileNotFoundException e) {
            System.out.println("Error! Please check FIlename!");
            e.printStackTrace();
            return null;
        }
    }

    public void store(ArrayList<Task> list) {
        try {
                String home = System.getProperty("user.home");
                Path path = Paths.get(home);
                File f = new File( "./text-ui-test/duke.txt");
                if (!f.exists()) {
                    f.createNewFile();
                } else {
                    f.delete();
                    f.createNewFile();
                }
                FileWriter fw = new FileWriter("./text-ui-test/duke.txt");
                for (int i = 0; i < list.size(); i++) {
                    fw.write(list.get(i).toString());
                    fw.write('\n');
                }
                fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    }

