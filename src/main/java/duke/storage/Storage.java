package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;
import duke.TaskList;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        checkFile(filePath);
    }
    
    private void checkFile(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    public void saveData(TaskList taskList) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < taskList.size(); i++) {
                Task t = taskList.getTask(i + 1);
                fileWriter.write(t.formatForFile());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public List<Task> loadFile() throws FileNotFoundException {
        List<Task> taskList = new ArrayList<>();
        File file = new File(filePath);
        if (file.isFile()) {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] data = sc.nextLine().split(" \\| ");
                Task task = null;
                switch (data[0]) {
                    case "T":
                        task = new Todo(data[2]);
                        break;
                    case "D":
                        task = new Deadline(data[2], data[3]);
                        break;
                    case "E":
                        task = new Event(data[2], data[3], data[4]);
                        break;
                }

                if (data[1].equals("1")) {
                    task.mark();
                }

                taskList.add(task);
            }
            sc.close();
        }

        return taskList;
     }
}
