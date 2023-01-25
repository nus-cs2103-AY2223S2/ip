package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private File file;

    public Storage(String filePath) throws DukeException{
        this.file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] split = s.nextLine().split(" \\| ", 5);
                switch(split[0]) {
                case "T":
                    list.add(new Todo(
                            split[2],
                            split[1].equals("1")
                    ));
                    break;
                case "D":
                    list.add(new Deadline(
                            split[2],
                            LocalDate.parse(split[3]),
                            split[1].equals("1")
                    ));
                    break;
                case "E":
                    list.add(new Event(
                            split[2],
                            LocalDate.parse(split[3]),
                            LocalDate.parse(split[4]),
                            split[1].equals("1")
                    ));
                    break;
                }
            }
            s.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return list;
    }

    public void save(TaskList list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : list) {
                fw.write(task.toFile() + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }
}
