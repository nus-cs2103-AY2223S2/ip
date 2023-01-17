package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import duke.task.*;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void loadData(TaskList taskList) throws IOException {
        try {
            Scanner in = new Scanner(Paths.get("data", "duke.txt"));
            while(in.hasNextLine()) {
                String line = in.nextLine();
                parse_data(line, taskList);
            }
        } catch (IOException e) {
            File file = new File("data/duke.txt");
            file.getParentFile().mkdirs();
            FileWriter fw = new FileWriter(file);
            fw.close();
        }
    }

    public void writeToData(String newData) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(newData);
        fw.close();
    }

    public void parse_data(String line, TaskList taskList) {
        String[] parts = line.split("\\|");
        if (parts[0].trim().equals("[T]")) {
            taskList.initAdd(new ToDo(parts[2].trim(), parts[1].trim().equals("1")));
        } else if (parts[0].trim().equals("[D]")) {
            taskList.initAdd(new Deadline(parts[2].trim(), parts[3].trim(), parts[1].trim().equals("1")));
        } else if (parts[0].trim().equals("[E]")) {
            String[] fromAndTo = parts[3].trim().split("-");
            taskList.initAdd(new Event(parts[2].trim(), fromAndTo[0], fromAndTo[1], parts[1].trim().equals("1")));
        }
    }
}
