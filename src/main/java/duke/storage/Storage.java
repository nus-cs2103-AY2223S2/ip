package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Storage {

    private final String dirPath;
    private final String filePath;

    public Storage(String dirPath) {
        this.dirPath = dirPath;
        this.filePath = dirPath + "tasks.txt";
    }
    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<Task>();

        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String[] line = sc.nextLine().split(" ");
            if (line[0].equals("todo")) {
                ToDo t = new ToDo(sc.nextLine());
                if (line[1].equals("true")) {
                    t.mark();
                }
                tasks.add(t);
            } else if (line[0].equals("deadline")) {
                Deadline d = new Deadline(sc.nextLine(), line[2], line[3]);
                if (line[1].equals("true")) {
                    d.mark();
                }
                tasks.add(d);
            } else { // event
                Event e = new Event(sc.nextLine(), line[2], line[3], line[4], line[5]);
                if (line[1].equals("true")) {
                    e.mark();
                }
                tasks.add(e);
            }
        }
        return tasks;
    }

    void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(text);
        fw.close();
    }

    public void saveTasks(TaskList tasks) throws IOException {

        File file = new File(filePath);
        File dir = new File(dirPath);

        if (!dir.exists()) {
            dir.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
        writeToFile(tasks.generateTaskDetails());
    }
}
