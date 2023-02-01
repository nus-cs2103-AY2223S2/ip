package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.Event;
import task.Deadline;
import task.Todo;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            f.createNewFile();
            Scanner s = new Scanner(f);
            ArrayList<Task> arrOfTask = new ArrayList<>();
            while (s.hasNext()) {
                String[] arr = s.nextLine().split("\\|");
                Task t;
                if (arr[0].equals("T")) {
                    t = new Todo(arr[1]);
                } else if (arr[0].equals("D")) {
                    t = new Deadline(arr[1], LocalDate.parse(arr[3]));
                } else {
                    t = new Event(arr[1], LocalDate.parse(arr[3]), LocalDate.parse(arr[4]));
                }
                if (arr[2].equals("1")) {
                    t.taskDone();
                }
                arrOfTask.add(t);
            }
            return arrOfTask;
        } catch (IOException e) {
            throw new DukeException("Cannot open file");
        }
    }
    public void writeFile(TaskList t) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            t.writeToFile(fw);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Cannot write to file");
        }
    }
}
