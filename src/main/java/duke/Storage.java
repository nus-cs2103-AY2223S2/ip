package duke;

import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private final String path;

    public Storage(String path) {
        this.path = path;
    }

    public void loadTasks(TaskList taskList) throws FileNotFoundException {
        File toRead = new File(this.path);
        Scanner sc = new Scanner(toRead);

        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(" \\| ");
            taskList.loader(line);

        }
        sc.close();
    }

    public void writeTasks(TaskList taskList) {
        try {
            new File(this.path).getParentFile().mkdirs();
            FileWriter writer = new FileWriter(this.path);
            for (int i = 0; i < taskList.size(); i++) {
                Task toSave = taskList.get(i);
                writer.write(toSave.toWrite());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to find file to write into");
        } catch (IOException e) {
            System.out.println("Unable to save task. Try again");
        }
    }
}
