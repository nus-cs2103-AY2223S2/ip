package duke.storage;

import exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StorageList {
    private ArrayList<Task> list;
    private String path;

    public StorageList(String path) {
        this.list = new ArrayList<>();
        this.path = path;
    }


    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(this.path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] linearr = line.split("\\] ");
                String[] linetype = linearr[0].split("\\]");
                this.list.add(new Task(linearr[1], linetype[0].substring(1), linetype[1].substring(1)));
            }
            scanner.close();
            return this.list;
        } catch (FileNotFoundException e) {
            throw new DukeException("The file is not found in the directory");
        }

    }

    public void updateStorage() {
        try {
            File dir = new File("data");
            if (!dir.exists()) dir.mkdirs();
            java.nio.file.Path path = java.nio.file.Paths.get("data", "duke.txt");
            FileWriter writer = new FileWriter(String.valueOf(path));
            for (Task str : list) {
                writer.write(str.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
