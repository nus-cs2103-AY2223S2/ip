package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {

    private String input;

    public Storage(String input) {
        this.input = input;
    }

    public ArrayList<String> load() {
        ArrayList<String> result = new ArrayList<>();
        result.add(input);
        return result;
    }

    public void store(ArrayList<Task> list) {
        try {
            String home = System.getProperty("user.home");
            Path path = Paths.get(home);
            File f = new File("./text-ui-test/duke.txt");
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

