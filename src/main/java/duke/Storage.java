package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private ArrayList<Task> init;
    private String filePath;

    public Storage(String filePath) {
        this.init = new ArrayList<>();
        this.filePath = filePath;
    }
    public void store(TaskList taskList) {
        try {
            File f = new File(filePath);
            f.getParentFile().mkdirs();
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parse(String lineFromFile) {
        String[] arr = lineFromFile.split("/");

        char eventType = arr[0].charAt(0);
        boolean isDone = (arr[1].charAt(0) == '1');
        String description = arr[2];

        if (eventType == 'T') {
            Todo t = new Todo(description, isDone);
            init.add(t);
        } else if (eventType == 'D') {
            String by = arr[3].substring(4);
            Deadline d = new Deadline(description, isDone, by);
            init.add(d);
        } else if (eventType == 'E') {
            String from = arr[3].substring(6);
            String to = arr[4].substring(4);
            Event e = new Event(description, isDone, from, to);
            init.add(e);
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                parse(s);
            }

            sc.close();

            if (init.isEmpty()) {
                throw new DukeException("No saved tasks to load");
            }

            return init;
        } catch (FileNotFoundException e) {
            try {
                File f = new File(filePath);
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return init;
    }
}
