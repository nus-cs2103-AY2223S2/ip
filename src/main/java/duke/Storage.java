package duke;
import duke.tasks.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILEPATH;
    public Storage(String filepath){
        this.FILEPATH = filepath;
    }

    public String getFilepath(){
        return FILEPATH;
    }

    public void save(ArrayList<Task> dataArray) {
            try {
                FileWriter fw = new FileWriter(FILEPATH);
                for (Task data : dataArray) {
                    String text = data.saveFormat();
                    fw.write(text + "\n");
                }
                fw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
        public ArrayList<String> load() throws FileNotFoundException {
            ArrayList<String> data = new ArrayList<>();
            File file = new File(FILEPATH);
            Scanner loader = new Scanner(file);
            while(loader.hasNextLine()) {
                data.add(loader.nextLine());
            }
            return data;
        }
    }


