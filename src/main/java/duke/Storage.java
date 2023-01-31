package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Storage {

    private String filePath;
    private File f;
    private String text;

    /**
     * Constructor which creates a Storage instance.
     * Creates a File instance with the given file path whether the file exists or not.
     *
     * @param filePath path in computer directory to the file.
     * @return Storage instance with File object created.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        f = new File(filePath);
        try {
            if(!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a List of Task after parsing the File object.
     * Runs at the start of Duke program.
     *
     * @return TaskList instance.
     * @throws FileNotFoundException  If File is not found by Scanner.
     */
    public TaskList load() throws FileNotFoundException {
        Scanner s = new Scanner(f);
        TaskList l = new TaskList();

        while (s.hasNext()) {
            String str = s.nextLine();
            String parts[] = str.split("~", 5);

            switch (parts[0]) {
            case "T":
                l.add(new ToDo(parts[2], parts[1].equals("1")));
                break;
            case "D":
                l.add(new Deadline(parts[2], parts[1].equals("1"), LocalDate.parse(parts[3])));
                break;
            case "E":
                l.add(new Event(parts[2], parts[1].equals("1"), parts[3], parts[4]));
                break;
            }
        }
        return l;
    }

    /**
     * Saves TaskList into File for storage.
     * Runs at the end of Duke program.
     *
     * @param l TaskList instance.
     * @throws IOException  If there is an error when writing to File.
     */
    public void save(TaskList l) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for (Task i : l) {
            fw.write(i.saveFormat() + "\n");
        }
        fw.close();
    }

}
