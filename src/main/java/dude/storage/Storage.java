package dude.storage;

import dude.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        checkFile(filePath);
    }

    private void checkFile(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            try {
                File d = new File("data");
                if (!d.exists()) {
                    d.mkdir();
                }
                f.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void saveData(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String rawData = tasks.toRaw();
            fw.write(rawData);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> loadData() throws FileNotFoundException {
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath);
        if (f.exists()) {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] output = sc.nextLine().split(" \\| ");
                Task task = null;
                switch (output[0]) {
                    case "T":
                        task = new Todo(output[2]);
                        break;
                    case "D":
                        task = new Deadline(output[2], output[3]);
                        break;
                    case "E":
                        task = new Event(output[2], output[3], output[4]);
                        break;
                    default:
                        break;
                }

                if (output[1].equals("1")) {
                    task.mark();
                }

                taskList.add(task);
                Task.addTaskCount();
            }
        }
        return taskList;
    }
}