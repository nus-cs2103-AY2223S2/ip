package duke;

import duke.exceptions.IncorrectFileFormatException;
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
    private File file;

    /**
     * Constructor which creates a Storage instance.
     * Creates a File instance with the given file path whether the file exists or not.
     *
     * @param filePath path in computer directory to the file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
        try {
            file.createNewFile(); //no need for f.exists() chk
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
    public TaskList load() throws FileNotFoundException, IncorrectFileFormatException {
        Scanner s = new Scanner(file);
        TaskList l = new TaskList();

        while (s.hasNext()) {
            String str = s.nextLine();
            String[] parts = str.split("~", 5);

            switch (parts[0]) { // add assert
            case "T":
                l.add(new ToDo(parts[2], parts[1].equals("1")));
                break;
            case "D":
                l.add(new Deadline(parts[2], parts[1].equals("1"), LocalDate.parse(parts[3])));
                break;
            case "E":
                l.add(new Event(parts[2], parts[1].equals("1"), parts[3], parts[4]));
                break;
            default:
                throw new IncorrectFileFormatException();
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
